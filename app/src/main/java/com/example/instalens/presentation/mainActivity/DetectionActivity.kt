package com.example.instalens

import android.graphics.RectF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instalens.domain.model.Detection
import com.example.instalens.presentation.common.DetectionBoxView

class DetectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detection)

        // Find the custom view by its ID
        val detectionBoxView: DetectionBoxView = findViewById(R.id.detectionBoxView)

        // Create a sample detection object
        val detection = Detection(
            boundingBox = RectF(100f, 100f, 200f, 200f),
            detectedObjectName = "Object",
            confidenceScore = 0.9f,
            tensorImageHeight = 1080,
            tensorImageWidth = 1920
        )

        // Set the detection data for the custom view
        detectionBoxView.setDetection(detection)
    }
}