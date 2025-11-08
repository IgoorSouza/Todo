# Aplicativo de Lista de Tarefas (Jetpack Compose)

Este projeto consiste em desenvolver uma **lista de tarefas** desenvolvida utilizando **Jetpack Compose** para a interface do usuário. O foco está em criar uma **interface moderna e responsiva**, com **boas práticas de UI/UX**, e um fluxo de navegação simples para gerenciamento de atividades. Os dados são persistidos em memória (RAM) enquanto o aplicativo está em execução.

---

## 💻 Instruções de Execução

1.  **Clone o repositório** ou abra o projeto no Android Studio.
2.  Certifique-se de que todas as dependências do Gradle estão sincronizadas.
3.  Execute o aplicativo em um emulador ou dispositivo Android (SDK 24+).

## 📱 Features

- **Gerenciamento de Tarefas**
  - **Listagem de Tarefas** com título e status de conclusão.
  - **Adição de Novas Tarefas** em uma tela de formulário dedicada (título e descrição).
  - **Marcação de Conclusão** utilizando um `Checkbox` na lista.
  - **Exclusão** de tarefas com modal de confirmação tanto na lista quanto na tela de detalhes.
  - **Visualização de Detalhes** de uma tarefa específica.

- **Navegação e Estado**
  - Navegação entre três telas principais: Lista, Adicionar e Detalhes (Single-Activity pattern).
  - Gerenciamento de estado das tarefas em memória (`TaskRepository`).

- **UI/UX – Jetpack Compose + Material 3**
  - Interface desenvolvida inteiramente com **Jetpack Compose** e o tema **Material 3**.
  - **Design moderno** com gradiente de fundo nas telas.
  - Uso de `Floating Action Button` (FAB) para iniciar a adição de novas tarefas.
  - Estilização de Cards e Cores consistentes com o projeto original.

<img src="https://github.com/user-attachments/assets/3a11d4f5-9b1d-4a76-9861-d06954aba79b" alt="Tela Inicial - Lista de Tarefas" width="300"/>
<img src="https://github.com/user-attachments/assets/9134f9d8-ae28-41d0-81f3-a07878d94e07" alt="Tela de Detalhes da Tarefa" width="300"/>
<img src="https://github.com/user-attachments/assets/f4cc0001-39a5-43bd-b289-09089d6675c4" alt="Tela de Adição de Tarefa" width="300"/>

## 🛠️ Technologies Used

- **Language:** Kotlin
- **Framework:** Jetpack Compose (Material 3)
- **Data Persistence:** Memória (objetos `mutableStateListOf` em `TaskRepository`)
- **IDE:** Android Studio
- **Build System:** Gradle

## 🚀 Highlights

- Totalmente desenvolvido com **Jetpack Compose**.
- Design responsivo e consistente com **Material Design 3** e estilização replicada.
- Implementação de navegação entre telas (`Screen` sealed class).
- Uso eficiente do `LazyColumn` para a lista.
- Implementação de diálogos de confirmação de exclusão (`AlertDialog`).
