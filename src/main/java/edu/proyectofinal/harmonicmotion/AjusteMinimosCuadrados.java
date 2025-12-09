package edu.proyectofinal.harmonicmotion;

/**
 * Implementa el ajuste lineal por el Método de Mínimos Cuadrados (MMC)
 * para una recta y = A + B x.
 */
public class AjusteMinimosCuadrados {

    public static class ResultadoAjuste {
        private final double interceptoA;
        private final double pendienteB;
        private final double errorInterceptoA;
        private final double errorPendienteB;

        public ResultadoAjuste(double interceptoA,
                               double pendienteB,
                               double errorInterceptoA,
                               double errorPendienteB) {
            this.interceptoA = interceptoA;
            this.pendienteB = pendienteB;
            this.errorInterceptoA = errorInterceptoA;
            this.errorPendienteB = errorPendienteB;
        }

        public double getInterceptoA() {
            return interceptoA;
        }

        public double getPendienteB() {
            return pendienteB;
        }

        public double getErrorInterceptoA() {
            return errorInterceptoA;
        }

        public double getErrorPendienteB() {
            return errorPendienteB;
        }
    }

    /**
     * Ajusta una recta y = A + B x a los datos.
     */
    public static ResultadoAjuste ajustarRecta(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("x e y deben tener la misma longitud.");
        }
        int n = x.length;
        if (n < 2) {
            throw new IllegalArgumentException("Se requieren al menos dos puntos para ajustar una recta.");
        }

        double sumX = 0.0;
        double sumY = 0.0;
        double sumXX = 0.0;
        double sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXX += x[i] * x[i];
            sumXY += x[i] * y[i];
        }

        double det = n * sumXX - sumX * sumX;
        if (det == 0.0) {
            throw new IllegalStateException("Determinante cero: no se puede ajustar la recta.");
        }

        double pendienteB = (n * sumXY - sumX * sumY) / det;
        double interceptoA = (sumXX * sumY - sumX * sumXY) / det;

        // Cálculo de errores
        double sumaResiduos2 = 0.0;
        for (int i = 0; i < n; i++) {
            double yEstimada = interceptoA + pendienteB * x[i];
            double residuo = y[i] - yEstimada;
            sumaResiduos2 += residuo * residuo;
        }

        double sigma2 = sumaResiduos2 / (n - 2);
        double errorPendienteB = Math.sqrt(sigma2 * n / det);
        double errorInterceptoA = Math.sqrt(sigma2 * sumXX / det);

        return new ResultadoAjuste(interceptoA, pendienteB, errorInterceptoA, errorPendienteB);
    }
}
