package com.example.dusigrosz.ui.edit.strategy

import android.view.View
import com.example.dusigrosz.ui.edit.EditFragment

abstract class EditorStrategy(val view: View, val fragment: EditFragment) {

    abstract fun initForm()

    abstract fun savePerson()
}
