package com.loops.loopstrapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore.Audio.Media
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loops.loopstrapp.adapter.CourseRVAdapter
import com.loops.loopstrapp.`interface`.ItemSelect
import com.loops.loopstrapp.model.ColorItem
import com.loops.loopstrapp.model.SongsItem

class MainActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener {
    lateinit var courseRV: RecyclerView
    lateinit var stack1: TextView
    lateinit var stackbar1: ProgressBar

    lateinit var stack2: TextView
    lateinit var stackbar2: ProgressBar
    lateinit var stack3: TextView
    lateinit var stackbar3: ProgressBar
    lateinit var stack4: TextView
    lateinit var stackbar4: ProgressBar
    lateinit var play_all: ImageView

    private lateinit var courseRVAdapter: CourseRVAdapter
    var mainList = ArrayList<SongsItem>()
    private lateinit var mediaPlayer: MediaPlayer

    private var stacklist1 = ArrayList<Int>()
    private var stack1Progress: Int = 0;
    private var stacklist2 = ArrayList<Int>()
    private var stack2Progress: Int = 0;
    private var stacklist3 = ArrayList<Int>()
    private var stack3Progress: Int = 0;
    private var stacklist4 = ArrayList<Int>()
    private var stack4Progress: Int = 0;
    private var stackall = ArrayList<Int>()

    private var buttonNo: Int = 0

    private var current_index: Int = 0
    lateinit var objectAnimator1: ObjectAnimator
    lateinit var objectAnimator2: ObjectAnimator
    lateinit var objectAnimator3: ObjectAnimator
    lateinit var objectAnimator4: ObjectAnimator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
        prepareList()
        setAdapter()
    }

    private fun bind() {
        courseRV = findViewById(R.id.idRVCourses)
        stack1 = findViewById(R.id.stack1)
        stackbar1 = findViewById(R.id.stackbar1)
        stack2 = findViewById(R.id.stack2)
        stackbar2 = findViewById(R.id.stackbar2)
        stack3 = findViewById(R.id.stack3)
        stackbar3 = findViewById(R.id.stackbar3)
        stack4 = findViewById(R.id.stack4)
        stackbar4 = findViewById(R.id.stackbar4)
        play_all = findViewById(R.id.play_all)
        stacklist1 = ArrayList<Int>()
        stacklist2 = ArrayList<Int>()
        stacklist3 = ArrayList<Int>()
        stacklist4 = ArrayList<Int>()
        stackall = ArrayList<Int>()
        buttonAnim()

        stack1.setOnClickListener {
            buttonNo = 1
            current_index = 0
            stacklist1.clear()
            for (i in mainList.indices) {
                if (i == 0 || i == 4 || i == 8 || i == 12) {
                    for (j in mainList[i].list.indices) {
                        if (mainList[i].list[j].isSelected) {
                            println("Tune object" + mainList[i].list[j].tune)
                            stacklist1.add(mainList[i].list[j].tune)
                        }
                    }
                }
            }


            if (stacklist1.size > 0) {
                for (item in stacklist1) {
                    println("stack List 1 " + item + " list Size " + stacklist1.size)
                }
                mediaPlayer = MediaPlayer.create(this, stacklist1[0])
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.setOnCompletionListener(this)
                mediaPlayer.start()
                objectAnimator1.start()
            }
        }

        stack2.setOnClickListener {
            current_index = 0
            buttonNo = 2
            stacklist2.clear()
            for (i in mainList.indices) {

                if (i == 1 || i == 5 || i == 9 || i == 13) {
                    for (j in mainList[i].list.indices) {
                        if (mainList[i].list[j].isSelected) {
                            stacklist2.add(mainList[i].list[j].tune)
                        }
                    }
                }
            }


            if (stacklist2.size > 0) {
                for (item in stacklist2) {
                    println("stack List 2 " + item + " list Size " + stacklist2.size)
                }
                mediaPlayer = MediaPlayer.create(this, stacklist2[0])
                mediaPlayer.setOnCompletionListener(this)
                mediaPlayer.start()
                objectAnimator2.start()
            }

        }

        stack3.setOnClickListener {
            current_index = 0
            buttonNo = 3
            stacklist3.clear()
            for (i in mainList.indices) {
                if (i == 2 || i == 6 || i == 10 || i == 14) {
                    for (j in mainList[i].list.indices) {
                        if (mainList[i].list[j].isSelected) {
                            stacklist3.add(mainList[i].list[j].tune)
                        }
                    }
                }
            }

            if (stacklist3.size > 0) {
                for (item in stacklist3) {
                    println("stack List 3 " + item + " list Size " + stacklist3.size)
                }
                mediaPlayer = MediaPlayer.create(this, stacklist3[0])
                mediaPlayer.setOnCompletionListener(this)
                mediaPlayer.start()
                objectAnimator3.start()
            }

        }

        stack4.setOnClickListener {
            current_index = 0
            buttonNo = 4
            stacklist4.clear()
            for (i in mainList.indices) {

                if (i == 3 || i == 7 || i == 11 || i == 15) {
                    for (j in mainList[i].list.indices) {
                        if (mainList[i].list[j].isSelected) {
                            stacklist4.add(mainList[i].list[j].tune)
                        }
                    }
                }
            }

            if (stacklist4.size > 0) {
                for (item in stacklist4) {
                    println("stack List 4 " + item + " list Size " + stacklist4.size)
                }
                mediaPlayer = MediaPlayer.create(this, stacklist4[0])
                mediaPlayer.setOnCompletionListener(this)
                mediaPlayer.start()
                objectAnimator4.start()
            }

        }

        play_all.setOnClickListener {
            current_index = 0
            buttonNo = 0
            stackall.clear()
            for ((i, v) in mainList.withIndex()) {
                for ((j, x) in mainList[i].list.withIndex()) {
                    if (mainList[i].list[j].isSelected) {
                        stackall.add(mainList[i].list[j].tune)
                    }
                }
            }

            if (stackall.size > 0) {
                for (item in stackall) {
                    println("stack List All " + item + " list Size " + stackall.size)
                }
                mediaPlayer = MediaPlayer.create(this, stackall[0])
                mediaPlayer.setOnCompletionListener(this)
                mediaPlayer.start()
            }
        }

    }

    fun buttonAnim() {
        objectAnimator1 =
            ObjectAnimator.ofInt(stackbar1, "progress", stackbar1.progress, 100).setDuration(2000)

        objectAnimator1.addUpdateListener {
            val progress: Int = objectAnimator1.animatedValue as Int
            stackbar1.progress = progress
        }

        objectAnimator2 =
            ObjectAnimator.ofInt(stackbar2, "progress", stackbar2.progress, 100).setDuration(2000)

        objectAnimator2.addUpdateListener {
            val progress: Int = objectAnimator2.animatedValue as Int
            stackbar2.progress = progress
        }

        objectAnimator3 =
            ObjectAnimator.ofInt(stackbar3, "progress", stackbar3.progress, 100).setDuration(2000)

        objectAnimator3.addUpdateListener {
            val progress: Int = objectAnimator3.animatedValue as Int
            stackbar3.progress = progress
        }


        objectAnimator4 =
            ObjectAnimator.ofInt(stackbar4, "progress", stackbar4.progress, 100).setDuration(2000)

        objectAnimator4.addUpdateListener {
            val progress: Int = objectAnimator4.animatedValue as Int
            stackbar4.progress = progress
        }
    }

    override fun onStop() {
        super.onStop()

        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }

    override fun onCompletion(p0: MediaPlayer?) {
        if (buttonNo == 1) {
            play(stacklist1)
        } else if (buttonNo == 2) {
            play(stacklist2)
        } else if (buttonNo == 3) {
            play(stacklist3)
        } else if (buttonNo == 4) {
            play(stacklist4)
        } else {
            play(stackall)
        }
    }

    private fun play(list: ArrayList<Int>) {
        if (list.size > 0) {
            current_index += 1
            if (list.size != current_index) {
                if (list[current_index] != 0) {
                    println("tune name playing " + list[current_index])
                    var afd: AssetFileDescriptor =
                        this.resources.openRawResourceFd(list[current_index])
                    try {
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(
                            afd.fileDescriptor, afd.startOffset, afd.declaredLength
                        )
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        afd.close()
                    } catch (e: Exception) {
                        //Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
                    } catch (e: Exception) {
                        //Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
                    } catch (e: Exception) {
                        //Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
                    }
                } else {
                    current_index = 0
                }
            }
        } else {
            current_index = 0
            buttonNo = 0
        }
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }

    private fun prepareList() {
        val pColor0 = getColor(R.color.transparent)
        val pColor1 = getColor(R.color.green)
        val pColor2 = getColor(R.color.yellow)
        val pColor3 = getColor(R.color.orange)
        val pColor4 = getColor(R.color.dark_orange)


        for (i in 0..15) {
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                mainList.add(
                    SongsItem(
                        listOf<ColorItem>(
                            ColorItem(pColor0, false, R.drawable.ic_mic, 0, false),
                            ColorItem(pColor1, false, null, R.raw.aks1, false),
                            ColorItem(pColor2, false, null, R.raw.aks2, false),
                            ColorItem(pColor3, false, null, R.raw.aks3, false),
                            ColorItem(pColor4, false, null, R.raw.aks4, false)
                        )
                    )
                )
            } else if (i == 4 || i == 5 || i == 6 || i == 7) {
                mainList.add(
                    SongsItem(
                        listOf<ColorItem>(
                            ColorItem(pColor0, false, R.drawable.ic_star, 0, false),
                            ColorItem(pColor1, false, null, R.raw.bhat1, false),
                            ColorItem(pColor2, false, null, R.raw.bhat2, false),
                            ColorItem(pColor3, false, null, R.raw.bhat3, false),
                            ColorItem(pColor4, false, null, R.raw.bhat1, false)
                        )
                    )
                )
            } else if (i == 8 || i == 9 || i == 10 || i == 11) {
                mainList.add(
                    SongsItem(
                        listOf<ColorItem>(
                            ColorItem(pColor0, false, R.drawable.ic_tune, 0, false),
                            ColorItem(pColor1, false, null, R.raw.chl1, false),
                            ColorItem(pColor2, false, null, R.raw.chl2, false),
                            ColorItem(pColor3, false, null, R.raw.chl3, false),
                            ColorItem(pColor4, false, null, R.raw.chl4, false)
                        )
                    )
                )
            } else if (i == 12 || i == 13 || i == 14 || i == 15) {
                mainList.add(
                    SongsItem(
                        listOf<ColorItem>(
                            ColorItem(pColor0, false, R.drawable.ic_article, 0, false),
                            ColorItem(pColor1, false, null, R.raw.dfx1, false),
                            ColorItem(pColor2, false, null, R.raw.dfx2, false),
                            ColorItem(pColor3, false, null, R.raw.dfx3, false),
                            ColorItem(pColor4, false, null, R.raw.dfx1, false)
                        )
                    )
                )
            }
        }
    }

    private fun setAdapter() {
        val layoutManager = GridLayoutManager(this, 4)
        courseRV.layoutManager = layoutManager
        // on below line we are initializing our adapter
        courseRVAdapter =
            CourseRVAdapter(mainList as ArrayList<SongsItem>, this, object : ItemSelect {
                override fun getValues(selRow: Int, pageNum: Int) {
                    for (i in mainList[selRow].list.indices) {
                        mainList[selRow].list[i].isSelected = i == pageNum
                        println("onPageSelected calls one" + "position $selRow " + mainList[selRow].list[i])
                    }
                    println(mainList)

                }

            })

        // on below line we are setting
        // adapter to our recycler view.
        courseRV.adapter = courseRVAdapter
    }

    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }

}


