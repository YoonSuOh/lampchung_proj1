package com.example.lamp.repository;

import com.example.lamp.entity.VersicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersicleRepository extends JpaRepository<VersicleEntity, Integer> {
    List<VersicleEntity> findByParagraph(int pargraph);
}
