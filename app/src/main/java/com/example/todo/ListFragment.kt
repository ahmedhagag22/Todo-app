package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.base.BaseFragment
import com.example.todo.database.MyDataBase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.databinding.ItemlistrecyclerBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar


class ListFragment : BaseFragment() {
    lateinit var viewBinding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    lateinit var taskadapter: CustemAdapterList
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskadapter = CustemAdapterList(null)
        viewBinding.recyclerView.adapter = taskadapter
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                currentDate.set(Calendar.YEAR, date.year)
                currentDate.set(Calendar.MONTH, date.month - 1)
                currentDate.set(Calendar.DAY_OF_MONTH, date.day)
                loadTask()
            }
        }
        // select today date
        viewBinding.calendarView.selectedDate = CalendarDay.today()

        //on click item > show done or update
        taskadapter.onItemClicked = object : OnItemClicked {
            override fun onItemClick( task: Task) {
                // show messege > Base fragment > alert Diadlog

                showMessage(
                    "what do you want ?",
                    "Update",
                    { _, dialog -> updateTodoTask(task) },
                    "Make done !",
                    { _, dialog -> makeDone(task) },
                )
            }
        }
        //on click delete
        taskadapter.onItemDeleteClick = object : OnItemDeleteClick {
            override fun onItemDeleteClick(position: Int, task: Task) {
                deleteTask(task)
            }

        }
    }


    // func make done
    private fun makeDone(task: Task) {
        task.isdane = true
        MyDataBase.getDataBase(requireActivity()).getDao()
            .update(task)
        refreshRecyclerView()

    }

    //refresh recycler
    private fun refreshRecyclerView() {
        taskadapter.changeDate(MyDataBase.getDataBase(requireActivity()).getDao().getAllTasks())
        taskadapter.notifyDataSetChanged()
    }

    // fun update data
    private fun updateTodoTask(task: Task) {
        var intent = Intent(requireContext(), EditActivity::class.java)
        //send task by data base > to show data and edit it
        intent.putExtra(constant.Task, task)
        startActivity(intent)

    }

    //fun make delete
    private fun deleteTask(task: Task) {
        showMessage("Are you want to delete this task !!",
            posActionTitle = "yes",
            posAction = { dialog, _ ->
                dialog.dismiss()
                MyDataBase.getDataBase(requireContext()).getDao().delete(task)
                refreshRecyclerView()
            },
            negActionTitle = "no",
            negAction = { dialog, _ ->
                dialog.dismiss()
            }
        )

    }


    val currentDate = Calendar.getInstance()

    init {
        currentDate.set(Calendar.HOUR, 0)
        currentDate.set(Calendar.MINUTE, 0)
        currentDate.set(Calendar.SECOND, 0)
        currentDate.set(Calendar.MILLISECOND, 0)
    }

    override fun onResume() {
        super.onResume()
        loadTask()
    }

    // TODO:  المشكله هنا اني لو روحت اضيف تاسك وانا مش واقف في الفرجمنت الخاصه ب الليست هيكراش
    // TODO:الحل اني اعمل  chick fragment is visible by >  (!isResumed) (لو مش ظاهره او مش شغاله متعملش لود )
    fun loadTask() {
        if (!isResumed) {
            return
        }
        var tasks = MyDataBase.getDataBase(requireActivity())
            // TODO: المفروض نستخدم require لو انا واثق ان  الفرجمنت ظاهره
            .getDao().getTasksByDate(currentDate.timeInMillis);
        taskadapter.changeDate(tasks)
    }
}

