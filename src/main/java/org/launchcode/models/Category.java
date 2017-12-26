package org.launchcode.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    //says that the Id field is the primary id
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //property that joins the id to a list of cheeses (one category to many cheeses, each cheese can have only
    //one category)
    @OneToMany
    @JoinColumn (name="category_id") //category_id is added automatically, it specifies which cheese belongs to a given category
    //list of items/cheese in a given category
    private List<Cheese> cheeses = new ArrayList<>();

    public Category (String name) {
        this.name = name;

    }

    public Category() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //only need to get ID, DBMS will take care of setting ID

    public int getId() {return id;}

    public List<Cheese> getCheeses() {
        return cheeses;
    }
}
