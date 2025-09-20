package edu.eci.arsw.parcial.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BufferService {
    public List<Integer> ejecutarProductorConsumidor(int cantidad) {
        List<Integer> buffer = new ArrayList<>();
        Object lock = new Object();
        List<Integer> resultado = new ArrayList<>();

        Thread productor = new Thread(() -> {
            for (int i = 1; i <= cantidad; i++) {
                synchronized (lock) {
                    buffer.add(i);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread consumidor = new Thread(() -> {
            for (int i = 1; i <= cantidad; i++) {
                synchronized (lock) {
                    while (buffer.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int valor = buffer.remove(0);
                    resultado.add(valor);
                    lock.notifyAll();
                }
            }
        });

        productor.start();
        consumidor.start();
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return resultado;
    }
}
