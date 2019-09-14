package com.example.portfolio_alert

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log.d
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.new_stock_form.*

class FireMissilesDialogFragment(private val mainActivity: MainActivity) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.new_stock_form, null)

            builder.setView(view)
                .setPositiveButton("Submit") { dialog, id -> mainActivity.createNewStock(
                    view.findViewById<EditText>(R.id.stock_name_textfield).text.toString(),
                    view.findViewById<EditText>(R.id.stock_symbol_textfield).text.toString(),
                    view.findViewById<EditText>(R.id.stock_nominal_textfield).text.toString().toDouble()) }
                .setNegativeButton("Cancel") { _, _ -> dialog?.cancel() }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}