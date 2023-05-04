package com.loops.loopstrapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.loops.loopstrapp.R
import com.loops.loopstrapp.`interface`.ItemSelect
import com.loops.loopstrapp.model.SongsItem

// on below line we are creating
// a course rv adapter class.
class CourseRVAdapter(
    // on below line we are passing variables
    // as course list and context
    private val mainList: MutableList<SongsItem>,
    private val context: Context,
    private val itemSelect: ItemSelect
) : RecyclerView.Adapter<CourseRVAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.viewPagerAdapter = ViewPagerAdapter(context, mainList[holder.adapterPosition].list)
        holder.viewpager.adapter = holder.viewPagerAdapter

        holder.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(pos: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(pos: Int) {
                itemSelect.getValues(holder.adapterPosition, pos)
            }
        })
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewpager: ViewPager = itemView.findViewById(R.id.viewpager)
        lateinit var viewPagerAdapter: ViewPagerAdapter
    }
}