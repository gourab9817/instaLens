package com.example.instalens.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.instalens.R

class CustomImageButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.image_button, this, true)
        imageView = findViewById(R.id.imageButton)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomImageButton,
            0, 0
        ).apply {
            try {
                val drawableResId = getResourceId(R.styleable.CustomImageButton_drawableResource, 0)
                val contentDescriptionResId = getResourceId(R.styleable.CustomImageButton_contentDescriptionResource, 0)
                if (drawableResId != 0) {
                    imageView.setImageResource(drawableResId)
                }
                if (contentDescriptionResId != 0) {
                    imageView.contentDescription = context.getString(contentDescriptionResId)
                }
            } finally {
                recycle()
            }
        }
    }

    fun setImageResource(drawableResId: Int) {
        imageView.setImageResource(drawableResId)
    }

    fun setContentDescription(contentDescriptionResId: Int) {
        imageView.contentDescription = context.getString(contentDescriptionResId)
    }
}