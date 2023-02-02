package com.example.todo

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
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

    var onDismissListener:OnDismissListener?=null
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss();
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

    // TODO: valdit the edit txt is not null or blank
        fun valdit():Boolean{
            var valid=true
            val title =viewBinding.taskTitle.editText?.text.toString()
            val desc=viewBinding.taskDesc.editText?.text.toString()

            if (title.isNullOrBlank())
            {
                viewBinding.taskTitle.error=" please enter the title"
            valid=false
            }

            else {
                viewBinding.taskTitle.error = null
            }
            if (desc.isNullOrBlank())
            {

                viewBinding.taskDesc.error="please enter the description"
            valid=false
            }
            else {
                viewBinding.taskDesc.error = null
            }
        return valid
        }
     fun addTask() {
        if (valdit()==false){
            return;
        }

         val title =viewBinding.taskTitle.editText?.text.toString()
         val desc=viewBinding.taskDesc.editText?.text.toString()
        MyDataBase.getDataBase(requireActivity()).getDao()
            .insert(Task(
                title = title,
                description = desc,
                data = currentDate.timeInMillis
            ))
         showInsertDialog()
     }

         fun showInsertDialog()
         {
             val alertDialogBuilder=AlertDialog.Builder(activity)
                 .setMessage("insert successfully")
                 .setPositiveButton(R.string.ok
                 ) { dialog, which ->
                     dialog.dismiss()
                     dismiss()
                 }
             alertDialogBuilder.show()
         }


    fun setData(){
        viewBinding.dateTask.
        setText(""+ currentDate.get(Calendar.DAY_OF_MONTH)
                +"/ "+ currentDate.get(Calendar.MONTH)
                + "/"+currentDate.get(Calendar.YEAR)).toString()
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