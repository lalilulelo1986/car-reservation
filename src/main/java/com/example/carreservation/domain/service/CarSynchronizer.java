package com.example.carreservation.domain.service;

import org.springframework.stereotype.Service;

import java.util.WeakHashMap;

@Service
public class CarSynchronizer {
    private final WeakHashMap<Long, Object> locks = new WeakHashMap<>(100);

    synchronized Object getLockObject(Long id) {
        return locks.computeIfAbsent(id, k -> new Object());
    }
}
