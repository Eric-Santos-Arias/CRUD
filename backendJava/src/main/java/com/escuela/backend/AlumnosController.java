
package com.escuela.backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Sort;
import java.util.Optional;


@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "http://127.0.0.1:8080") //no funciona colocando localhost:8080
public class AlumnosController {

    private final AlumnosRepository alumnosRepository;

    public AlumnosController(AlumnosRepository alumnosRepository) {
        this.alumnosRepository = alumnosRepository;
    }

//los elementos con @ son anotaciones de spring boot son como un ORM (Object Relational Mapping),
//el ORM se guardo en alumnosRepository importamos arriba la clase AlumnosRepository


//agregar alumnos
    @PostMapping  
    public Alumnos crear(@RequestBody Alumnos alumnos) {

        //ver como remover tantos if's son muchos pero funciona bien
        if (alumnos.getNombre() == null || alumnos.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es rquerido");
        }
         if (alumnos.getApellido() == null || alumnos.getApellido().trim().isEmpty()) {
            throw new RuntimeException("El apellido es rquerido");
        }
         if (alumnos.getTelefono() == null || alumnos.getTelefono().trim().isEmpty()) {
            throw new RuntimeException("El telefono es rquerido");
        }
         if (alumnos.getDireccion() == null || alumnos.getDireccion().trim().isEmpty()) {
            throw new RuntimeException("La direccion es rquerido");
        }

        return alumnosRepository.save(alumnos);
    }

    //obtener los todos datos del alumno 
    @GetMapping //usando el metodo GET 
    public List<Alumnos> listar() {
        //será necesario validar algo aca?

        return alumnosRepository.findAll(Sort.by("id")); //ordenar por id importamos Sort arriba


        //no funciono findAllByOrderByIdAsc() segun stackoverflow 
    }

   
    @GetMapping("/{id}") //PathVariable para indicar que el id viene en la ruta de la url 
    public Alumnos obtener(@PathVariable Long id) {

        //revisamos si el id que pasamos es mayor o giual a 0
        if (id <= 0 || !alumnosRepository.existsById(id)  ) {
            throw new RuntimeException("id incorrecto");
        }  //esto es redunante pero sirve para validar
        return alumnosRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        //esto hace lo mismo que el bloque de codigo comentado arriba
    }

    // Actualizar
    @PutMapping("/{id}")//como pasamos el id tambien necesitamos el cuerpo del request para actualizar 
    public Alumnos actualizar(@PathVariable Long id, @RequestBody Alumnos alumnos) {
        if (id <= 0) {
            throw new RuntimeException("id invalido");
        }
        
        if (!alumnosRepository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado");
        }
        
        alumnos.setId(id);
        return alumnosRepository.save(alumnos); //no existe un metodo update hasta donde se

        //dato a tomar en cuenta el metodo save() de JPA Repository hace tanto crear como actualizar
        //si el id existe actualiza, si no existe crea uno nuevo 

    }

    // eliminar alumnos 
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
