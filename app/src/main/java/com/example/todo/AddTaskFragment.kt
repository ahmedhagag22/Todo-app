package com.example.todo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.database.MyDataBase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.Year
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment()
{

    lateinit var viewBinding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding= FragmentAddTaskBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        viewBinding.date.setOnClickListener(View.OnClickListener {

            showDatepicker()
        })

       viewBinding.btntask.setOnClickListener(View.OnClickListener {
           addTask()
        })


    }

    private fun addTask() {
        val title =viewBinding.taskTitle.editText?.text.toString()
        val desc=viewBinding.taskDesc.editText?.text.toString()

        if (title.isNotBlank())
        {
            viewBinding.taskTitle.error=" please enter the title"
        }
        else {
            viewBinding.taskTitle.error = null
        }
        if (desc.isNotBlank())
        {
            viewBinding.taskDesc.error="please enter the description"
        }
        else {
            viewBinding.taskDesc.error = null
        }
    }

    fun setData(){
        viewBinding.dateTask.setText(""+ currentDate.get(Calendar.DAY_OF_MONTH)
                +"/ "+ currentDate.get(Calendar.MONTH)+1+"/"+currentDate.get(Calendar.YEAR)).toString()
    }

    var currentDate=Calendar.getInstance()
    private fun showDatepicker() {
       val datePickerDialog=DatePickerDialog(requireActivity(),
         {
                view, year, month, dayOfMonth ->
             currentDate.set(Calendar.YEAR,year)
             currentDate.set(Calendar.MONTH,month)
             currentDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                setData()

         },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
        currentDate.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

}