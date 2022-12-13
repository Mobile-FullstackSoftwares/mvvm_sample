package com.fullstack.mvvm_sample.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.fullstack.mvvm_sample.R
import com.fullstack.mvvm_sample.databinding.CustomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class AlertDialogUtility(
    private val title: String? = null,
    private val message: String? = null,
    private val primaryButtonText: String? = null,
    private val secondaryButtonText: String? = null,
    private val showNegativeButton: Boolean = false,
    private val primaryCallback: (() -> Unit)? = null,
    private val secondaryCallback: (() -> Unit)? = null
) {
    fun showDialog(context: Context?) {
        context?.let {
            val builder = AlertDialog.Builder(context).apply {
                if (!title.isNullOrEmpty()) {
                    setTitle(title)
                }
                if (!message.isNullOrEmpty()) {
                    setMessage(message)
                }
                setPositiveButton(primaryButtonText ?: context.getString(R.string.yes)) { _, _ ->
                    primaryCallback?.invoke()
                }
                if (showNegativeButton) {
                    setNegativeButton(secondaryButtonText ?: context.getString(R.string.no)) { _, _ ->
                        secondaryCallback?.invoke()
                    }
                }
            }.create()
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
    }
}

/**
 * Can be called like Following
 *
customDialog(
    this,
    title = getString(R.string.location_dialog_title),
    message = getString(R.string.location_dialog_detail),
    primaryButtonText = getString(R.string.settings),
    secondaryButtonText = getString(R.string.no_thanks),
    primaryCallback = {
            val myAppSettings = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + this.packageName)
            )
            myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
            myAppSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            this.startActivity(myAppSettings)
        }
    )
 */
fun customDialog(
    context: Context,
    title: String,
    message: String,
    primaryButtonText: String,
    secondaryButtonText: String,
    primaryCallback: (() -> Unit)? = null,
    secondaryCallback: (() -> Unit)? = null,
    isCancellable: Boolean = false
) : AlertDialog {
    val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(context))
    val dialogView = dialogBinding.root
    val builder = AlertDialog.Builder(context)
    val alertDialog = builder.setView(dialogView).create()
    alertDialog.setCanceledOnTouchOutside(isCancellable)
    dialogBinding.apply {
        titleTv.text = title
        descriptionTv.text = message
        primaryBt.text = primaryButtonText
        primaryBt.setOnClickListener {
            primaryCallback?.invoke()
            alertDialog.dismiss()
        }
        secondaryBt.text = secondaryButtonText
        secondaryBt.setOnClickListener {
            secondaryCallback?.invoke()
            alertDialog.dismiss()
        }
    }
    alertDialog.show()
    return alertDialog
}

fun customBottomSheetDialog(context: Context) {
    val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(context))
    val dialog = BottomSheetDialog(context)
    dialog.setContentView(dialogBinding.root)
    //Below line is added to show bottomSheet always in expanded form this sometimes not expand in landscape mode
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()
}