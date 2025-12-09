# Teoría de Maven

Maven es una herramienta de automatización para proyectos Java. Sirve para:

- Compilar el código fuente.
- Ejecutar pruebas unitarias.
- Manejar dependencias (librerías externas como XChart o JUnit).
- Empaquetar el proyecto en un archivo JAR.

## Conceptos básicos

- **POM (`pom.xml`)**: archivo de configuración del proyecto.
- **groupId**: identifica a la organización o grupo (por ejemplo `edu.proyectofinal`).
- **artifactId**: nombre del artefacto o proyecto.
- **version**: versión del proyecto.
- **dependencies**: lista de librerías externas que Maven descargará.

## Comandos típicos

```bash
mvn clean           # Borra la carpeta target (compilaciones anteriores)
mvn compile         # Compila el código fuente
mvn test            # Ejecuta las pruebas unitarias
mvn package         # Genera el JAR del proyecto
mvn exec:java       # Ejecuta la clase principal (usando exec-maven-plugin)
```

## ¿Por qué y cuándo usar Maven?

Es útil cuando:

- El proyecto crece y se usan varias dependencias.
- Se quiere automatizar compilación, pruebas y empaquetado.
- Se desea tener un proyecto estándar que pueda ser entendido por otros
  desarrolladores o herramientas.
