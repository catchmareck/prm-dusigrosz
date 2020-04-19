package com.example.dusigrosz.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dusigrosz.R

class PeopleAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Person>
): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_person, parent, false)

        val personNameTextView: TextView = rowView.findViewById(R.id.list_item_person_name)
        val personDebtTextView: TextView = rowView.findViewById(R.id.list_item_person_debt)

        val person: Person = getItem(position)
        personNameTextView.text = person.name
        personDebtTextView.text = context.getString(R.string.list_item_debt_text, person.debt.toString(), context.getString(R.string.currency_label))

        return rowView
    }

    override fun getItem(position: Int): Person {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}
