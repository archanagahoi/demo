package com.example.movemedicalhierarchy.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.movemedicalhierarchy.model.MoveMedicalProduct;

@Repository
public interface MoveMedicalProductRepository extends JpaRepository<MoveMedicalProduct, Long>, MoveMedicalCustomRepository {

	Optional<MoveMedicalProduct> findById(Long id);   

}
