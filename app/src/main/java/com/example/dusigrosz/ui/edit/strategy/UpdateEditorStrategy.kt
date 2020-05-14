package com.example.dusigrosz.ui.edit.strategy

import android.app.AlertDialog
import android.view.View
import com.example.dusigrosz.R
import com.example.dusigrosz.ui.edit.EditFragment
import com.example.dusigrosz.ui.edit.EditViewModel
import com.example.dusigrosz.entities.Person

class UpdateEditorStrategy(view: View, fragment: EditFragment, viewModel: EditViewModel) : EditorStrategy(view, fragment, viewModel) {

    override fun initForm() {
        val person = fragment.activity?.intent?.getSerializableExtra("person") as Person

        fragment.personNameInput.setText(person.name)
        fragment.personDebtInput.setText(person.debt.toString())
    }

    override fun clearForm() {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle(fragment.getString(R.string.form_clear_dialog_title))
        builder.setMessage(fragment.getString(R.string.form_clear_dialog_message))

        builder.setPositiveButton(fragment.getString(R.string.form_clear_dialog_positive_text)){ _, _ ->
            initForm()
        }

        builder.setNegativeButton(fragment.getString(R.string.form_clear_dialog_negative_text)) { dialog, _ -> dialog.cancel() }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun savePerson() {
        val newName: String = fragment.personNameInput.text.toString()
        val newDebt: Double = fragment.personDebtInput.text.toString().toDouble()

        viewModel.updatePerson(newName, newDebt)
    }
}
