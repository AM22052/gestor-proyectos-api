# API Gestor de Proyectos Simple

API REST desarrollada con **Spring Boot** para gestionar proyectos, tareas y empleados, permitiendo registrar las horas trabajadas en cada tarea y calcular el total de horas por tarea o por proyecto.

Proyecto de ciclo de la asignatura **Programación Orientada a Objetos** — Ingeniería en Desarrollo de Software, Facultad Multidisciplinaria de Occidente, Universidad de El Salvador (Ciclo II/2026).

## Integrantes

- César Ezequiel Aguilar Peralta
- José Emerson Alfaro Mendoza

**Cordinador de asignatura:** Ing. Erick Adiel Trigueros Jerez

## Descripción

Un proyecto tiene varias tareas asignadas a empleados. Los empleados registran las horas que trabajan en cada tarea, y la API permite consultar el total de horas por tarea o por proyecto.

## Entidades

- **Proyecto**: nombre, descripción, fecha de inicio
- **Tarea**: nombre, descripción, proyecto al que pertenece
- **Empleado**: nombre, email, cargo
- **RegistroHoras**: tarea, empleado, horas trabajadas, fecha

## Funcionalidades

- CRUD (GET, POST, PUT, DELETE) de proyectos, tareas, empleados y registros de horas
- Cálculo del total de horas por tarea
- Cálculo del total de horas por proyecto

## Tecnologías

- Java 17
- Spring Boot
- Maven
- Spring Data JPA
- MySQL
- Lombok

## Estado del proyecto

En desarrollo. Entrega #1: diseño (diagrama de clases, diagrama entidad-relación y casos de uso) y creación del repositorio.