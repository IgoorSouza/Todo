package com.igor.todo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.todo.data.Task
import com.igor.todo.data.TaskRepository
import com.igor.todo.ui.theme.BackgroundEndColor
import com.igor.todo.ui.theme.BackgroundStartColor
import com.igor.todo.ui.theme.PrimaryColor
import com.igor.todo.ui.theme.TextDarkColor

@Composable
fun TaskListScreen(
    onTaskClick: (String) -> Unit,
    onAddTaskClick: () -> Unit
) {
    val tasks = TaskRepository.tasks

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(BackgroundStartColor, BackgroundEndColor)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Minhas Tarefas",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextDarkColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhuma tarefa registrada.",
                        color = Color(0xFF555555),
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(tasks, key = { it.id }) { task ->
                        TaskCard(task, onTaskClick)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAddTaskClick,
            containerColor = PrimaryColor,
            shape = RoundedCornerShape(percent = 50),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 56.dp)
        ) {
            Icon(Icons.Filled.Add, "Adicionar Tarefa", tint = Color.White)
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onTaskClick: (String) -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTaskClick(task.id) }
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { TaskRepository.toggleTaskCompletion(task) },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryColor)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = task.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (task.isCompleted) Color(0xFFAAAAAA) else TextDarkColor,
                    style = if (task.isCompleted) MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = TextDecoration.LineThrough
                    ) else MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Excluir Tarefa",
                        tint = Color.Red
                    )
                }

                IconButton(onClick = { onTaskClick(task.id) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Ver detalhes",
                        tint = PrimaryColor
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            taskTitle = task.title,
            onConfirm = {
                TaskRepository.removeTaskById(task.id)
                showDeleteDialog = false
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}