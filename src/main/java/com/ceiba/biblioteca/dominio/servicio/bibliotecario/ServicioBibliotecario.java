package com.ceiba.biblioteca.dominio.servicio.bibliotecario;

import com.ceiba.biblioteca.dominio.Libro;
import com.ceiba.biblioteca.dominio.Prestamo;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.dominio.excepcion.PrestamoException;
import java.util.Objects;

public class ServicioBibliotecario {

    public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

    private final RepositorioLibro repositorioLibro;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void prestar(String isbn) {   
        if (!esPrestado(isbn)) {
            //consulta el libro
            Libro libroAPrestar = repositorioLibro.obtenerPorIsbn(isbn);
            repositorioPrestamo.agregar(prestarLibro(libroAPrestar));
        } else {
            throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
        }
    }

    public boolean esPrestado(String isbn) {
        Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
        if(Objects.nonNull(libro)){
            return true;
        }
        return false;
    }

    public boolean yaEsPrestado(String isbn) {
        Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
        if(Objects.nonNull(libro)){
            return false;
        }
        return true;
    }

    private Prestamo prestarLibro(Libro libro) {
        return new Prestamo(libro);
    }
}
