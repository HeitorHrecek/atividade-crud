package com.example.crud.repository;

import com.example.crud.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long> {
    Optional<ItemMagico> findById(Long id);
}