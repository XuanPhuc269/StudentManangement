package com.example.studentmanangement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentAddEdit : Fragment() {

    private lateinit var editTextMSSV: EditText
    private lateinit var editTextName: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit, container, false)

        editTextMSSV = view.findViewById(R.id.editTextMSSV)
        editTextName = view.findViewById(R.id.editTextName)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)

        val mssv = arguments?.getString("mssv")
        val name = arguments?.getString("name")

        if (mssv != null && name != null) {
            editTextMSSV.setText(mssv)
            editTextName.setText(name)
        }

        buttonSave.setOnClickListener {
            // Add or Update logic
            findNavController().navigate(R.id.action_to_list)
        }
        return view
    }
}
