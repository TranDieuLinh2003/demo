package com.example.demo.district;

import com.example.demo.district.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
    List<District> findByNameContains(String name);

    List<District> findByCodeContains(String code);
}
