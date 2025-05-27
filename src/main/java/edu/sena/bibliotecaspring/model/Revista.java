package edu.sena.bibliotecaspring.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "revistas")
@PrimaryKeyJoinColumn(name = "id")
public class Revista extends ElementoBiblioteca {
    private String editorial;
    private String categoria;
    private String issn;

    public Revista() {
    }

    public Revista(String titulo, LocalDate fechaPublicacion, String editorial, String categoria, String issn) {
        super(titulo, fechaPublicacion);
        this.editorial = editorial;
        this.categoria = categoria;
        this.issn = issn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }
}