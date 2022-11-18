package com.example.serviceetudiants.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="NOM", length = 30)
    @NotEmpty
    private String nom;
    @NotEmpty
    private String prenom;
    @NotEmpty
    private String massar;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String filiere;
    @NotEmpty
    private String session;
    @NotEmpty
    private String semestre;
}