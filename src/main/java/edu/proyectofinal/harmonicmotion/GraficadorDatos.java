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
 * Genera gráficos de posición y velocidad en función del tiempo
 * para cada conjunto de datos.
 */
public class GraficadorDatos {

    public static void graficarTodasLasParticulas(String directorioSalida) throws IOException {
        List<DatosMovimiento.DatosParticula> particulas = DatosMovimiento.obtenerDatos();
        Path outDir = Paths.get(directorioSalida);
        Files.createDirectories(outDir);

        for (DatosMovimiento.DatosParticula datos : particulas) {
            crearGrafico(directorioSalida, datos, true);
            crearGrafico(directorioSalida, datos, false);
        }
    }

    private static void crearGrafico(String directorioSalida,
                                     DatosMovimiento.DatosParticula datos,
                                     boolean posicion) throws IOException {
        double[] tiempos = datos.getTiempos();
        double[] valores = posicion ? datos.getPosiciones() : datos.getVelocidades();

        String titulo = (posicion ? "Posición" : "Velocidad") + " vs tiempo (" + datos.getNombre() + ")";
        String ejeY = posicion ? "x [m]" : "v [m/s]";

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title(titulo)
                .xAxisTitle("t [s]")
                .yAxisTitle(ejeY)
                .build();

        chart.addSeries("datos", tiempos, valores);

        String prefijo = posicion ? "posicion" : "velocidad";
        Path archivo = Paths.get(directorioSalida, prefijo + "_" + datos.getNombre() + ".png");
        BitmapEncoder.saveBitmap(chart, archivo.toString(), BitmapEncoder.BitmapFormat.PNG);
    }
}
