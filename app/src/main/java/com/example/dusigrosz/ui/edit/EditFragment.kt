package com.example.dusigrosz.ui.edit

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

class EditFragment : Fragment() {

    lateinit var personNameInput: EditText
    lateinit var personDebtInput: EditText
    lateinit var buttonSave: Button
    lateinit var buttonSimulate: Button

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
        buttonSimulate = view.findViewById(R.id.button_simulate)

        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        personNameInput.hint = getString(R.string.person_name_input_placeholder)
        personDebtInput.hint = getString(R.string.person_debt_input_placeholder)
        buttonSave.text = getString(R.string.button_save_person_text)
        buttonSimulate.text = getString(R.string.button_simulate_text)

        val action: PersonAction = activity?.intent?.getSerializableExtra("action") as PersonAction
        setStrategy(action, view)
        strategy.initForm()

        bindSaveButtonEvents()
    }

    private fun setStrategy(action: PersonAction, view: View) {
        strategy = if (action == PersonAction.CREATE_PERSON) CreateEditorStrategy(view, this) else UpdateEditorStrategy(view, this)
    }

    private fun bindSaveButtonEvents() {
        buttonSave.setOnClickListener {
            strategy.savePerson()
        }
    }
}
