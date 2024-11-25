package com.example.instalens.presentation.home.components

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    const val REQUEST_CODE_PERMISSIONS = 10

    val REQUIRED_PERMISSIONS = mutableListOf(
        Manifest.permission.CAMERA
    ).apply {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }.toTypedArray()

    fun allPermissionsGranted(context: Activity) = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            REQUIRED_PERMISSIONS,
            REQUEST_CODE_PERMISSIONS
        )
    }
}