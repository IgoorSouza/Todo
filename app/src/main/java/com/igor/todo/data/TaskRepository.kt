package com.igor.todo.data

import androidx.compose.runtime.mutableStateListOf

object TaskRepository {
    private val _tasks = mutableStateListOf(
        Task(title = "Desenvolver uma Lista de Tarefas", description = "Utilizar Jetpack Compose para construir uma Lista de Tarefas moderna e interativa.", isCompleted = false),
    )

    val tasks: List<Task>
        get() = _tasks

    fun addTask(title: String, description: String = "") {
        if (title.isNotBlank()) {
            _tasks.add(0, Task(title = title, description = description))
        }
    }

    fun toggleTaskCompletion(task: Task) {
        val index = _tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            _tasks[index] = _tasks[index].copy(isCompleted = !task.isCompleted)
        }
    }

    fun removeTaskById(taskId: String): Boolean {
        return _tasks.removeIf { it.id == taskId }
    }

    fun getTaskById(taskId: String): Task? {
        return _tasks.find { it.id == taskId }
    }
}