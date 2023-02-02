package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.database.MyDataBase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.databinding.ItemlistrecyclerBinding


class ListFragment : Fragment() {
        lateinit var viewBinding:FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=FragmentListBinding.inflate(inflater,container,false)
        return viewBinding.root

    }
        lateinit var taskadapter:CustemAdapterList
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskadapter= CustemAdapterList(null)
        viewBinding.recyclerView.adapter=taskadapter

        loadTask()



    }

    fun loadTask() {
        var tasks=MyDataBase.getDataBase(requireActivity())
            .getDao().getAllTasks()
        taskadapter.changeDate(tasks)
    }


}

