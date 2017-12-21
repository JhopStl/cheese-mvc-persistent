package org.launchcode.controllers;


import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="menu")
public class MenuController {


    //initialize these, put objects in these fields upon startup
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    //handlers

    //index handler/list menus
    @RequestMapping(value = "")
    public String index (Model model){

        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "menu/index";
    }

    //display add Form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenuForm (Model model){
        //creating new menu for the form to use
        model.addAttribute(new Menu());
        model.addAttribute("title", "Add Menu");

        return "menu/add";
    }

    //process add Form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuForm (@ModelAttribute @Valid Menu menu, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        //if no errors, save the new menu item given by form to DB has object
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    //view menu handler
    //need to get a query param of menu id using @PathVariable (passed when form is processed)
    @RequestMapping(value = "view/{menuId}", method=RequestMethod.GET)
    public String viewMenu (Model model, @PathVariable int menuId){

        //fetch the particular method that we will want to look at
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("Title", menu.getName());
        model.addAttribute("menu", menu);
        //model.addAttribute("cheeses", menu.getCheeses());
        //model.addAttribute("menuId", menu.getId());

        return "menu/view";
    }


}
