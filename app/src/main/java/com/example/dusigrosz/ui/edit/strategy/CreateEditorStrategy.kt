package com.example.dusigrosz.ui.edit.strategy

import android.view.View
import com.example.dusigrosz.entities.Person
import com.example.dusigrosz.ui.edit.EditFragment
import com.example.dusigrosz.ui.edit.EditViewModel

class CreateEditorStrategy(view: View, fragment: EditFragment, viewModel: EditViewModel) : EditorStrategy(view, fragment, viewModel) {

    override fun initForm() {
        fragment.personNameInput.setText("")
        fragment.personDebtInput.setText("0")
    }

    override fun clearForm() {
        initForm()
    }

    override fun savePerson() {
        val newName: String = fragment.personNameInput.text.toString()
        val newDebt: Double = fragment.personDebtInput.text.toString().toDouble()

        viewModel.addPerson(Person(newName, newDebt))
    }
}
