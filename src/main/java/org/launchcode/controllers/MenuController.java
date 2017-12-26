package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
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

        //if no errors, save the new menu item given by form to DB and generates ID for menu
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    //view menu handler
    //need to get a query param of menu id using @PathVariable (passed when form is processed)
    @RequestMapping(value = "view/{menuId}", method=RequestMethod.GET)
    public String viewMenu (Model model, @PathVariable int menuId){

        //fetch the particular method that we will want to look at
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", menu.getName());
        model.addAttribute("menu", menu);
        //model.addAttribute("cheeses", menu.getCheeses());
        //model.addAttribute("menuId", menu.getId());

        return "menu/view";
    }

    //add menu handler
    //display form
    @RequestMapping(value="add-item/{menuId}", method=RequestMethod.GET)
    public String addMenuItemForm (Model model, @PathVariable int menuId) {
        //menuId tell us which menu the user intends to add cheeses to

        //fetch menu
        Menu menu = menuDao.findOne(menuId);

        //create instance of Menu Item Form with the given Menu object and a list of all Cheese items
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());

        //pass form object into view
        model.addAttribute("form", form);

        //add title
        model.addAttribute("title","Add item to menu: " + menu.getName());

        return "menu/add-item";
    }

    //process add menu item form
    @RequestMapping(value ="add-item", method = RequestMethod.POST)
    public String addMenuItemForm(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {

        if (errors.hasErrors()){
            //return the form
            model.addAttribute("form", form);

            return "menu/add-item";
        }

        //Find cheese and menu by id
       Cheese aCheese = cheeseDao.findOne(form.getCheeseId());
        Menu aMenu = menuDao.findOne(form.getMenuId());

        //call addItem utility method to add item/cheese to the menu
        //check this- not working
        aMenu.addItem(aCheese);

        //saves the menu items to the menu
        menuDao.save(aMenu);

            return "redirect:/menu/view/" + aMenu.getId();
    }


}
