package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.ClassMasterModel;
import com.example.demo.repository.ClassMasterRepository;

@Controller
public class ClassMasterController {
	
	@Autowired
	private final ClassMasterRepository classRepo;

    public ClassMasterController(ClassMasterRepository classRepo) {
        this.classRepo = classRepo;
    }

    @GetMapping("/classes")
    public String showClassNames(Model model) {
        List<ClassMasterModel> classNames = classRepo.findAll();
        model.addAttribute("classNames", classNames);
        model.addAttribute("classObject", new ClassMasterModel());
        return "Classes"; 
    }

    @PostMapping("/classes/save")
    public String saveClassName(@ModelAttribute ClassMasterModel className) {
    	classRepo.save(className);
        return "redirect:/classes";
    }

    @PostMapping("/classes/delete/{id}")
    public String deleteClassName(@PathVariable Long id) {
    	classRepo.deleteById(id);
        return "redirect:/classes";
    }
	
}
