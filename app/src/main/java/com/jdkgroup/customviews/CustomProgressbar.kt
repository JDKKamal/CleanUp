package com.jdkgroup.customviews

import android.app.Dialog
import android.content.Context

import com.jdkgroup.cleanup.R

class CustomProgressbar(private val context: Context) {
    private val dialog: Dialog?

    init {
        dialog = Dialog(context, R.style.CircularProgressTransparent)
    }

    fun show(isCancelable: Boolean) {
        dialog!!.setCancelable(isCancelable!!)
        dialog.setContentView(R.layout.custom_progressbar)
        dialog.show()
    }

    val isShowing: Boolean
        get() = dialog!!.isShowing

    fun hide() {
        if (dialog != null) {
            dialog.cancel()
            dialog.dismiss()
        }
    }
}
