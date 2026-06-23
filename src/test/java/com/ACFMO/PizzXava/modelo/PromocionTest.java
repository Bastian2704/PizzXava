package com.ACFMO.PizzXava.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PromocionTest {

    @Test
    public void promocionDebeEstarActiva() {

        Promocion promocion = new Promocion();

        promocion.setFechaInicio(LocalDate.now().minusDays(1));
        promocion.setFechaFin(LocalDate.now().plusDays(1));

        assertTrue(promocion.estaActiva());
    }
}