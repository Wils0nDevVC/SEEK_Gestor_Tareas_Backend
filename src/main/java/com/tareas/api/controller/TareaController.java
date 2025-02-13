package com.tareas.api.controller;

import com.tareas.api.dto.ResponseCustom;
import com.tareas.api.dto.TareaDTO;
import com.tareas.api.entity.Tarea;
import com.tareas.api.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "API para gestionar tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las tareas", description = "Retorna una lista de todas las tareas almacenadas.")
    public ResponseEntity<ResponseCustom<List<TareaDTO>>> obtenerTodas() {
        List<TareaDTO> tareas = tareaService.obtenerTodas();
        return ResponseEntity.ok(ResponseCustom.success(tareas, "Lista de tareas obtenida con éxito."));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por ID", description = "Retorna una tarea específica.")
    public ResponseEntity<ResponseCustom<TareaDTO>> obtenerPorId(@PathVariable String id) {
        TareaDTO tarea = tareaService.obtenerPorId(id);
        return ResponseEntity.ok(ResponseCustom.success(tarea, "Tarea encontrada."));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva tarea", description = "Crea una tarea y la guarda en la base de datos.")
    public ResponseEntity<ResponseCustom<TareaDTO>> crear(@Valid @RequestBody TareaDTO tareaDTO) {
        TareaDTO nuevaTarea = tareaService.guardar(tareaDTO);
        return ResponseEntity.ok(ResponseCustom.success(nuevaTarea, "Tarea creada con éxito."));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar una tarea", description = "Modifica el estado de una tarea.")
    public ResponseEntity<ResponseCustom<TareaDTO>> actualizarEstado(@PathVariable String id, @RequestParam TareaDTO.Estado estado) {
        TareaDTO tareaActualizada = tareaService.actualizarEstado(id, estado);
        return ResponseEntity.ok(ResponseCustom.success(tareaActualizada, "Estado actualizado correctamente."));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea de la base de datos.")
    public ResponseEntity<ResponseCustom<Void>> eliminar(@PathVariable String id) {
        tareaService.eliminar(id);
        return ResponseEntity.ok(ResponseCustom.success(null, "Tarea eliminada con éxito."));
    }
}
