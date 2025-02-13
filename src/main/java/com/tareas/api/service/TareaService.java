package com.tareas.api.service;

import com.tareas.api.dto.TareaDTO;
import com.tareas.api.entity.Tarea;
import com.tareas.api.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<TareaDTO> obtenerTodas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas.stream().map(this::convertirA_DTO).collect(Collectors.toList());
    }

    public TareaDTO obtenerPorId(String id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada con ID: " + id));
        return convertirA_DTO(tarea);
    }

    public TareaDTO guardar(TareaDTO tareaDto) {
        Tarea tarea = convertirA_Entidad(tareaDto);
        tarea = tareaRepository.save(tarea);
        return convertirA_DTO(tarea);
    }

    public TareaDTO actualizarEstado(String id, TareaDTO.Estado nuevoEstado) {

        Tarea.Estado estado = Tarea.Estado.valueOf(nuevoEstado.name());

        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada con ID: " + id));
        tarea.setEstado(estado);
        tarea = tareaRepository.save(tarea);
        return convertirA_DTO(tarea);
    }

    public void eliminar(String id) {
        if (!tareaRepository.existsById(id)) {
            throw new NoSuchElementException("No se puede eliminar. Tarea no encontrada con ID: " + id);
        }
        tareaRepository.deleteById(id);
    }

    // Método para convertir de Entidad a DTO
    private TareaDTO convertirA_DTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(TareaDTO.Estado.valueOf(tarea.getEstado().name()));
        dto.setUserId(tarea.getUserId());
        return dto;
    }

    // Método para convertir de DTO a Entidad
    private Tarea convertirA_Entidad(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(Tarea.Estado.valueOf(dto.getEstado().name()));
        tarea.setUserId(dto.getUserId());
        return tarea;
    }
}
