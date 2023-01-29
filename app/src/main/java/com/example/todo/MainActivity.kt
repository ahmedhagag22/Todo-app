package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var ViewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(ViewBinding.root)
        selectedItem()
        ViewBinding.bottomnavigation.selectedItemId=R.id.list

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