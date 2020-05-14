package com.example.dusigrosz.ui.main

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.dusigrosz.EditActivity
import com.example.dusigrosz.R
import com.example.dusigrosz.ui.PersonAction
import com.example.dusigrosz.entities.Person
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    internal lateinit var peopleList: ListView
    internal lateinit var buttonAddPerson: Button
    internal lateinit var debtSumText: TextView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var arrayAdapter: PeopleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        peopleList = view.findViewById(R.id.people_list)

        buttonAddPerson = view.findViewById(R.id.button_add_person)
        buttonAddPerson.text = getString(R.string.button_add_person_text)

        debtSumText = view.findViewById(R.id.debt_sum_text)

        bindListViewEvents(view)
        bindAddPersonButtonEvents(view)

        viewModel.getPeople().observe(
            viewLifecycleOwner,
            Observer {
                if(::arrayAdapter.isInitialized)
                    arrayAdapter.notifyDataSetChanged()

                setDebtSumText()
                initializeListView(view)
            }
        )
    }

    private fun initializeListView(view: View) {
        arrayAdapter = PeopleAdapter(view.context, viewModel.getPeople().value as ArrayList<Person>)
        peopleList.adapter = arrayAdapter
    }

    private fun setDebtSumText() {
        debtSumText.text = getString(R.string.debt_sum_label_text, viewModel.getDebtSum().toString(), getString(R.string.currency_label))
    }

    private fun bindListViewEvents(view: View) {
        peopleList.setOnItemClickListener { adapterView: AdapterView<*>, _: View, position: Int, _: Long ->
            val intent = Intent(view.context, EditActivity::class.java)
            intent.putExtra("action", PersonAction.EDIT_PERSON)
            intent.putExtra("person", adapterView.getItemAtPosition(position) as Person)
            startActivity(intent)
        }

        peopleList.setOnItemLongClickListener { adapterView: AdapterView<*>, _: View, position: Int, _: Long ->

            val person: Person = adapterView.getItemAtPosition(position) as Person
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.person_delete_dialog_title))
            builder.setMessage(getString(R.string.person_delete_dialog_message, person.name))

            builder.setPositiveButton(getString(R.string.person_delete_dialog_positive_text)){_, _ ->
                viewModel.deletePerson(person)
            }

            builder.setNegativeButton(getString(R.string.person_delete_dialog_negative_text)) {dialog, _ -> dialog.cancel() }

            val dialog: AlertDialog = builder.create()
            dialog.show()

            true
        }
    }

    private fun bindAddPersonButtonEvents(view: View) {

        buttonAddPerson.setOnClickListener {
            val intent = Intent(view.context, EditActivity::class.java)
            intent.putExtra("action", PersonAction.CREATE_PERSON)
            startActivity(intent)
        }
    }
}
