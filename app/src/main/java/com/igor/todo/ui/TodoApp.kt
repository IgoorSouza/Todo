package com.igor.todo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.igor.todo.ui.theme.TodoTheme

sealed class Screen {
    object List : Screen()
    object Add : Screen()
    data class Detail(val taskId: String) : Screen()
}

@Composable
fun TodoApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.List) }

    TodoTheme {
        when (val screen = currentScreen) {
            Screen.List -> {
                TaskListScreen(
                    onTaskClick = { taskId -> currentScreen = Screen.Detail(taskId) },
                    onAddTaskClick = {
                        currentScreen = Screen.Add
                    }
                )
            }
            Screen.Add -> {
                AddTaskScreen(
                    onBackClick = { currentScreen = Screen.List }
                )
            }
            is Screen.Detail -> {
                TaskDetailScreen(
                    taskId = screen.taskId,
                    onBackClick = {
                        currentScreen = Screen.List
                    }
                )
            }
        }
    }
}