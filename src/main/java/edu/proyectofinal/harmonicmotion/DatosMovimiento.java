package edu.proyectofinal.harmonicmotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Lee los datos experimentales de un archivo CSV dentro de resources
 * y los organiza por partícula (masa).
 */
public class DatosMovimiento {

    public static class DatosParticula {
        private final String nombre;
        private final double masaEnUnidadesDeM;
        private final double[] tiempos;
        private final double[] posiciones;
        private final double[] velocidades;

        public DatosParticula(String nombre,
                              double masaEnUnidadesDeM,
                              double[] tiempos,
                              double[] posiciones,
                              double[] velocidades) {
            if (tiempos.length != posiciones.length || tiempos.length != velocidades.length) {
                throw new IllegalArgumentException("Los arreglos deben tener la misma longitud.");
            }
            this.nombre = nombre;
            this.masaEnUnidadesDeM = masaEnUnidadesDeM;
            this.tiempos = tiempos.clone();
            this.posiciones = posiciones.clone();
            this.velocidades = velocidades.clone();
        }

        public String getNombre() {
            return nombre;
        }

        /**
         * Masa en unidades de m (por ejemplo 4m, 3m, etc.).
         */
        public double getMasaEnUnidadesDeM() {
            return masaEnUnidadesDeM;
        }

        public double[] getTiempos() {
            return tiempos.clone();
        }

        public double[] getPosiciones() {
            return posiciones.clone();
        }

        public double[] getVelocidades() {
            return velocidades.clone();
        }
    }

    private static class Medicion {
        final double masaUnidadesM;
        final double tiempo;
        final double posicion;
        final double velocidad;

        Medicion(double masaUnidadesM, double tiempo, double posicion, double velocidad) {
            this.masaUnidadesM = masaUnidadesM;
            this.tiempo = tiempo;
            this.posicion = posicion;
            this.velocidad = velocidad;
        }
    }

    private static final String RUTA_RECURSO = "/data/datos_masas.csv";

    /**
     * Lee el archivo CSV y devuelve la lista de partículas con sus datos.
     */
    public static List<DatosParticula> obtenerDatos() {
        try (InputStream inputStream = DatosMovimiento.class.getResourceAsStream(RUTA_RECURSO)) {
            if (inputStream == null) {
                throw new IllegalStateException("No se encontró el archivo de datos: " + RUTA_RECURSO);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                Map<String, List<Medicion>> datosPorNombre = new LinkedHashMap<>();
                Map<String, Double> masaPorNombre = new LinkedHashMap<>();

                String linea = reader.readLine(); // Encabezado
                while ((linea = reader.readLine()) != null) {
                    if (linea.isBlank()) {
                        continue;
                    }
                    String[] partes = linea.split(",");
                    if (partes.length < 5) {
                        continue;
                    }
                    String nombre = partes[0].trim();
                    double masaUnidadM = Double.parseDouble(partes[1].trim());
                    double t = Double.parseDouble(partes[2].trim());
                    double x = Double.parseDouble(partes[3].trim());
                    double v = Double.parseDouble(partes[4].trim());

                    Medicion medicion = new Medicion(masaUnidadM, t, x, v);

                    datosPorNombre.computeIfAbsent(nombre, k -> new ArrayList<>()).add(medicion);
                    masaPorNombre.putIfAbsent(nombre, masaUnidadM);
                }

                List<DatosParticula> resultado = new ArrayList<>();
                for (Map.Entry<String, List<Medicion>> entry : datosPorNombre.entrySet()) {
                    String nombre = entry.getKey();
                    List<Medicion> lista = entry.getValue();

                    int n = lista.size();
                    double[] tiempos = new double[n];
                    double[] posiciones = new double[n];
                    double[] velocidades = new double[n];

                    for (int i = 0; i < n; i++) {
                        Medicion m = lista.get(i);
                        tiempos[i] = m.tiempo;
                        posiciones[i] = m.posicion;
                        velocidades[i] = m.velocidad;
                    }

                    double masaUnidadM = masaPorNombre.get(nombre);
                    resultado.add(new DatosParticula(nombre, masaUnidadM, tiempos, posiciones, velocidades));
                }

                return resultado;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error al leer los datos del archivo CSV.", e);
        }
    }
}
