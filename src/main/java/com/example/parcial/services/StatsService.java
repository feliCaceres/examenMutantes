package com.example.parcial.services;

import com.example.parcial.dtos.StatsResponse;
import com.example.parcial.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRepository dnaRepository;

    @Autowired
    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public StatsResponse getStats() {
        long countMutantDna = dnaRepository.countByIsMutant(true);
        long countHumanDna = dnaRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 1 : (double) countMutantDna / countHumanDna;
        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}
