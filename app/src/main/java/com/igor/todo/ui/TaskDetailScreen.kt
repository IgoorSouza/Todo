package com.igor.todo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.todo.data.TaskRepository
import com.igor.todo.ui.theme.BackgroundEndColor
import com.igor.todo.ui.theme.BackgroundStartColor
import com.igor.todo.ui.theme.DividerColor
import com.igor.todo.ui.theme.PrimaryColor
import com.igor.todo.ui.theme.TextDarkColor
import com.igor.todo.ui.theme.TextSecondaryColor

@Composable
fun TaskDetailScreen(
    taskId: String,
    onBackClick: () -> Unit
) {
    val task = TaskRepository.getTaskById(taskId)
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (task == null) {
        onBackClick()
        return
    }

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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onBackClick() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = PrimaryColor
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Voltar",
                        color = PrimaryColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }

                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Excluir Tarefa",
                        tint = Color.Red
                    )
                }
            }

            Text(
                text = "Detalhes da Tarefa",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextDarkColor,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = task.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDarkColor,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    HorizontalDivider(color = DividerColor)
                    Spacer(modifier = Modifier.height(16.dp))

                    DetailItem(
                        label = "Status",
                        value = if (task.isCompleted) "Concluída" else "Pendente",
                        valueColor = if (task.isCompleted) Color(0xFF388E3C) else Color.Red
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        label = "Descrição",
                        value = task.description,
                        maxLines = Int.MAX_VALUE
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
                onBackClick()
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}

@Composable
fun DetailItem(label: String, value: String, valueColor: Color = TextDarkColor, maxLines: Int = 1) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondaryColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = valueColor,
            maxLines = maxLines
        )
    }
}