package edu.proyectofinal.harmonicmotion;

import java.util.List;

/**
 * Calcula la constante elástica equivalente del resorte a partir
 * de los datos experimentales, usando la relación v^2 = -(k/m) x^2 + (k/m) A^2.
 */
public class CalculadoraConstanteResorte {

    public static class ResultadoConstanteResorte {
        private final double kPromedio;
        private final double errorK;
        private final double[] kIndividuales;

        public ResultadoConstanteResorte(double kPromedio,
                                         double errorK,
                                         double[] kIndividuales) {
            this.kPromedio = kPromedio;
            this.errorK = errorK;
            this.kIndividuales = kIndividuales.clone();
        }

        public double getKPromedio() {
            return kPromedio;
        }

        public double getErrorK() {
            return errorK;
        }

        public double[] getKIndividuales() {
            return kIndividuales.clone();
        }
    }

    /**
     * Cálculo general de k usando todos los conjuntos de datos.
     * masaBase se puede tomar como 1.0 kg si no se conoce el valor real.
     */
    public static ResultadoConstanteResorte calcularConstanteResorte(double masaBase) {
        List<DatosMovimiento.DatosParticula> particulas = DatosMovimiento.obtenerDatos();
        double[] kValores = new double[particulas.size()];

        for (int i = 0; i < particulas.size(); i++) {
            DatosMovimiento.DatosParticula datos = particulas.get(i);
            kValores[i] = calcularKParaParticula(datos, masaBase);
        }

        double suma = 0.0;
        for (double k : kValores) {
            suma += k;
        }
        double media = suma / kValores.length;

        double sumaCuadrados = 0.0;
        for (double k : kValores) {
            double diff = k - media;
            sumaCuadrados += diff * diff;
        }

        double desviacionEstandar = Math.sqrt(sumaCuadrados / (kValores.length - 1));
        double errorMedia = desviacionEstandar / Math.sqrt(kValores.length);

        return new ResultadoConstanteResorte(media, errorMedia, kValores);
    }

    /**
     * Cálculo directo de k a partir de la relación k/m.
     */
    public static double calcularKDesdeRelacion(double masaFisica, double relacionKSobreM) {
        return masaFisica * relacionKSobreM;
    }

    /**
     * Ajusta la relación v^2 vs x^2 para una partícula y calcula k correspondiente.
     */
    public static double calcularKParaParticula(DatosMovimiento.DatosParticula datos, double masaBase) {
        AjusteMinimosCuadrados.ResultadoAjuste ajuste = ajustarEnergia(datos);
        double relacionKSobreM = -ajuste.getPendienteB(); // pendiente = -(k/m)
        double masaFisica = datos.getMasaEnUnidadesDeM() * masaBase;
        return calcularKDesdeRelacion(masaFisica, relacionKSobreM);
    }

    /**
     * Construye los arreglos x^2 y v^2 y realiza el ajuste lineal.
     */
    public static AjusteMinimosCuadrados.ResultadoAjuste ajustarEnergia(DatosMovimiento.DatosParticula datos) {
        double[] posiciones = datos.getPosiciones();
        double[] velocidades = datos.getVelocidades();
        int n = posiciones.length;

        double[] x2 = new double[n];
        double[] v2 = new double[n];

        for (int i = 0; i < n; i++) {
            x2[i] = posiciones[i] * posiciones[i];
            v2[i] = velocidades[i] * velocidades[i];
        }

        return AjusteMinimosCuadrados.ajustarRecta(x2, v2);
    }
}
