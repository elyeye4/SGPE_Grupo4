# SGPE - Sistema de Gestión de Préstamos de Equipos

Proyecto desarrollado para el curso SC-403 Desarrollo de Aplicaciones Web y Patrones.

## Objetivo

Desarrollar una aplicación web que permita gestionar el préstamo de equipos, desde la solicitud hasta la devolución.

## Integrantes

- Hugo Josué Boza Castillo
- Juan José Castro Zúñiga
- Elías León Montero
- Andrés Fernando Sánchez Morales

## Tecnologías previstas

- Java 21
- Spring Boot
- Thymeleaf
- Bootstrap
- Hibernate/JPA
- MySQL
- GitHub

## Estado actual

Avance 1 - Semana 5.

Actualmente se cuenta con:

- Definición del proyecto.
- 20 historias de usuario.
- Criterios de aceptación.
- Backlog priorizado.
- Prototipo de interfaces.
- Mapa de navegación.
- Modelo preliminar de datos.
- Estructura inicial del proyecto.

## Trabajo con ramas

Se utilizará una rama principal llamada:

`main`

Cada funcionalidad se trabajará en una rama independiente.

Ejemplos:

- `feature/login`
- `feature/equipos`
- `feature/prestamos`
- `feature/solicitudes`
- `feature/internacionalizacion`

Para correcciones se podrán utilizar ramas como:

- `fix/validacion-fechas`
- `fix/error-navegacion`

## Acuerdos del equipo

- No trabajar directamente sobre `main`.
- Cada integrante debe trabajar desde su propia cuenta de GitHub.
- Los cambios deben realizarse en una rama.
- Los commits deben describir el trabajo realizado.
- Cuando una funcionalidad esté lista, se debe crear un Pull Request.
- Otro integrante debe revisar el Pull Request antes de unirlo a `main`.
- Todos los integrantes deben mantener actualizado su proyecto con los cambios de `main`.

## Relación entre historias y tareas

Las historias de usuario servirán como base para crear las tareas del proyecto.

Ejemplos:

- Historia: `HU11 - Solicitar préstamo`
- Tarea: Crear formulario de solicitud de préstamo
- Rama: `feature/hu11-solicitar-prestamo`

- Historia: `HU16 - Registrar equipo`
- Tarea: Crear formulario para registrar equipos
- Rama: `feature/hu16-registrar-equipo`

## Commits

Se utilizarán mensajes sencillos y descriptivos.

Ejemplos:

- `feat: agregar pantalla de equipos`
- `feat: agregar solicitud de préstamo`
- `fix: corregir validación de fechas`
- `docs: actualizar README`

## Ejecución preliminar

1. Clonar el repositorio.
2. Abrir el proyecto en Apache NetBeans.
3. Utilizar JDK 21.
4. Ejecutar el proyecto Spring Boot.
5. Abrir en el navegador:

`http://localhost:8080`

## Próximos pasos

- Implementar las historias de mayor prioridad.
- Crear las entidades del sistema.
- Conectar la aplicación con MySQL o base de datos que usemos.
- Implementar los módulos de préstamo y devolución.
- Incorporar autenticación y roles.
- Continuar el trabajo mediante ramas y Pull Requests.
