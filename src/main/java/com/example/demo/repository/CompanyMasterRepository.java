package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CompanyMasterModel;

public interface CompanyMasterRepository extends JpaRepository<CompanyMasterModel, Long> {

}
