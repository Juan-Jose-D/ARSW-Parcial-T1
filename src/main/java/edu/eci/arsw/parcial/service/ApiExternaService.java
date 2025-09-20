package edu.eci.arsw.parcial.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ApiExternaService {
    public List<String> procesarDatosExterna() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&month=2009-01&outputsize=full&apikey=demo";

        List<Map<String, Object>> valores = restTemplate.getForObject(url, List.class);

        System.out.println(valores);;

        // Procesar: obtener solo los nombres
        List<String> nombres = new ArrayList<>();
        for (Map<String, Object> valor : valores) {
            nombres.add((String) valor.get("name"));
        }
        return nombres;
        }
}
