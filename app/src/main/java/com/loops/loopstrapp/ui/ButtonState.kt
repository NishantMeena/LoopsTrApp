package com.loops.loopstrapp.ui

sealed class ButtonState {
    object Clicked : ButtonState()   // when button is clicked for downloading
    object Loading : ButtonState()   // when downloading is in progress
    object Completed : ButtonState() // when downloading is finished
}