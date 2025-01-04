package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.ColorMasterModel;
import com.example.demo.repository.ColorMasterRepository;

@Controller
public class ColorMasterController {
	
	@Autowired
	private ColorMasterRepository colorRep;
	
	public ColorMasterController(ColorMasterRepository colorRep) {
        this.colorRep = colorRep;
    }
	
	@GetMapping("/colors")
    public String showColorNames(Model model) {
        List<ColorMasterModel> colorNames = colorRep.findAll();
        model.addAttribute("colorNames", colorNames);
        model.addAttribute("colorObject", new ColorMasterModel());
        return "Colors"; 
    }

    @PostMapping("/colors/save")
    public String saveColorName(@ModelAttribute ColorMasterModel colorName) {
    	colorRep.save(colorName);
        return "redirect:/colors";
    }

    @PostMapping("/colors/delete/{id}")
    public String deleteColorName(@PathVariable Long id) {
    	colorRep.deleteById(id);
        return "redirect:/colors";
    }
	
}
