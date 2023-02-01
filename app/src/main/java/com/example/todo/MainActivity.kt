package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todo.database.MyDataBase
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var ViewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(ViewBinding.root)
        selectedItem()
        ViewBinding.bottomnavigation.selectedItemId=R.id.list
        // TODO: when click on the btn add
        ViewBinding.idBtnAdd.setOnClickListener(View.OnClickListener {

                showAddTaskBottomSheet()
            // TODO:  لما يدوس علي البوتن هيفتح بوتم شيت 
        })
    }


    // TODO: this func show the BottomSheet
    private fun showAddTaskBottomSheet() {
        //بنعمل اوبجكت منها
       var addTaskFragment=AddTaskFragment()
        //بياخد مني فرجمنت منجر ونل
        addTaskFragment.show(supportFragmentManager,null)

    }

    //func used at selected item in bottomnav
    private  fun selectedItem()
    {
        ViewBinding.bottomnavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.list -> {
                    showfragment(ListFragment())


                }
                R.id.setting -> {
                    showfragment(SetteingFragment())
                }


            }
            return@setOnItemSelectedListener true

        }


    }


    //run the fragment
    private  fun showfragment(Fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            //framelayou > the place show on the fragment
            .replace(R.id.framelayout_content,Fragment)
            .commit()


    }


}