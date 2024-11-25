package com.example.instalens.presentation.home.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.instalens.R

class ObjectCounterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val objectIcon: ImageView
    private val objectCountText: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.object_counter, this, true)
        objectIcon = findViewById(R.id.objectIcon)
        objectCountText = findViewById(R.id.objectCountText)
    }

    fun setObjectCount(count: Int) {
        objectCountText.text = count.toString()
    }
}