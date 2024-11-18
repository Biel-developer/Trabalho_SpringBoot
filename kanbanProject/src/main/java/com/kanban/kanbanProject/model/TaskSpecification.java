package com.kanban.kanbanProject.model;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {


    public static Specification<Task> hasStatus(String status) {
        return (root, query, builder) -> builder.equal(root.get("status"), status);
    }

    public static Specification<Task> hasPrioridade(String prioridade) {
        return (root, query, builder) -> builder.equal(root.get("prioridade"), prioridade);
    }

    public static Specification<Task> hasLimite(String datalimite){
        return (root, query, builder) -> builder.equal(root.get("datalimite"), datalimite);
    }

    public static Specification<Task> hasLimiteAndPrioridade(String datalimite, String prioridade){
        return Specification.where(hasLimite(datalimite)).and(hasPrioridade(prioridade));
    }
    public static Specification<Task> hasStatusAndPrioridade(String status, String prioridade) {
        return Specification.where(hasStatus(status)).and(hasPrioridade(prioridade));
    }

    public static Specification<Task> isAtrasada() {
        return (root, query, builder) -> builder.and(
                builder.lessThan(root.get("datalimite"), LocalDateTime.now()), // Data limite passada
                builder.notEqual(root.get("status"), "Concluído") // Status não "Concluído"
        );
    }
}
