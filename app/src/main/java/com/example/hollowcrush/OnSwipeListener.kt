package com.example.hollowcrush

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

open class OnSwipeListener(context: Context?) : OnTouchListener {
    var gestureDetector: GestureDetector
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(motionEvent)
    }

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    private inner class GestureListener : SimpleOnGestureListener() {
        val SWIPE_THRESOLD = 100
        val SWIPE_VELOCITY_THRESOLD = 100

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            val yDiff = e2.y - e1.y
            val xDiff = e2.x - e1.x
            if (Math.abs(xDiff) > Math.abs(yDiff)) { // It means that the user is either going to left or right direction
                if (Math.abs(xDiff) > SWIPE_THRESOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESOLD) {
                    if (xDiff > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                    result = true
                }
            } else if (Math.abs(yDiff) > SWIPE_THRESOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESOLD) {
                if (yDiff > 0) {
                    onSwipeBottom()
                } else {
                    onSwipeTop()
                }
                result = true
            }
            return result
        }
    }

    open fun onSwipeTop() {}
    open fun onSwipeBottom() {}
    open fun onSwipeLeft() {}
    open fun onSwipeRight() {}
}