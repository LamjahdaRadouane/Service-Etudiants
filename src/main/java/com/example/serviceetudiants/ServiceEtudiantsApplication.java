package com.example.serviceetudiants;

import com.example.serviceetudiants.entities.Etudiant;
import com.example.serviceetudiants.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceEtudiantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEtudiantsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository){
        return args -> {
            etudiantRepository.save(
                    new Etudiant(null, "Hassan","Hassan", "M123456789", "Hassan@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Mohammed","Mohammed", "M123456789", "Mohammed@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Yasmine","Yasmine", "M123456789", "Yasmine@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hatim","Hatim", "M123456789", "Hatim@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Radouane","Radouane", "M123456789", "Radouane@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Farid","Farid", "M123456789", "Farid@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hanae","Hanae", "M123456789", "Hanae@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Soufiane","Soufiane", "M123456789", "Soufiane@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Imad","Imad", "M123456789", "Imad@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Ikram","Ikram", "M123456789", "Ikram@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Youssef","Youssef", "M123456789", "Youssef@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Youssef","Youssef", "M123456789", "Youssef@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Anass","Anass", "M123456789", "Anass@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hamza","Hamza", "M123456789", "Hamza@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hanae","Hanae", "M123456789", "Hanae@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hamid","Hamid", "M123456789", "Hamid@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hassan","Hassan", "M123456789", "Hassan@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Haitham","Haitham", "M123456789", "Haitham@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hicham","Hicham", "M123456789", "Hicham@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hind","Hind", "M123456789", "Hind@gmail.com", "SMI", "Autaumne", "S1"));
            etudiantRepository.save(
                    new Etudiant(null,"Hamza","Hamza", "M123456789", "Hamza@gmail.com", "SMI", "Autaumne", "S1"));

            etudiantRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }
}