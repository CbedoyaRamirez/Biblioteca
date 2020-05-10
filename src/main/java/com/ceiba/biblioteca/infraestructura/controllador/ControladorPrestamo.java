package com.ceiba.biblioteca.infraestructura.controllador;

import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorGenerarPrestamo;
import com.ceiba.biblioteca.aplicacion.manejadores.prestamo.ManejadorObtenerPrestamo;
import com.ceiba.biblioteca.dominio.Prestamo;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/prestamos")
public class ControladorPrestamo {
    private final ManejadorObtenerPrestamo manejadorObtenerPrestamo;
    private final ManejadorGenerarPrestamo manejadorGenerarPrestamo;

    LocalDate fechaActual = LocalDate.now();

    private static final int CANTIDADDIASPRESTAMO = 15;

    public ControladorPrestamo(ManejadorObtenerPrestamo manejadorObtenerPrestamo, ManejadorGenerarPrestamo manejadorGenerarPrestamo) {
        this.manejadorObtenerPrestamo = manejadorObtenerPrestamo;
        this.manejadorGenerarPrestamo = manejadorGenerarPrestamo;
    }

    @PostMapping("/{isbn}/{nombreCliente}")
    public void prestar(@PathVariable(name = "isbn") String isbn, @PathVariable(name = "nombreCliente") String nombreCliente) {
        validarCamposParaElPrestamo(isbn, nombreCliente);
        String cadenaReverse = new StringBuilder(isbn).reverse().toString();
        if (cadenaReverse.equals(isbn)) {
            throw new UnsupportedOperationException("Los libros palindromos solo se pueden utilizar en la biblioteca");
        } else {
            validacionesPrestamo(isbn, nombreCliente);
        }
    }

    private void validarCamposParaElPrestamo(String isbn, String nombre) {
        if (isbn.trim() == "" || nombre.trim() == "") {
            throw new UnsupportedOperationException("Los libros palindromos solo se pueden utilizar en la biblioteca");
        }
    }

    private void validacionesPrestamo(String isbn, String nombre) {
        if (validarCantidadNumericaISBN(isbn)) {
            guardarFechaPrestamo(isbn, nombre, new Date(), calcularFechaEntrega());
        } else {
            guardarFechaPrestamo(isbn, nombre, null, null);
        }
    }

    private Date calcularFechaEntrega() {
        int cantidadDiasArestar = 0;
        System.out.println(java.sql.Date.valueOf(fechaActual.plusDays(CANTIDADDIASPRESTAMO)));
        for (int i = 0; i < CANTIDADDIASPRESTAMO; i++) {
            if (fechaActual.plusDays(i).getDayOfWeek().getValue() == Calendar.SUNDAY) {
                cantidadDiasArestar = cantidadDiasArestar + 1;
            }
        }

        return java.sql.Date.valueOf(fechaActual.plusDays(CANTIDADDIASPRESTAMO - cantidadDiasArestar));
    }

    private Boolean validarCantidadNumericaISBN(String isbn) {
        char[] array = isbn.toCharArray();
        String caracter = "";
        Integer resultado = 0;

        for (int i = 0; i < array.length; i++) {
            caracter = String.valueOf(array[i]);
            if (caracter.matches("[0-9]*")) {
                resultado = resultado + Integer.parseInt(caracter);
            }
        }

        if (resultado > 30) {
            return true;
        } else {
            return false;
        }
    }

    private void guardarFechaPrestamo(String isbn, String nombre, Date fechaPrestamo, Date fechaEntrega) {
        manejadorGenerarPrestamo.prestar(isbn, nombre, fechaPrestamo, fechaEntrega);
    }

    @GetMapping("/{isbn}")
    public Prestamo obtenerLibroPrestadoPorIsbn(@PathVariable(name = "isbn") String isbn) {
        return this.manejadorObtenerPrestamo.ejecutar(isbn);
    }
}
