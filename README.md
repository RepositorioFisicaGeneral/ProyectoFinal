# Proyecto Laboratorio de Física General

Repositorio Maven en Java para resolver el proyecto de movimiento armónico simple.

El proyecto ya incluye:

- Lectura de datos experimentales desde un archivo CSV.
- Gráficas de posición y velocidad en función del tiempo usando **XChart**.
- Ajuste por **método de mínimos cuadrados**.
- Cálculo de la constante elástica equivalente `k` y su error.
- Cálculo del período de oscilación para una masa `9m`.
- Simulación numérica del movimiento para `9m` y generación de un gráfico.
- Pruebas unitarias con **JUnit 5**.
- Workflow de **GitHub Actions** para integración continua.

## Requisitos

- Java 17 (JDK).
- Maven instalado y en el `PATH`.
- Git y cuenta en GitHub (para subir el repositorio y crear ramas).

## Comandos básicos

Compilar y ejecutar pruebas:

```bash
mvn -q test
```

Ejecutar el programa principal (genera gráficos y muestra resultados en consola):

```bash
mvn -q exec:java
```

Los gráficos se guardan en la carpeta `charts/` en la raíz del proyecto.

## Flujo sugerido de trabajo en Git

1. Crea el repositorio en GitHub y sube este proyecto a la rama `main`.
2. Crea las ramas de desarrollo para la semana 4:

```bash
git checkout -b branch_dev_1
git push -u origin branch_dev_1

git checkout main
git checkout -b branch_dev_2
git push -u origin branch_dev_2

git checkout main
git checkout -b branch_dev_3
git push -u origin branch_dev_3
```

3. En cada rama implementa o modifica la parte que te corresponda
   (coeficiente A, coeficiente B, errores) y luego abre **Pull Requests**
   desde cada `branch_dev_*` hacia `main` para que tu profesor pueda revisar.
