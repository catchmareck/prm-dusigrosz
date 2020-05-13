package com.example.dusigrosz.ui.edit.strategy

import android.view.View
import com.example.dusigrosz.ui.edit.EditFragment
import com.example.dusigrosz.ui.edit.EditViewModel
import com.example.dusigrosz.ui.main.Person

class UpdateEditorStrategy(view: View, fragment: EditFragment, viewModel: EditViewModel) : EditorStrategy(view, fragment, viewModel) {

    override fun initForm() {
        val person = fragment.activity?.intent?.getSerializableExtra("person") as Person

        fragment.personNameInput.setText(person.name)
        fragment.personDebtInput.setText(person.debt.toString())
    }

    override fun savePerson() {
        val newName: String = fragment.personNameInput.text.toString()
        val newDebt: Double = fragment.personDebtInput.text.toString().toDouble()

        viewModel.updatePerson(newName, newDebt)
    }
}
