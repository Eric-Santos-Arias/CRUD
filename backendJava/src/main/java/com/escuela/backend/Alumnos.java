
package com.escuela.backend;

import jakarta.persistence.Entity; //esta libreria ayuda a mapear la clase a una tabla de base de datos
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//ojito entity usa el nombre de la clase como nombre de la tabla en la base de datos 
//si queremos otro nombre usamos @Table(name="nombreTabla"). 
@Entity
public class Alumnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //estos son los campos de la tabla alumnos que tengo en la base de datos
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    // colocamos los getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    //todo estos seran llamados desde el controlador y creo que tambien desde el repositorio 

}

