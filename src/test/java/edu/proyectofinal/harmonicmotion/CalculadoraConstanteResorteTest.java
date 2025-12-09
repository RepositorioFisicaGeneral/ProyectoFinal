package edu.proyectofinal.harmonicmotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraConstanteResorteTest {

    @Test
    void calculaKDesdeRelacion() {
        double masa = 2.0;
        double relacion = 4.0; // k/m
        double k = CalculadoraConstanteResorte.calcularKDesdeRelacion(masa, relacion);
        assertEquals(8.0, k, 1e-12);
    }
}
