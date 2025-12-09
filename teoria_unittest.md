# Teoría de Pruebas Unitarias

Las **pruebas unitarias** son pequeños programas que verifican que una parte
concreta del código (una *unidad*, por ejemplo una función) funciona como se
espera.

## Objetivos

- Detectar errores de forma temprana.
- Poder refactorizar (mejorar) el código con confianza.
- Documentar el comportamiento esperado de las funciones.

## JUnit 5

En este proyecto se usa **JUnit 5 (Jupiter)**:

- Cada prueba es un método anotado con `@Test`.
- Se utilizan aserciones como `assertEquals`, `assertTrue`, `assertThrows`, etc.

Ejemplo sencillo:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EjemploTest {

    @Test
    void sumaDeValores() {
        int resultado = 2 + 3;
        assertEquals(5, resultado);
    }
}
```

Para ejecutar las pruebas con Maven:

```bash
mvn test
```
