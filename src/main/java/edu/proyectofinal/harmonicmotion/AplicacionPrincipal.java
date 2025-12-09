package edu.proyectofinal.harmonicmotion;

/**
 * Punto de entrada principal del proyecto.
 */
public class AplicacionPrincipal {

    public static void main(String[] args) {
        double masaBase = 1.0; // puedes cambiarla si tu guía de laboratorio indica otra masa
        String directorioGraficos = "charts";

        try {
            // Semana 2: graficar datos experimentales
            GraficadorDatos.graficarTodasLasParticulas(directorioGraficos);

            // Semana 5: cálculo de k promedio
            CalculadoraConstanteResorte.ResultadoConstanteResorte resultadoK =
                    CalculadoraConstanteResorte.calcularConstanteResorte(masaBase);

            // Semana 5: período para masa 9m
            CalculadoraPeriodo.ResultadoPeriodo resultadoPeriodo =
                    CalculadoraPeriodo.calcularPeriodoMasa9m(masaBase, resultadoK);

            // Semana 5: simulación de movimiento
            SimuladorMasa9m.simularMovimiento(directorioGraficos, masaBase, 5.0, 0.01);

            System.out.println("Constante elástica k promedio = "
                    + resultadoK.getKPromedio() + " ± " + resultadoK.getErrorK() + " [unidades de tu sistema]");
            System.out.println("Período para masa 9m = "
                    + resultadoPeriodo.getPeriodo() + " ± " + resultadoPeriodo.getErrorPeriodo() + " s");
            System.out.println("Gráficos generados en el directorio '" + directorioGraficos + "'");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al ejecutar la aplicación:");
            e.printStackTrace();
        }
    }
}
