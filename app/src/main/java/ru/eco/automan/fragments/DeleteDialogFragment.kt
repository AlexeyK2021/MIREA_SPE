package ru.eco.automan.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import ru.eco.automan.R
import ru.eco.automan.listeners.OnDialogListener

class DeleteDialogFragment(
    private val titleId: Int,
    private val messageId: Int,
    private val onDialogListener: OnDialogListener,
    private val context: Context
) {
    init {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleId)
        builder.setMessage(messageId)
        builder.setNegativeButton(R.string.no) { dialog, id ->
            onDialogListener.onNegativeButtonClicked()
            dialog.cancel()
        }
        builder.setPositiveButton(R.string.yes) { dialog, id ->
            onDialogListener.onPositiveButtonClicked()
            dialog.cancel()
        }
        val alert = builder.create()
//        alert.getButton(DialogInterface.BUTTON_NEGATIVE)
//            ?.setTextColor(context.resources.getColor(R.color.dismiss, context.theme))
//        alert.getButton(DialogInterface.BUTTON_POSITIVE)
//            ?.setTextColor(context.resources.getColor(R.color.delete, context.theme))
        alert.show()
    }

}