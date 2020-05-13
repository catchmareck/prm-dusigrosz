package com.example.dusigrosz.ui.edit.strategy

import android.view.View
import com.example.dusigrosz.ui.edit.EditFragment
import com.example.dusigrosz.ui.edit.EditViewModel

abstract class EditorStrategy(val view: View, val fragment: EditFragment, val viewModel: EditViewModel) {

    abstract fun initForm()

    abstract fun savePerson()
}
