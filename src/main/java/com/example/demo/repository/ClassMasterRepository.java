package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ClassMasterModel;

public interface ClassMasterRepository extends JpaRepository<ClassMasterModel, Long>{

}
