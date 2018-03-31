package nervousapps.pwidgetfm_kotlin

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle


class DialogFragmentWidget() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.notification_message)
                .setPositiveButton(R.string.app_name, DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                })
                .setNegativeButton(R.string.notification_message, DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    this.dismiss()
                })
        // Create the AlertDialog object and return it
        return builder.show()
    }
}