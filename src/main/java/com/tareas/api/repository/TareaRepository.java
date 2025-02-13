package com.tareas.api.repository;

import com.tareas.api.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, String> {
}
