package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    //Fields

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //holds all the cheeses in the menu
    @ManyToMany
    private List<Cheese> cheeses;

    //constructors
    public Menu (String name){
        this.name = name;
    }

    public Menu (){
    }

    //utility methods

    //adds given item to list of cheeses
    public void addItem(Cheese item) {

    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }
}
