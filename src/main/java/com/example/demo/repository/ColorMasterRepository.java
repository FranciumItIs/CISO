package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ColorMasterModel;

public interface ColorMasterRepository extends JpaRepository<ColorMasterModel, Long>{

}
