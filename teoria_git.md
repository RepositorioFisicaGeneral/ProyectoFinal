# Teoría de Git

Git es un sistema de control de versiones distribuido. Permite:

- Guardar el historial de cambios de un proyecto.
- Trabajar en equipo sin sobrescribir el trabajo de otras personas.
- Crear ramas independientes para nuevas funciones o experimentos.
- Volver a versiones anteriores del código si algo sale mal.

## Conceptos básicos

- **Repositorio**: carpeta controlada por Git.
- **Commit**: foto del estado de los archivos en un momento concreto.
- **Rama (branch)**: línea de desarrollo independiente.
- **Merge**: operación para unir los cambios de una rama en otra.
- **Remoto (remote)**: repositorio alojado en un servidor como GitHub.

## Comandos útiles

```bash
git init                 # Crear un nuevo repositorio local
git clone URL            # Clonar un repositorio existente
git status               # Ver el estado de los archivos
git add archivo          # Preparar archivo para commit
git commit -m "mensaje"  # Crear un commit
git log                  # Ver historial de commits
git branch               # Ver ramas
git checkout -b nombre   # Crear y cambiar a una nueva rama
git checkout nombre      # Cambiar de rama
git merge nombre         # Mezclar otra rama en la rama actual
git push                 # Subir cambios al remoto
git pull                 # Traer y mezclar cambios del remoto
```

## ¿Por qué y cuándo usar Git?

Se usa siempre que se quiera:

- Trabajar con seguridad, pudiendo deshacer cambios.
- Colaborar con varias personas sobre el mismo proyecto.
- Mantener un historial claro de qué se hizo y por qué.
