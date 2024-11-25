# instaLens

## Challenge 1: Limited Support for Jetpack Compose

### Solution  
To address the limited support for Jetpack Compose, all Jetpack Compose components have been migrated to XML-based layouts. This ensures better compatibility and resolves the issue effectively.

---

### Changes Made  

#### Updated Files  
**Path:** `instaLens\app\src\main\java\com\example\instalens\presentation`

- **Common Components:**  
  - `CustomImageButton.kt`  
  - `DetectionBoxView.kt`  
  - `DrawDetectionBox.kt`  
  - `PrimaryButtonView.kt`  
  - `SecondaryButtonView.kt`  

- **Home Components:**  
  - `components`  
    - `CameraOverlayView.kt`  
    - `CameraPreview.kt`  
    - `ObjectCounterView.kt`  
    - `RequestPermissions.kt`  
    - `ThresholdLevelSliderView.kt`  
  - `HomeViewModel.kt`  

- **Main Activity Components:**  
  - `DetectionActivity.kt`  
  - `MainActivity.kt`  
  - `MainViewModel.kt`  

#### Newly Added Files  
**Path:** `instaLens\app\src\main\res\layouts`  
- `activity_detection.xml`  
- `activity_main.xml`  
- `camera_preview.xml`  
- `image_button.xml`  
- `object_counter.xml`  
- `primary_button.xml`  
- `secondary_button.xml`  
- `threshold_level_slider.xml`  

**Path:** `instaLens\app\src\main\res\values`  
- `attrs.xml` (newly added)  
- `colors.xml`  
- `strings.xml`  
- `themes.xml`  
- `ic_launcher_background.xml`  
- `splash.xml`  

#### Updated File  
- `AndroidManifest.xml`  

---

### Summary  
The migration from Jetpack Compose to XML-based components ensures improved compatibility, seamless integration, and an enhanced development experience. Key updates include the addition of new XML layout files, updates to the `AndroidManifest.xml`, and restructuring of relevant Kotlin files to align with the XML approach.

This restructuring enhances the maintainability, usability, and overall functionality of the `instaLens` application.

---

### Future Tasks  
While the migration has been completed, the following tasks remain to ensure a polished application:  
- Resolve potential integration bugs.  ### need to fix the integratation (The application is not yet ready to RUN)
- Update dependencies where required.  
- Migrate the **Navbar** and **Onboarding screens** to XML-based layouts.  
- Implement UI improvements based on client feedback.  

These steps will ensure a seamless user experience and better alignment with project goals.
