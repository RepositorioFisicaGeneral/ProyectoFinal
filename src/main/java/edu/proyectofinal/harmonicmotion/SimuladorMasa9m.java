package edu.proyectofinal.harmonicmotion;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Simula el movimiento oscilatorio para una masa 9m usando k promedio
 * y genera un gráfico x(t).
 */
public class SimuladorMasa9m {

    public static void simularMovimiento(String directorioSalida,
                                         double masaBase,
                                         double tiempoFinal,
                                         double paso) throws IOException {

        CalculadoraConstanteResorte.ResultadoConstanteResorte resultadoK =
                CalculadoraConstanteResorte.calcularConstanteResorte(masaBase);

        double masa9m = 9.0 * masaBase;
        double k = resultadoK.getKPromedio();
        double omega = Math.sqrt(k / masa9m);

        double amplitud = estimarAmplitudMaxima();

        int n = (int) Math.round(tiempoFinal / paso) + 1;
        double[] tiempos = new double[n];
        double[] posiciones = new double[n];

        for (int i = 0; i < n; i++) {
            double t = i * paso;
            tiempos[i] = t;
            posiciones[i] = amplitud * Math.cos(omega * t);
        }

        Path outDir = Paths.get(directorioSalida);
        Files.createDirectories(outDir);

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Simulación masa 9m: x(t)")
                .xAxisTitle("t [s]")
                .yAxisTitle("x [m]")
                .build();

        chart.addSeries("x(t)", tiempos, posiciones);

        Path archivo = Paths.get(directorioSalida, "simulacion_masa_9m.png");
        BitmapEncoder.saveBitmap(chart, archivo.toString(), BitmapEncoder.BitmapFormat.PNG);
    }

    private static double estimarAmplitudMaxima() {
        List<DatosMovimiento.DatosParticula> particulas = DatosMovimiento.obtenerDatos();
        double max = 0.0;
        for (DatosMovimiento.DatosParticula datos : particulas) {
            for (double x : datos.getPosiciones()) {
                double abs = Math.abs(x);
                if (abs > max) {
                    max = abs;
                }
            }
        }
        return max;
    }
}
