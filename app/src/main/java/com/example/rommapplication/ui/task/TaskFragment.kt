package com.example.rommapplication.ui.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rommapplication.R
import com.example.rommapplication.TaskApplication
import com.example.rommapplication.data.Task
import com.example.rommapplication.data.TaskViewModel
import com.example.rommapplication.databinding.FragmentTaskBinding

class TaskFragment: Fragment(R.layout.fragment_task), TaskAdapter.OnItemClickListener {

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory((activity?.application as TaskApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTaskBinding.bind(view)

        val taskAdapter = TaskAdapter(this)

        binding.apply {
            recyclerViewTasks.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            fabAddTask.setOnClickListener {
                //TODO
            }
        }

        taskViewModel.allTasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }

    }

    override fun onItemClickListener(task: Task) {

    }

    override fun onCheckBoxClickListener(task: Task, isChecked: Boolean) {

    }

    override fun onDeleteClickListener(task: Task) {

    }

}