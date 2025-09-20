
# Repaso ARSW Tercio 1

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-brightgreen.svg)](https://maven.apache.org/)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen.svg)

## Descripción General

Proyecto del primer parcial de Arquitectura de Software (ARSW).

---

El proyecto llama a una API externa y puede enviar diferentes parámetros. Como el identificador de una acción, como "IBM".
Esto se hace mediante un filtro en el app.js, la idea es enviar por parámetro a la API lo que la persona pone en la página web, es extensible para todos los parámetros que sean necesarios.


![alt text](/img/image.png)


Se implementó de esta manera:

```js
function consumirApiExterna() {
	event.preventDefault();
	const symbol = document.getElementById('id').value;
	const base_url = 'https://www.alphavantage.co/query?';

	document.getElementById('comboBox').addEventListener('change', function() {
		document.getElementById('comboBox').value = this.functionType;
	});

	const funtion = 'function=' +  document.getElementById('comboBox').value || 'TIME_SERIES_INTRADAY&' + '&';
	const urlsymbol = 'symbol=' + symbol || 'IBM';
	const interval = '&interval=5min&month=2009-01&';
	const outputsize = 'outputsize=full&';
	const apikey = 'apikey=demo' ;


	fetch(base_url + funtion + urlsymbol + interval + outputsize + apikey)
		.then(response => response.json())
		.then(data => {
			const contenedor = document.getElementById('api-externa');
			contenedor.innerHTML = '<h3>API externa:</h3>';
			const div = document.createElement('div');

			const objetoJS = JSON.parse(data);
			const metaData = objetoJS["Meta Data"];
			const TimeSeries = objetoJS["Time Series (5min)"]; 

			console.log(metaData);
			console.log(TimeSeries);

			div.textContent = metaData;
			div.textContent = TimeSeries;
			contenedor.appendChild(div);
		})
		.catch(error => {
			console.log(" ");
		});
}
```

También se implmentó un servicio de backend que procesa solicitudes concurrentemente mediante el uso de hilos.

```java
@Service
public class ConcurrentService {

    private final RestTemplate restTemplate;
    
    public ConcurrentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void realizarSolicitudesConcurrentes(){
        Thread solicitud = new Thread(() -> {
            procesarDatosExterna();
        });

        solicitud.start();

    }

    public CompletableFuture<List<String>> procesarDatosExterna() {
        return CompletableFuture.supplyAsync(() -> {
            String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";
            
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            List<Map<String, Object>> timeSeries = (List<Map<String, Object>>) response.get("Time Series (5min)");

            List<String> valores = new ArrayList<>();
            if (timeSeries != null) {
                for (Map<String, Object> element : timeSeries) {
                    valores.add((String) element.get("Meta Data"));
                }
            }
            return valores;
        });
    }
}
```

## Ejecución del proyecto

### Requisitos

- Java 17+
- Maven 3+

1. **Clona el repositorio:**
	```bash
	git clone https://github.com/Juan-Jose-D/ARSW-Parcial-T1.git
	cd ARSW-ParcialT1
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



## Autor

Juan José D.