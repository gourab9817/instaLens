package com.example.instalens.presentation.home.components

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.instalens.R

class CameraPreviewView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val previewView: PreviewView

    init {
        inflate(context, R.layout.camera_preview, this)
        previewView = findViewById(R.id.previewView)
    }

    fun bindToLifecycle(lifecycleOwner: LifecycleOwner, controller: LifecycleCameraController) {
        previewView.controller = controller
        controller.bindToLifecycle(lifecycleOwner)
    }

    fun setOnPreviewSizeChangedListener(listener: (IntSize) -> Unit) {
        previewView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val size = IntSize(previewView.width, previewView.height)
            listener(size)
        }
    }
}