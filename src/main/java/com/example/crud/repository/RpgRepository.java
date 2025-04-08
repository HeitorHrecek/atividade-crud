package com.example.crud.repository;

import com.example.crud.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RpgRepository extends JpaRepository<Personagem, Long> {
}
