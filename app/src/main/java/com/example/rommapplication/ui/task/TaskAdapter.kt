package com.example.rommapplication.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rommapplication.data.Task
import com.example.rommapplication.databinding.ItemTaskBinding

class TaskAdapter(private val listener: OnItemClickListener) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TASK_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val task = getItem(position)
                        listener.onItemClickListener(task)
                    }
                }

                checkBoxCompleted.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val task = getItem(position)
                        listener.onCheckBoxClickListener(task, checkBoxCompleted.isChecked)
                    }
                }

                imageViewDelete.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val task = getItem(position)
                        listener.onDeleteClickListener(task)
                    }
                }

            }
        }

        fun bind(task: Task){
            binding.apply {
                textViewName.text = task.name
                checkBoxCompleted.isChecked = task.complete
                textViewName.paint.isStrikeThruText = task.complete
                imageViewImportant.isVisible = task.important
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClickListener(task: Task)
        fun onCheckBoxClickListener(task: Task, isChecked: Boolean)
        fun onDeleteClickListener(task: Task)
    }

    companion object {
        private val TASK_COMPARATOR = object : DiffUtil.ItemCallback<Task>(){
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }
        }
    }

}