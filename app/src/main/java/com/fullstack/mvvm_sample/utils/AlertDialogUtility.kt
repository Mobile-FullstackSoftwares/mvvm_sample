package com.fullstack.mvvm_sample.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.fullstack.mvvm_sample.R

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