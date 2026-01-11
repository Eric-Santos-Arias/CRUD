
package com.escuela.backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class AlumnosController {

    private final AlumnosRepository alumnosRepository;

    public AlumnosController(AlumnosRepository alumnosRepository) {
        this.alumnosRepository = alumnosRepository;
    }

    // Crear con validación mínima
    @PostMapping
    public Alumnos crear(@RequestBody Alumnos alumnos) {
        if (alumnos.getNombre() == null || alumnos.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es rquerido");
        }
        return alumnosRepository.save(alumnos);
    }

    // Listar
    @GetMapping
    public List<Alumnos> listar() {
        return alumnosRepository.findAll(Sort.by("id"));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Alumnos obtener(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("id incorrecto");
        }
        return alumnosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    // Actualizar
    @PutMapping("/{id}")
    public Alumnos actualizar(@PathVariable Long id, @RequestBody Alumnos alumnos) {
        if (id <= 0) {
            throw new RuntimeException("id invalido");
        }
        
        if (!alumnosRepository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado");
        }
        
        alumnos.setId(id);
        return alumnosRepository.save(alumnos);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (id <= 0) {
            throw new RuntimeException("id inválido");
        }
        
        if (!alumnosRepository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado");
        }
        
        alumnosRepository.deleteById(id);
    }
}
