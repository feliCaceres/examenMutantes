package com.example.parcial.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "dna")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Dna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isMutant")
    private boolean isMutant;

    @Column(name = "dna")
    private String dna;
}
