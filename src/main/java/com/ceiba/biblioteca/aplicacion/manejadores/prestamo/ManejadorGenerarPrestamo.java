package com.ceiba.biblioteca.aplicacion.manejadores.prestamo;

import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class ManejadorGenerarPrestamo {

    private final RepositorioPrestamo repositorioPrestamo;

    public ManejadorGenerarPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    @Transactional
    public void ejecutar(String isbn, String nombreCliente) {
        throw new UnsupportedOperationException("Método pendiente por implementar");
    }

    @Transactional
    public void prestar(String isbn, String nombreCliente, Date fechaPrestamo, Date fechaEntrega) {
        repositorioPrestamo.prestrar(isbn, nombreCliente, fechaPrestamo, fechaEntrega);
    }
}
