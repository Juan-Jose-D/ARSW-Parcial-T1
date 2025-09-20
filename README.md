
# Repaso ARSW Tercio 1

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-brightgreen.svg)](https://maven.apache.org/)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen.svg)

## Descripción General

Proyecto de repaso para el primer parcial de Arquitectura de Software (ARSW).

---


## Ejecución del proyecto

### Requisitos

- Java 17+
- Maven 3+

1. **Clona el repositorio:**
	```bash
	git clone https://github.com/Juan-Jose-D/RepasoARSWTercio1.git
	cd Repaso
	```

2. **Compila y ejecuta con Maven:**
	```bash
	mvn spring-boot:run
	```

3. **Accede a la página web:**
	- Abre tu navegador y visita [http://localhost:8080/](http://localhost:8080)

---


## Arquitectura usada

El proyecto está construido con **Spring Boot** y sigue una arquitectura de capas:

- **Controller:** Expone los endpoints REST para interactuar con el frontend y los servicios.
- **Service:** Contiene la lógica de negocio, incluyendo ejemplos de concurrencia (hilos, locks, wait/notify) y consumo de APIs externas.
- **Static (Frontend):** Página web en `src/main/resources/static` con HTML y JavaScript para consumir los endpoints y mostrar resultados.

---


### Ejemplos incluidos

- Impresión de números usando hilos (pares/impares) y locks.
- Ejemplo de Productor-Consumidor usando `wait` y `notify`.
- Consumo de APIs externas (GET y POST) desde el frontend y backend.
- Procesamiento de datos concurrente y visualización en la web.

---


## Autor

Juan José D.