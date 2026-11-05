# SGPE - Sistema de Gestión de Préstamos de Equipos

Proyecto desarrollado para el curso SC-403 Desarrollo de Aplicaciones Web y Patrones.

## Objetivo

Desarrollar una aplicación web que permita gestionar el préstamo de equipos, desde la solicitud hasta la devolución.

## Integrantes

- Hugo Josué Boza Castillo
- Juan José Castro Zúñiga
- Elías León Montero
- Andrés Fernando Sánchez Morales

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Thymeleaf
- Bootstrap
- Hibernate/JPA
- MySQL
- GitHub

## Estado actual

Avance 2 - Semana 9.

Actualmente se cuenta con:

- Estructura del proyecto basada en MVC.
- Conexión funcional con MySQL mediante Hibernate/JPA.
- Uso de Thymeleaf y Bootstrap en las vistas.
- Inicio y cierre de sesión.
- Control de usuarios y roles.
- Consulta y búsqueda de equipos.
- Consulta del detalle de equipos.
- Consulta de disponibilidad de equipos.
- Registro de solicitudes de préstamo.
- Selección y validación de fechas para préstamos.
- Consulta de solicitudes realizadas por el usuario.
- Registro y edición de equipos.
- Aprobación de préstamos.
- Repositorio GitHub actualizado.
- Trabajo colaborativo mediante ramas y Pull Requests.

## Historias de usuario implementadas

Las siguientes historias de usuario se encuentran implementadas actualmente:

- HU01 - Iniciar sesión.
- HU02 - Cerrar sesión.
- HU06 - Consultar equipos.
- HU07 - Buscar equipos.
- HU09 - Consultar detalle de equipos.
- HU10 - Consultar disponibilidad.
- HU11 - Solicitar préstamo.
- HU12 - Seleccionar y validar fechas del préstamo.
- HU13 - Consultar mis solicitudes.
- HU16 - Registrar equipo.
- HU17 - Editar equipo.
- HU18 - Aprobar préstamo.

Esto representa 12 de las 20 historias de usuario definidas para el proyecto.

## Base de datos

El proyecto utiliza MySQL.

El repositorio contiene el script:

`SGPE_script_BD.sql`

Este script permite crear la estructura necesaria de la base de datos y cargar los datos iniciales utilizados para las pruebas.

Las contraseñas de los usuarios se almacenan utilizando BCrypt.

### Usuarios de prueba

Administrador:

- Usuario: `admin`
- Contraseña: `Admin123.`

Usuario solicitante:

- Usuario: `andres`
- Contraseña: `Usuario123.`

Usuario solicitante:

- Usuario: `maria`
- Contraseña: `Usuario123.`

## Trabajo con ramas

Se utiliza una rama principal llamada:

`main`

Cada integrante realiza sus cambios desde una rama de trabajo propia.

Ejemplos:

- `Juan`
- `hugo`
- `feature/andres`

Los cambios desarrollados en las ramas se integran a `main` mediante Pull Requests.

## Acuerdos del equipo

- No trabajar directamente sobre `main`.
- Cada integrante debe trabajar desde su propia cuenta de GitHub.
- Cada integrante debe realizar sus cambios desde su rama de trabajo.
- Antes de comenzar a desarrollar se debe actualizar el proyecto con los cambios de `main`.
- Los commits deben describir el trabajo realizado.
- Cuando el trabajo esté listo, se debe crear un Pull Request hacia `main`.
- Otro integrante debe revisar los cambios antes de realizar el merge.
- Todos los integrantes deben mantener actualizado su proyecto con los cambios de `main`.

## Relación entre historias y tareas

Las historias de usuario se encuentran registradas como Issues dentro del repositorio de GitHub.

Cada Issue representa una funcionalidad que debe ser desarrollada por uno de los integrantes.

Ejemplos:

- `HU10 - Consultar disponibilidad`
- `HU11 - Solicitar préstamo`
- `HU12 - Seleccionar y validar fechas`
- `HU16 - Registrar equipo`
- `HU17 - Editar equipo`
- `HU18 - Aprobar préstamo`

Los Issues permiten identificar las funcionalidades pendientes, las funcionalidades en desarrollo y las historias que ya fueron implementadas.

## Commits

Se utilizan mensajes sencillos y descriptivos.

## Ejecución del proyecto

1. Clonar el repositorio.
2. Ejecutar el script `SGPE_script_BD.sql` en MySQL.
3. Verificar la configuración de conexión en `application.properties`.
4. Abrir el proyecto en Apache NetBeans.
5. Utilizar JDK 21.
6. Ejecutar el proyecto Spring Boot.
7. Abrir en el navegador:

`http://localhost:8080`

8. Iniciar sesión utilizando alguno de los usuarios de prueba.

## Trabajo colaborativo

El desarrollo del proyecto se realiza mediante GitHub.

Cada integrante cuenta con aportes dentro del repositorio mediante:

- Ramas de trabajo.
- Commits.
- Issues.
- Pull Requests.
- Revisión de cambios.
- Integración controlada hacia la rama `main`.

El repositorio se mantiene actualizado con los avances realizados por cada integrante.

## Material complementario: prototipo navegable y archivo de diseño en Figma. 

## Prototipo navegable

https://www.figma.com/proto/4JINLBWM0u1wLHN6QnQHp5/Proyecto-SFPE?node-id=11-2&starting-point-node-id=11%3A2

## Archivo de diseño

https://www.figma.com/design/4JINLBWM0u1wLHN6QnQHp5/Proyecto-SFPE?node-id=0-1&t=jDYCaR7mjbCCgike-1

## GitHub del proyecto

https://github.com/elyeye4/SGPE_Grupo4.git
