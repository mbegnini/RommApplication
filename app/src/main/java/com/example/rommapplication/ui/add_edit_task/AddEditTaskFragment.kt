package com.example.rommapplication.ui.add_edit_task

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rommapplication.R
import com.example.rommapplication.TaskApplication
import com.example.rommapplication.data.Task
import com.example.rommapplication.data.TaskViewModel
import com.example.rommapplication.databinding.FragmentAddEditTaskBinding

class AddEditTaskFragment: Fragment(R.layout.fragment_add_edit_task) {

    val args: AddEditTaskFragmentArgs by navArgs()

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory((activity?.application as TaskApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditTaskBinding.bind(view)

        val newTask = args.task == null

        val task = args?.task ?: Task("")

        binding.apply {
            editTextName.setText(task.name)
            checkBoxImportant.isChecked = task.important
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewCreated.isVisible = !newTask
            textViewCreated.text = "Created: ${task.createdDateFormat}"

            fabConfirm.setOnClickListener {
                if(newTask)
                    taskViewModel.insert(Task(name = editTextName.text.toString(), important = checkBoxImportant.isChecked))
                else
                    taskViewModel.update(task.copy(name = editTextName.text.toString(), important = checkBoxImportant.isChecked))

                findNavController().navigateUp()
            }

        }

    }

}