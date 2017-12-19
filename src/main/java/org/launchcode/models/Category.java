package org.launchcode.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //property that joins the id to a list of cheeses (one category to many cheeses)
    @OneToMany
    @JoinColumn (name="category_id")
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

    //only need to set ID, DBMS will take care of getting ID
    public void setId(int id) {
        this.id = id;
    }
}
