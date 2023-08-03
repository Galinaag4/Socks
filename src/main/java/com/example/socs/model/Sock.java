package com.example.socs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Objects;

@Entity
public class Sock {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String color;
    private int cottonPart;
    private int quantity;
    private static int counter;

    public Sock(String color, int cottonPart, int quantity) {
        this.id = counter++;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;

    }

    public Sock() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Sock.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sock sock)) return false;
        return getId() == sock.getId() && getCottonPart() == sock.getCottonPart() && getQuantity() == sock.getQuantity() && Objects.equals(getColor(), sock.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getCottonPart(), getQuantity());
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
