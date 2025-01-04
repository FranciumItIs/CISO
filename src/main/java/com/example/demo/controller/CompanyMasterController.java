package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.CarModel;
import com.example.demo.model.CompanyMasterModel;
import com.example.demo.repository.CompanyMasterRepository;

@Controller
public class CompanyMasterController {
	
	@Autowired
	private final CompanyMasterRepository companyNameRepository;

    public CompanyMasterController(CompanyMasterRepository companyNameRepository) {
        this.companyNameRepository = companyNameRepository;
    }

    @GetMapping("/companies")
    public String showCompanyNames(Model model) {
        List<CompanyMasterModel> companyNames = companyNameRepository.findAll();
        model.addAttribute("companyNames", companyNames);
        model.addAttribute("companyObject", new CompanyMasterModel());
        return "Companies"; 
    }

    @PostMapping("/companies/save")
    public String saveCompanyName(@ModelAttribute CompanyMasterModel companyName) {
        companyNameRepository.save(companyName);
        return "redirect:/companies";
    }
    
    @GetMapping("/companies/edit/{id}")
    public String showEditCompanyForm(@PathVariable Long id, Model model) {
        CompanyMasterModel company = companyNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        model.addAttribute("companyName", company);
        model.addAttribute("companyObject", new CompanyMasterModel());
        return "Companies"; 
    }
    
    @PostMapping("/companies/delete/{id}")
    public String deleteCompanyName(@PathVariable Long id) {
        companyNameRepository.deleteById(id);
        return "redirect:/companies";
    }
	
}
