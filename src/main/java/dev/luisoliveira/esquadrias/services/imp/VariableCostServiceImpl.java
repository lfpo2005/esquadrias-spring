package dev.luisoliveira.esquadrias.services.imp;

import dev.luisoliveira.esquadrias.models.VariableCostModel;
import dev.luisoliveira.esquadrias.repositories.VariableCostRepository;
import dev.luisoliveira.esquadrias.services.VariableCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VariableCostServiceImpl implements VariableCostService {

    @Autowired
    VariableCostRepository variableCostRepository;

    @Override
    public VariableCostModel save(VariableCostModel variableCostModel) {
        return variableCostRepository.save(variableCostModel);
    }

    @Override
    public boolean existsByNameCostsAndUser_UserId(String nameCosts, UUID userId) {
        return variableCostRepository.existsByNameCostsAndUser_UserId(nameCosts, userId);
    }

    @Override
    public List<VariableCostModel> findAllByUserId(UUID userId) {
        return variableCostRepository.findAllByUser_UserId(userId);
    }

    @Override
    public Optional<VariableCostModel> findById(UUID variableCostId) {
        return variableCostRepository.findById(variableCostId);
    }

    @Override
    public void delete(VariableCostModel variableCostModel) {

    }
}
