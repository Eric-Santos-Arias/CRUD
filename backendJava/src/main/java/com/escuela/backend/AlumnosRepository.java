
package com.escuela.backend;


//esta linea es donde se llama al ORM de JPA Repository
import org.springframework.data.jpa.repository.JpaRepository;

//revisar porque se llama interface y no class
public interface AlumnosRepository extends JpaRepository<Alumnos, Long> {
}

