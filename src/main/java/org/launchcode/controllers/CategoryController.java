package org.launchcode.controllers;


import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    //mechanism with which we interact with objects stored in the database
    //create a class that implements CategoryDao and put those objects in the categoryDao field on startup
    @Autowired
    private CategoryDao categoryDao;

    //index handler
    @RequestMapping(value = "")
    public String index (Model model) {

        model.addAttribute("title", "Category");
        model.addAttribute("category", categoryDao.findAll());

        return"category/index";
    }

    //add handler - form display
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCatForm (Model model) {

        model.addAttribute("title", "Add Category");
        //create new default constructor object and pass it
        model.addAttribute(new Category());

        return "category/add";
    }

    //add handler - form processing
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCatForm (Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "add";
        }

        categoryDao.save(category);
        return "redirect:";
    }


}
