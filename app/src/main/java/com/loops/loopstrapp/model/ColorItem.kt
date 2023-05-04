package com.loops.loopstrapp.model

data class ColorItem(
    var itemColor: Int = 0,
    var isSelected: Boolean = false,
    var songImg: Int?,
    var tune: Int,
    var isplaying: Boolean = false
)