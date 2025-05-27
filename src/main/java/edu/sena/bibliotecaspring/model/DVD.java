package edu.sena.bibliotecaspring.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dvds")
@PrimaryKeyJoinColumn(name = "id")
public class DVD extends ElementoBiblioteca {
    private String director;
    private String genero;
    private String duracion;

    public DVD() {
    }

    public DVD(String titulo, LocalDate fechaPublicacion, String director, String genero, String duracion) {
        super(titulo, fechaPublicacion);
        this.director = director;
        this.genero = genero;
        this.duracion = duracion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}