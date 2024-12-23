
# Task CLI - Gerenciador de Tarefas

Este é um simples gerenciador de tarefas de linha de comando (CLI) em Java, projetado para permitir o gerenciamento de tarefas com funcionalidades como adicionar, atualizar, excluir e listar tarefas com base em seu status.

- https://roadmap.sh/projects/task-tracker

## Funcionalidades

- **Adicionar tarefa**: Permite adicionar uma nova tarefa com uma descrição.
- **Atualizar tarefa**: Permite atualizar a descrição de uma tarefa existente.
- **Excluir tarefa**: Permite excluir uma tarefa pelo seu ID.
- **Marcar tarefa como "em progresso"**: Permite marcar uma tarefa como "in-progress".
- **Marcar tarefa como "concluída"**: Permite marcar uma tarefa como "done".
- **Listar tarefas**: Exibe todas as tarefas ou filtra as tarefas com base no seu status (ex: "todo", "in-progress", "done").

## Como usar

### Requisitos

- JDK 8 ou superior

### Comandos

- **Adicionar tarefa**:
  ```bash
  java TaskCLI add "Descrição da tarefa"
  ```

- **Atualizar tarefa**:
  ```bash
  java TaskCLI update <ID da tarefa> "Nova descrição"
  ```

- **Excluir tarefa**:
  ```bash
  java TaskCLI delete <ID da tarefa>
  ```

- **Marcar tarefa como "em progresso"**:
  ```bash
  java TaskCLI mark-in-progress <ID da tarefa>
  ```

- **Marcar tarefa como "concluída"**:
  ```bash
  java TaskCLI mark-done <ID da tarefa>
  ```

- **Listar tarefas**:
  ```bash
  java TaskCLI list [status]
  ```
  Onde o status pode ser "todo", "in-progress", "done", ou deixado em branco para listar todas as tarefas.

## Estrutura do Projeto

O projeto consiste em um único arquivo Java (`TaskCLI.java`), que implementa as funcionalidades do gerenciador de tarefas. As tarefas são salvas em um arquivo `tasks.json`.

## Roadmap

O projeto segue a [task tracker roadmap](https://roadmap.sh/projects/task-tracker), que inclui os seguintes marcos de desenvolvimento:

1. **Cadastro de tarefas**: Implementação da criação de tarefas e armazenamento de dados.
2. **Gestão de status**: Implementação do controle de status das tarefas (todo, in-progress, done).
3. **Aprimoramento da interface**: Refinamento da interação com o usuário e adição de novas funcionalidades.
4. **Persistência de dados**: Melhoria na forma de persistir e carregar as tarefas com segurança.

## Contribuição

1. Faça um fork deste repositório.
2. Crie uma branch para sua modificação (`git checkout -b feature/nome-da-sua-modificação`).
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`).
4. Faça push para a sua branch (`git push origin feature/nome-da-sua-modificação`).
5. Abra um Pull Request para o branch principal.