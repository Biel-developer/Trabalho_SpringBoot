package com.kanban.kanbanProject.service;

import com.kanban.kanbanProject.model.Task;
import com.kanban.kanbanProject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task insertTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> insertTasks(List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }

    public List<Task> selectAllTask(){
        return taskRepository.findAll();
    }

    public Task selectTaskById(long id){
        Optional<Task> tk= taskRepository.findById(id);
        if(tk.isPresent()){
            return tk.get();
        }else{
            throw new RuntimeException("Task não encontrada.");
        }
    }

    public Task moverTask(long id){
        Task tk = selectTaskById(id);
        if (tk.getStatus().equals("A fazer")){
            tk.setStatus("Em progresso");
        } else {
            tk.setStatus("Task Concluída");
        }
        return taskRepository.save(tk);
    }

    public Task atualizarTarefa(long id, Task tarefaUpdate) {
        Optional<Task> tarefaOptional = taskRepository.findById(id);

        if (tarefaOptional.isPresent()) {
            Task tarefa = tarefaOptional.get();
            tarefa.setTitulo(tarefaUpdate.getTitulo());
            tarefa.setDescricao(tarefaUpdate.getDescricao());
            tarefa.setPrioridade(tarefaUpdate.getPrioridade());
            tarefa.setDatalimite(tarefaUpdate.getDatalimite());
            return taskRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada com o ID: " + id);
        }
    }

    public void deletarTask(long id){
        taskRepository.deleteById(id);
    }
}
