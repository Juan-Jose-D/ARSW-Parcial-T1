package edu.eci.arsw.parcial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import edu.eci.arsw.parcial.service.ApiExternaService;
import edu.eci.arsw.parcial.service.HilosService;
import edu.eci.arsw.parcial.service.BufferService;

@RestController
public class AppController {

	@Autowired
	private HilosService hilosService;
    @Autowired
    private ApiExternaService apiExternaService;
    @Autowired
    private BufferService bufferService;

    
    @GetMapping("/buffer")
    public List<Integer> getBuffer() {
        // Ejecuta el ejemplo de productor-consumidor con 10 elementos
        return bufferService.ejecutarProductorConsumidor(10);
    }

	@GetMapping("/numeros")
	public List<String> getNumeros() {
		int a = 1;
		int b = 100;
		return hilosService.generarNumeros(a, b);
	}

    @GetMapping("/procesar")
    public ResponseEntity<?> procesar() {
        // Llamas al servicio y obtienes la respuesta de la API externa
        List<String> respuesta = apiExternaService.procesarDatosExterna();

        // Aquí puedes procesar la respuesta antes de devolverla
        // Por ejemplo, extraer solo los nombres de los usuarios
        // Si la respuesta es una lista de usuarios:
        if (respuesta instanceof List) {
            List<String> usuarios = (List<String>) respuesta;
            return ResponseEntity.ok(usuarios);
        }

        // Si la respuesta es un solo usuario, simplemente retorna la respuesta
        return ResponseEntity.ok(respuesta);
    }
}
