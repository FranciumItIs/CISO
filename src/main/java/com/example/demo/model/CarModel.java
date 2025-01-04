package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class CarModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String carName;
    
	@Lob
	private byte[] image;
	
    @ManyToOne
    @JoinColumn(name="companyId", nullable=false)
    private CompanyMasterModel companyId;
    
    @ManyToOne
    @JoinColumn(name="classId", nullable=false)
    private ClassMasterModel classId;
    
    @ManyToOne
    @JoinColumn(name="colorId", nullable=false)
    private ColorMasterModel colorId;
}
