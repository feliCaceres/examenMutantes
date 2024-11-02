package com.example.parcial.services;

import com.example.parcial.entities.Dna;
import com.example.parcial.repositories.DnaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {this.dnaRepository = dnaRepository;}

    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                char c =dna[i].charAt(j);
                if (i <= n - 4){ //chequea si hay espacio para buscar en vertical
                    if (dna[i+1].charAt(j) == c && dna[i+2].charAt(j) == c && dna[i+3].charAt(j) == c){
                        sequenceCount++;
                        if (sequenceCount > 1) {return true;}
                    }
                }
                if (j <= n - 4){ //chequea si hay espacio para buscar en horizontal
                    if (dna[i].charAt(j+1) == c && dna[i].charAt(j+2) == c && dna[i].charAt(j+3) == c){
                        sequenceCount++;
                        if (sequenceCount > 1) {return true;}
                    }
                }
                if (i <= n - 4 && j <= n - 4){ //chequea si hay espacio para buscar en diagonal a la derecha
                    if (dna[i+1].charAt(j+1) == c && dna[i+2].charAt(j+2) == c && dna[i+3].charAt(j+3) == c){
                        sequenceCount++;
                        if (sequenceCount > 1) {return true;}
                    }
                }
                if (i <= n - 4 && j >= 3){  //chequea si hay espacio para buscar en diagonal a la izquierda
                    if (dna[i+1].charAt(j-1) == c && dna[i+2].charAt(j-2) == c && dna[i+3].charAt(j-3) == c){
                        sequenceCount++;
                        if (sequenceCount > 1) {return true;}
                    }
                }
            }
        }
        return false;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();
        }
        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder().dna(dnaSequence).isMutant(isMutant).build();
        dnaRepository.save(dnaEntity);
        return isMutant;
    }
}
