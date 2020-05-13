package com.example.dusigrosz.ui.edit.strategy

import android.view.View
import com.example.dusigrosz.ui.edit.EditFragment
import com.example.dusigrosz.ui.edit.EditViewModel

class CreateEditorStrategy(view: View, fragment: EditFragment, viewModel: EditViewModel) : EditorStrategy(view, fragment, viewModel) {

    override fun initForm() {
        fragment.personNameInput.setText("")
        fragment.personDebtInput.setText("")
    }

    override fun savePerson() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
