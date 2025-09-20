package edu.eci.arsw.parcial.service;

import java.util.ArrayList;
import java.util.List;

public class AccionesService {

    public List<String> generarNumeros(int a, int b) {
        List<String> resultado = new ArrayList<>();
        Object lock = new Object();
        int[] current = { a };

        Thread pares = new Thread(() -> {
            synchronized (lock) {
                while (current[0] <= b) {
                    if (current[0] % 2 == 0) {
                        resultado.add("Par: " + current[0]);
                        current[0]++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
                lock.notifyAll();
            }
        });

        Thread impares = new Thread(() -> {
            synchronized (lock) {
                while (current[0] <= b) {
                    if (current[0] % 2 != 0) {
                        resultado.add("Impar: " + current[0]);
                        current[0]++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
                lock.notifyAll();
            }
        });

        pares.start();
        impares.start();
        try {
            pares.join();
            impares.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return resultado;
    }
}
