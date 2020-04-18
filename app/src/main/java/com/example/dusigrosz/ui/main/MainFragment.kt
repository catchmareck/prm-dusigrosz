package com.example.dusigrosz.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.dusigrosz.R
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    internal lateinit var peopleList: ListView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        peopleList = view.findViewById(R.id.people_list)
        val arrayAdapter = PeopleAdapter(view.context, viewModel.getPeople() as ArrayList<Person>)
        peopleList.adapter = arrayAdapter
    }
}
