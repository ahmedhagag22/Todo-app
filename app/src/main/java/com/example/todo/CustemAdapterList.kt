package com.example.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemlistrecyclerBinding

class CustemAdapterList(var item:List<Task>?) :RecyclerView.Adapter<CustemAdapterList.viewHolder>() {
    class viewHolder(val viewBinding:ItemlistrecyclerBinding):
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val viewBinding=ItemlistrecyclerBinding.inflate(LayoutInflater.from(parent.context),
       parent, false)
        return viewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.viewBinding.title.text = item?.get(position)?.title
        holder.viewBinding.desc.text = item?.get(position)?.description
    }
    fun changeDate(newListOfTasks:List<Task>?)
    {
        item=newListOfTasks;
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int =
        item?.size?:0
}