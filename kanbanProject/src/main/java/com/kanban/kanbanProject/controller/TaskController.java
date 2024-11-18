package com.kanban.kanbanProject.controller;

import com.kanban.kanbanProject.model.Task;
import com.kanban.kanbanProject.model.TaskSpecification;
import com.kanban.kanbanProject.repository.TaskRepository;
import com.kanban.kanbanProject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/varias")
    public List<Task> criarTasks(@RequestBody List<Task> tasks){
        return taskService.insertTasks(tasks);
    }

    @PostMapping
    public Task criarTask(@RequestBody Task task){
        return taskService.insertTask(task);
    }

    @GetMapping
    public List<Task> listarTask(){
        return taskService.selectAllTask();
    }

    @PutMapping("/{id}/mover")
    public Task moverTask(@PathVariable long id){
        return taskService.moverTask(id);
    }

    @PutMapping("/{id}")
    public Task atualizarTarefa(@PathVariable long id, @RequestBody Task tarefaUpdate) {
        return taskService.atualizarTarefa(id, tarefaUpdate);
    }

    @GetMapping("/{id}")
    public Task buscarById(@PathVariable long id){
        return taskService.selectTaskById(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id){
        taskService.deletarTask(id);
    }

    @GetMapping("/filtro")
    public List<Task> getTarefas(@RequestParam(required = false) String status,
                                   @RequestParam(required = false) String prioridade,
                                   @RequestParam(required = false) String datalimite) {

        Specification<Task> spec = Specification.where(null);

        if (status != null && !status.isEmpty()) {
            spec = spec.and(TaskSpecification.hasStatus(status));
        }

        if (prioridade != null && !prioridade.isEmpty()) {
            spec = spec.and(TaskSpecification.hasPrioridade(prioridade));
        }

        if (datalimite != null && !datalimite.isEmpty()) {
            LocalDate dataLimiteConvertida = LocalDate.parse(datalimite);
        }

        return taskRepository.findAll(spec);

    }

    @GetMapping("/relatorio")
    public Map<String, List<Task>> gerarRelatorio() {

        Specification<Task> spec = Specification.where(TaskSpecification.isAtrasada());

        List<Task> tarefasAtrasadas = taskRepository.findAll(spec);

        return tarefasAtrasadas.stream()
                .collect(Collectors.groupingBy(Task::getStatus));
    }
}
