package com.example.demo.repository;

import com.example.demo.model.Buah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuahRepository extends JpaRepository<Buah, Long> {
    List<Buah> findAllByIsDeletedOrderByCreatedDate(Boolean isDeleted);
    Optional<Buah> findByIdAndIsDeleted(Long id, Boolean isDeleted);
    Optional<Buah> findByNameIgnoreCaseAndIsDeleted(String name, Boolean isDeleted);
}
