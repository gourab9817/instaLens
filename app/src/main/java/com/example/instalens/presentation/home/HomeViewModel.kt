class HomeActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var captureButton: Button
    private lateinit var overlayImage: ImageView
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        previewView = findViewById(R.id.preview_view)
        captureButton = findViewById(R.id.capture_button)
        overlayImage = findViewById(R.id.overlay_image)

        val cameraController = homeViewModel.prepareCameraController(this, CameraFrameAnalyzer())
        previewView.controller = cameraController

        captureButton.setOnClickListener {
            homeViewModel.capturePhoto(
                context = this,
                cameraController = cameraController,
                screenWidth = previewView.width.toFloat(),
                screenHeight = previewView.height.toFloat(),
                detections = emptyList() // Replace with actual detections
            )
        }

        homeViewModel.isImageSavedStateFlow.observe(this) { isSaved ->
            if (isSaved) {
                Toast.makeText(this, "Image saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save image.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
