package com.example.instalens.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.example.instalens.R

class PrimaryButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val button: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.primary_button, this, true)
        button = findViewById(R.id.primaryButton)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PrimaryButtonView,
            0, 0
        ).apply {
            try {
                val text = getString(R.styleable.PrimaryButtonView_text)
                button.text = text
            } finally {
                recycle()
            }
        }
    }

    fun setText(text: String) {
        button.text = text
    }

    fun setOnClickListener(onClick: () -> Unit) {
        button.setOnClickListener { onClick() }
    }
}