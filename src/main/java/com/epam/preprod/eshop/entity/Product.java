package com.epam.preprod.eshop.entity;

import com.epam.preprod.eshop.anotation.Init;
import com.epam.preprod.eshop.anotation.Service;

import java.io.Serializable;

@Service
public abstract class Product implements Serializable {

    private long id;

    private long price;

    private String name;

    private String description;

    public Product(long id, long price, String name, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Product() {
    }

    @Init(resource = "productName")
    public void setName(String name) {
        this.name = name;
    }

    @Init(resource = "productId")
    public void setId(long id) {
        this.id = id;
    }

    @Init(resource = "productPrice")
    public void setPrice(long price) {
        this.price = price;
    }

    @Init(resource = "productDescription")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
