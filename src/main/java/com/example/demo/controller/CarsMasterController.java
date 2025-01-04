package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.CarModel;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ClassMasterRepository;
import com.example.demo.repository.ColorMasterRepository;
import com.example.demo.repository.CompanyMasterRepository;

@Controller 
public class CarsMasterController {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CompanyMasterRepository compRepository;
	
	@Autowired
	private ColorMasterRepository colRepository;
	
	@Autowired
	private ClassMasterRepository claRepository;
	
    public CarsMasterController(CarRepository carRepository, CompanyMasterRepository compRepository,
    		ColorMasterRepository colRepository, ClassMasterRepository claRepository) {
        this.carRepository = carRepository;
        this.compRepository = compRepository;
        this.colRepository = colRepository;
        this.claRepository = claRepository;
    }
    
    @GetMapping("/cars")
    public String showCars(Model model) {
    	List<CarModel> cars = carRepository.findAll();
    	model.addAttribute("cars", cars);
        model.addAttribute("companies", compRepository.findAll());
        model.addAttribute("colors", colRepository.findAll());
        model.addAttribute("classes", claRepository.findAll());
        model.addAttribute("carObject", new CarModel());
        return "Cars";
    }
    
    @GetMapping("/cars/search")
    public String carsSearch(Model model) {
        return "Cars :: carsTable";
    }

    @PostMapping("/cars/save")
    public String saveCar(@ModelAttribute CarModel car
//    		, @RequestParam("imageFile") MultipartFile imageFile
    		) throws IOException {
//    	if (!imageFile.isEmpty()) {
//            car.setImage(imageFile.getBytes());
//        }
        carRepository.save(car);
        return "redirect:/cars"; 
    }

    @PostMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars"; 
    }
    
    @GetMapping("/cars/edit/{id}")
    public String showEditCarForm(@PathVariable Long id, Model model) {
    	CarModel car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return "redirect:/cars";
        }
        
        List<CarModel> cars = carRepository.findAll();
    	model.addAttribute("cars", cars);
    	model.addAttribute("carObject", car);
        model.addAttribute("companies", compRepository.findAll());
        model.addAttribute("colors", colRepository.findAll());
        model.addAttribute("classes", claRepository.findAll());
        return "Cars"; 
    }
}