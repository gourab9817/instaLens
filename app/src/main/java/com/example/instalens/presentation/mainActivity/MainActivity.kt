package com.example.instalens.presentation.mainActivity

import android.content.pm.PackageManager
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.instalens.R
import com.example.instalens.domain.model.Detection
import com.example.instalens.presentation.common.CustomImageButton
import com.example.instalens.presentation.common.DetectionBoxView
import com.example.instalens.presentation.common.PrimaryButtonView
import com.example.instalens.presentation.common.SecondaryButtonView
import com.example.instalens.presentation.home.components.CameraOverlayView
import com.example.instalens.presentation.home.components.CameraPreviewView
import com.example.instalens.presentation.home.components.ObjectCounterView
import com.example.instalens.presentation.home.components.PermissionUtils
import com.example.instalens.presentation.home.components.ThresholdLevelSliderView
import com.example.instalens.presentation.navgraph.NavGraph
import com.example.instalens.ui.theme.InstaLensTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String? = MainActivity::class.simpleName
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Initialize Splash-Screen API & keep it visible until a condition is met
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.redirectFlagState
            }
        }

        setContentView(R.layout.activity_main)

        // Check and request permissions
        if (PermissionUtils.allPermissionsGranted(this)) {
            setupCamera()
        } else {
            PermissionUtils.requestPermissions(this)
        }

        // Configure CustomImageButton
        val customImageButton: CustomImageButton = findViewById(R.id.customImageButton)
        customImageButton.setImageResource(R.drawable.ic_capture)
        customImageButton.setContentDescription(R.string.capture_button_description)

        // Configure PrimaryButtonView
        val primaryButtonView: PrimaryButtonView = findViewById(R.id.primaryButtonView)
        primaryButtonView.setText("Click Me")
        primaryButtonView.setOnClickListener {
            Log.d(TAG, "PrimaryButtonView clicked!")
        }

        // Configure SecondaryButtonView
        val secondaryButtonView: SecondaryButtonView = findViewById(R.id.secondaryButtonView)
        secondaryButtonView.setText("Click Me Too")
        secondaryButtonView.setOnClickListener {
            Log.d(TAG, "SecondaryButtonView clicked!")
        }

        // Set up the object counter view
        val objectCounterView: ObjectCounterView = findViewById(R.id.objectCounterView)
        objectCounterView.setObjectCount(5) // Example count

        // Set up the threshold level slider view
        val thresholdLevelSliderView: ThresholdLevelSliderView =
            findViewById(R.id.thresholdLevelSliderView)
        thresholdLevelSliderView.setThresholdValue(0.5f) // Example initial value
        thresholdLevelSliderView.onThresholdValueChanged = { value ->
            Log.d(TAG, "Threshold level changed: $value")
            // Handle threshold value change
        }

        // Compose content
        setContent {
            InstaLensTheme {
                Box(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                ) {
                    val startDestination = viewModel.startDestination
                    Log.d(TAG, "setContent() called with startDestination = $startDestination")
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }

    private fun setupCamera() {
        // Set up the camera preview
        val cameraPreviewView: CameraPreviewView = findViewById(R.id.cameraPreviewView)
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val cameraController = LifecycleCameraController(this)
            cameraPreviewView.bindToLifecycle(this, cameraController)

            cameraPreviewView.setOnPreviewSizeChangedListener { size ->
                Log.d(TAG, "Preview size changed: $size")
            }
        }, ContextCompat.getMainExecutor(this))

        // Configure CameraOverlayView
        val cameraOverlayView: CameraOverlayView = findViewById(R.id.cameraOverlayView)
        val overlayDetections = listOf(
            Detection(
                boundingBox = RectF(100f, 100f, 200f, 200f),
                detectedObjectName = "Object 1",
                confidenceScore = 0.9f,
                tensorImageHeight = 1080,
                tensorImageWidth = 1920
            ),
            Detection(
                boundingBox = RectF(300f, 300f, 400f, 400f),
                detectedObjectName = "Object 2",
                confidenceScore = 0.85f,
                tensorImageHeight = 1080,
                tensorImageWidth = 1920
            )
        )
        cameraOverlayView.setDetections(overlayDetections)

        // Configure DetectionBoxView
        val detectionBoxView: DetectionBoxView = findViewById(R.id.detectionBoxView)
        val detectionBoxSample = Detection(
            boundingBox = RectF(100f, 100f, 200f, 200f),
            detectedObjectName = "Single Object",
            confidenceScore = 0.95f,
            tensorImageHeight = 1080,
            tensorImageWidth = 1920
        )
        detectionBoxView.setDetection(detectionBoxSample)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionUtils.REQUEST_CODE_PERMISSIONS) {
            if (PermissionUtils.allPermissionsGranted(this)) {
                setupCamera()
            } else {
                Log.e(TAG, "Permissions not granted by the user.")
            }
        }
    }
}
