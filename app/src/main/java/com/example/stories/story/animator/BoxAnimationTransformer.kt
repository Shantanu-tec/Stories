package com.example.stories.story.animator

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class BoxAnimationTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val cameraDistance = 8000f  // Adjust this value for a more pronounced effect

        // Set the camera distance for 3D effect
        view.cameraDistance = cameraDistance

        // Determine pivot points for rotation
        view.pivotX = if (position < 0) view.width.toFloat() else 0f
        view.pivotY = view.height * 0.5f

        // Adjust rotation speed and apply rotation
        val rotationY = 70f * position  // Adjust the degree of rotation
        view.rotationY = rotationY

        // Adjust scaling for smoothness
        val scale = 1 - abs(position) * 0.3f  // Scale factor
        view.scaleX = scale
        view.scaleY = scale

        // Adjust alpha to fade out pages
        view.alpha = max(0.3f, 1 - abs(position))
    }
}
