package com.example.dusigrosz.ui.edit.strategy

import android.view.View
import com.example.dusigrosz.ui.edit.EditFragment
import com.example.dusigrosz.ui.main.Person

class UpdateEditorStrategy(view: View, fragment: EditFragment) : EditorStrategy(view, fragment) {

    override fun initForm() {
        val person = fragment.activity?.intent?.getSerializableExtra("person") as Person

        fragment.personNameInput.setText(person.name)
        fragment.personDebtInput.setText(person.debt.toString())
    }

    override fun savePerson() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
