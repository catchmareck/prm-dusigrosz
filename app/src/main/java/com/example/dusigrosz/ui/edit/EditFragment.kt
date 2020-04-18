package com.example.dusigrosz.ui.edit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dusigrosz.R
import com.example.dusigrosz.ui.main.Person

class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    private lateinit var viewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        val person = activity?.intent?.getSerializableExtra("person") as Person
        view.findViewById<TextView>(R.id.message).text = "${person.name} - ${person.debt}zł"
    }
}
