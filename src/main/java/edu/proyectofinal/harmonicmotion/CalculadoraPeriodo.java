package edu.proyectofinal.harmonicmotion;

/**
 * Calcula el período de oscilación para una masa 9m usando la constante k
 * obtenida experimentalmente.
 */
public class CalculadoraPeriodo {

    public static class ResultadoPeriodo {
        private final double periodo;
        private final double errorPeriodo;

        public ResultadoPeriodo(double periodo, double errorPeriodo) {
            this.periodo = periodo;
            this.errorPeriodo = errorPeriodo;
        }

        public double getPeriodo() {
            return periodo;
        }

        public double getErrorPeriodo() {
            return errorPeriodo;
        }
    }

    public static ResultadoPeriodo calcularPeriodoMasa9m(double masaBase,
                                                          CalculadoraConstanteResorte.ResultadoConstanteResorte resultadoK) {
        double masa9m = 9.0 * masaBase;
        double k = resultadoK.getKPromedio();
        double periodo = 2.0 * Math.PI * Math.sqrt(masa9m / k);

        double errorK = resultadoK.getErrorK();
        // Propagación de error: dT/dk en valor absoluto
        double derivada = Math.PI * Math.sqrt(masa9m) / Math.pow(k, 1.5);
        double errorPeriodo = derivada * errorK;

        return new ResultadoPeriodo(periodo, errorPeriodo);
    }
}
