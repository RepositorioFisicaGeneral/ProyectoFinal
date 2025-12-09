package edu.proyectofinal.harmonicmotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraPeriodoTest {

    @Test
    void calculaPeriodoPara9m() {
        // Creamos un resultado de k artificial para la prueba
        double kPromedio = 4.0;
        double errorK = 0.1;
        double[] kIndividuales = {4.0};

        CalculadoraConstanteResorte.ResultadoConstanteResorte resultadoK =
                new CalculadoraConstanteResorte.ResultadoConstanteResorte(kPromedio, errorK, kIndividuales);

        CalculadoraPeriodo.ResultadoPeriodo periodo =
                CalculadoraPeriodo.calcularPeriodoMasa9m(1.0, resultadoK);

        double esperado = 2.0 * Math.PI * Math.sqrt(9.0 / 4.0);
        assertEquals(esperado, periodo.getPeriodo(), 1e-12);
    }
}
