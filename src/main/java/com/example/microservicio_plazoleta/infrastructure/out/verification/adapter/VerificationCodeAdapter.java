package com.example.microservicio_plazoleta.infrastructure.out.verification.adapter;

import com.example.microservicio_plazoleta.domain.api.IVerificationCodePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
public class VerificationCodeAdapter implements IVerificationCodePort {

    private final Set<Integer> generatedPins = new HashSet<>();
    private final Random random = new Random();

    @Override
    public int generateVerificationCode() {
        int pin;
        do {
            pin = random.nextInt(10000); // Genera un número entre 0 y 9999
        } while (generatedPins.contains(pin));
        generatedPins.add(pin);
        return pin;
    }

}
