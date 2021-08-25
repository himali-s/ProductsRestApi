package com.example.CentricSoftware.Data;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    Date date = new Date(System.currentTimeMillis());
    private String description;
    private String brand;
//    private List<String> tags;
    @ElementCollection
    private List<String> tags = new ArrayList<String>();
    private String category;
    Product(){

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    Product(String name, String description, List<String> tags){
        this.name = name;
        this.description = description;
        this.tags = tags;
    }
//    Product(String name, String description, String category){
//        this.name = name;
//        this.description = description;
//        this.category = category;
//
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public String getDate() {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String text = sdf.format(date);

        return text;
    }

//    public void setDate(Date date) {
//        this.date = date;
//    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}

