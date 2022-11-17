package com.fullstack.mvvm_sample.utils

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


fun checkCameraPermission(context: Context?, onPermissionGranted : (()-> Unit)? = null , onPermissionDenied : (()-> Unit)? = null){
    context?.let {
        Dexter.withContext(context)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                    onPermissionGranted?.invoke()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {
                    if (permissionDeniedResponse.isPermanentlyDenied){
                        onPermissionDenied?.invoke()
                    }
                }

            }).check()
    }
}
fun openPermissionSettings(context: Context?){
    context?.let {
        AlertDialog.Builder(it)
            .setMessage("Camera Permission are required to use BarCode")
            .setNegativeButton("Cancel", null)
            .setPositiveButton(
                "Settings"
            ) { _: DialogInterface?, _: Int ->
                val myAppSettings = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + context.packageName)
                )
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
                myAppSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(myAppSettings)
            }.show()
    }
}