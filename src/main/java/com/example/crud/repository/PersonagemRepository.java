package com.example.crud.repository;

import com.example.crud.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Optional<Personagem> findById(Long id);
}
