package com.example.todo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemlistrecyclerBinding
import com.zerobranch.layout.SwipeLayout

class CustemAdapterList(var item: List<Task>?) :
    RecyclerView.Adapter<CustemAdapterList.viewHolder>() {
    var onItemClicked: OnItemClicked? = null
    var onItemDeleteClick: OnItemDeleteClick? = null

    class viewHolder(val viewBinding: ItemlistrecyclerBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewBinding = ItemlistrecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return viewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.viewBinding.title.text = item?.get(position)?.title
        holder.viewBinding.desc.text = item?.get(position)?.description
        if (item!![position].isdane) {
            holder.viewBinding.btnIsDone.setBackgroundColor(Color.GREEN)
            holder.viewBinding.title.setTextColor(Color.GREEN)
            holder.viewBinding.btnIsDone.setBackgroundResource(R.drawable.makedone)
        }
        holder.viewBinding.swipe.setOnActionsListener(object :SwipeLayout.SwipeActionsListener{
            override fun onOpen(direction: Int, isContinuous: Boolean) {

            }

            override fun onClose() {

            }

        })

        //on item click
        if(onItemClicked!=null) {
            holder.viewBinding.card.setOnLongClickListener {
                onItemClicked?.onItemClick(item!![position])
                true
            }
        }
        holder.viewBinding.delete.setOnClickListener(View.OnClickListener {
            onItemDeleteClick?.onItemDeleteClick(position, item!![position])
        })
    }

    fun changeDate(newListOfTasks: List<Task>?) {
        item = newListOfTasks;
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        item?.size ?: 0
}

//interface > to on click item in recylcler
interface OnItemClicked {
    fun onItemClick(task: Task)
}

interface OnItemDeleteClick {
    fun onItemDeleteClick(position: Int, task: Task)
}