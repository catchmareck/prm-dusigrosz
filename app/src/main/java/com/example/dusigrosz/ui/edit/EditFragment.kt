package com.example.dusigrosz.ui.edit

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.dusigrosz.R
import com.example.dusigrosz.ui.PersonAction
import com.example.dusigrosz.ui.edit.strategy.CreateEditorStrategy
import com.example.dusigrosz.ui.edit.strategy.EditorStrategy
import com.example.dusigrosz.ui.edit.strategy.UpdateEditorStrategy
import com.example.dusigrosz.entities.Person

class EditFragment : Fragment() {

    lateinit var personNameInput: EditText
    lateinit var personDebtInput: EditText
    lateinit var buttonSave: Button
    lateinit var buttonClear: Button
    lateinit var buttonSimulate: Button
    lateinit var buttonNotify: Button

    companion object {
        fun newInstance() = EditFragment()
    }

    private lateinit var viewModel: EditViewModel

    private lateinit var strategy: EditorStrategy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personNameInput = view.findViewById(R.id.person_name_input)
        personDebtInput = view.findViewById(R.id.person_debt_input)
        buttonSave = view.findViewById(R.id.button_save_person)
        buttonClear = view.findViewById(R.id.button_clear_text)
        buttonSimulate = view.findViewById(R.id.button_simulate)
        buttonNotify = view.findViewById(R.id.button_notify)

        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        personNameInput.hint = getString(R.string.person_name_input_placeholder)
        personDebtInput.hint = getString(R.string.person_debt_input_placeholder)
        buttonSave.text = getString(R.string.button_save_person_text)
        buttonSimulate.text = getString(R.string.button_simulate_text)
        buttonClear.text = getString(R.string.button_clear_text)
        buttonNotify.text = getString(R.string.button_notify_text)

        val action: PersonAction = activity?.intent?.getSerializableExtra("action") as PersonAction
        setStrategy(action, view)
        strategy.initForm()

        if (action == PersonAction.EDIT_PERSON) {
            viewModel.person = activity?.intent?.getSerializableExtra("person") as Person
        } else {
            buttonSimulate.isEnabled = false
            buttonNotify.isEnabled = false
        }

        bindSaveButtonEvents()
        bindClearButtonEvents()
        bindNotifyButtonEvents()
    }

    private fun setStrategy(action: PersonAction, view: View) {
        strategy = if (action == PersonAction.CREATE_PERSON) CreateEditorStrategy(view, this, viewModel) else UpdateEditorStrategy(view, this, viewModel)
    }

    private fun bindSaveButtonEvents() {
        buttonSave.setOnClickListener {
            strategy.savePerson()
            activity!!.finish()
        }
    }

    private fun bindClearButtonEvents() {
        buttonClear.setOnClickListener {
            strategy.clearForm()
        }
    }

    private fun bindNotifyButtonEvents() {
        buttonNotify.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.person_notify_message, viewModel.person.name, viewModel.person.debt.toString()))
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
