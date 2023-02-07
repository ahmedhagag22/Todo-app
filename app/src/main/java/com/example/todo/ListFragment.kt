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
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar


class ListFragment : Fragment() {
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
        // select today date

        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                currentDate.set(Calendar.YEAR,date.year)
                currentDate.set(Calendar.MONTH,date.month)
                currentDate.set(Calendar.DAY_OF_MONTH,date.day)
                loadTask()
            }
        }
        viewBinding.calendarView.selectedDate = CalendarDay.today()
    }
        val currentDate=Calendar.getInstance()
    init {
        currentDate.set(Calendar.HOUR,0)
        currentDate.set(Calendar.MINUTE,0)
        currentDate.set(Calendar.SECOND,0)
        currentDate.set(Calendar.MILLISECOND,0)
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

