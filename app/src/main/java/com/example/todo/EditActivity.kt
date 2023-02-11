package com.example.todo


import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.todo.database.MyDataBase
import com.example.todo.database.model.Task
import com.example.todo.databinding.ActivityEditBinding
import java.text.SimpleDateFormat
import java.util.Date

class EditActivity : AppCompatActivity() {
    private lateinit var task:Task
    lateinit var ViewBinding:ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewBinding=ActivityEditBinding.inflate(layoutInflater)
        setContentView(ViewBinding.root)
        //recive data
         task = ((intent.getSerializableExtra(constant.Task)as? Task)!!)
        showData(task)
        ViewBinding.editBtn.setOnClickListener(View.OnClickListener {
            updateTodo()
        })
    }
    fun valdit():Boolean{
        var valid=true
        val title =ViewBinding.containerTitle.editText?.text.toString()
        val desc=ViewBinding.containerDesc.editText?.text.toString()

        if (title.isNullOrBlank())
        {
            ViewBinding.containerTitle.error=" please enter the title"
            valid=false
        }

        else {
                ViewBinding.containerTitle.error = null
        }
        if (desc.isNullOrBlank())
        {

            ViewBinding.containerDesc.error="please enter the description"
            valid=false
        }
        else {
          ViewBinding.containerDesc.error = null
        }
        return valid
    }

    private fun updateTodo() {
        if (valdit()==false) {
         return;
        }
        task.title=ViewBinding.containerTitle.editText?.text.toString()
        task.description=ViewBinding.containerDesc.editText?.text.toString()
        MyDataBase.getDataBase(this).getDao().update(task)
        showInsertDialog()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun showInsertDialog() {
        val alertDialogBuilder= AlertDialog.Builder(this)
            .setMessage("Update successfully")
            .setPositiveButton(R.string.ok
            ) { dialog, which ->
                dialog.dismiss()
            }
        alertDialogBuilder.show()
    }

    private fun showData(task: Task) {
        ViewBinding.containerTitle.editText?.setText(task.title)
        ViewBinding.containerDesc.editText?.setText(task.description)
        val date=converLongToTime(task.date)
        ViewBinding.date.text=date
    }

private fun converLongToTime(date: Long?): String {
        val date=Date(date!!)
        val format=SimpleDateFormat("yyyy/MM//dd")
        return format.format(date)
    }
}