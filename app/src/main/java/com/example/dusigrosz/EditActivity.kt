package com.example.dusigrosz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dusigrosz.ui.edit.EditFragment

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EditFragment.newInstance())
                .commitNow()
        }
    }
}
