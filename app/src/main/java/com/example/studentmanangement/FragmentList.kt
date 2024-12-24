package com.example.studentmanangement

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentList : Fragment() {

    private lateinit var studentAdapter: ArrayAdapter<String>
    private val students = mutableListOf<Pair<String, String>>() // List of Pair(MSSV, Họ tên)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val listView = view.findViewById<ListView>(R.id.listViewStudents)
        studentAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            students.map { "${it.first} - ${it.second}" }
        )
        listView.adapter = studentAdapter

        // Context Menu
        registerForContextMenu(listView)
        listView.setOnItemClickListener { _, _, position, _ ->
            // Edit action via ContextMenu
            editStudent(position)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            findNavController().navigate(R.id.action_to_addEdit)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editStudent(position: Int) {
        val student = students[position]
        val bundle = Bundle().apply {
            putString("mssv", student.first)
            putString("name", student.second)
        }
        findNavController().navigate(R.id.action_to_addEdit, bundle)
    }

    // Context Menu
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.menu_edit -> editStudent(info.position)
            R.id.menu_remove -> {
                students.removeAt(info.position)
                studentAdapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }
}
