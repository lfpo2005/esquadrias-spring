package dev.luisoliveira.esquadrias.services;

import dev.luisoliveira.esquadrias.exceptions.CalculationException;
import dev.luisoliveira.esquadrias.models.CalculatorSumModel;

import java.util.Optional;
import java.util.UUID;

public interface CalculatorService {

    CalculatorSumModel totalMonthly(UUID userId) throws CalculationException;
    Optional<CalculatorSumModel> findById(UUID userId);

}
