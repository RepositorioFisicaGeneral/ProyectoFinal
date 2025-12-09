package edu.proyectofinal.harmonicmotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AjusteMinimosCuadradosTest {

    @Test
    void ajustaRectaPerfecta() {
        double[] x = {0.0, 1.0, 2.0, 3.0};
        double[] y = {1.0, 3.0, 5.0, 7.0}; // y = 1 + 2x

        AjusteMinimosCuadrados.ResultadoAjuste r =
                AjusteMinimosCuadrados.ajustarRecta(x, y);

        assertEquals(1.0, r.getInterceptoA(), 1e-12);
        assertEquals(2.0, r.getPendienteB(), 1e-12);
        assertTrue(r.getErrorPendienteB() < 1e-6);
    }

    @Test
    void lanzaExcepcionSiLongitudesDifieren() {
        double[] x = {0.0, 1.0};
        double[] y = {1.0};
        assertThrows(IllegalArgumentException.class,
                () -> AjusteMinimosCuadrados.ajustarRecta(x, y));
    }
}
