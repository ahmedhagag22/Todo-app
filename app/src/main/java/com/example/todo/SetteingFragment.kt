package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.todo.databinding.FragmentSetteingBinding

class SetteingFragment : Fragment() {

    lateinit var ViewBinding: FragmentSetteingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ViewBinding = FragmentSetteingBinding.inflate(layoutInflater, container, false)
        return ViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mode=resources.getStringArray(R.array.mode)
        val languages = resources.getStringArray(R.array.Languages)
        val spinnerSetting = ViewBinding.spinnerSetting
        if (spinnerSetting != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, languages
            )
            spinnerSetting.adapter = adapter
            spinnerSetting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                )
                {
                    Toast.makeText(requireActivity(),
                        getString(R.string.selected) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
        val spinnerMode=ViewBinding.spinnerMode
        if (spinnerMode!=null)
        {
            val adapter=ArrayAdapter(
                requireContext(),android.R.layout.simple_spinner_item,mode)
            spinnerMode.adapter=adapter
            spinnerMode.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(requireActivity(),
                        getString(R.string.selected) + " " +
                                "" + mode[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

}