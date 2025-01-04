package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CarModel;

public interface CarRepository extends JpaRepository<CarModel, Long>{

}
