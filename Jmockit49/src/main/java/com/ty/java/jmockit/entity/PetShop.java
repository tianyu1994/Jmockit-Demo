package com.ty.java.jmockit.entity;

import java.util.ArrayList;
import java.util.List;

public class PetShop{
    private String shopName;
    private List<Animal> animalList = new ArrayList<>();
    private Boolean isOpen;

    public PetShop(String shopName) {
        this.shopName = shopName;
    }

    public static Integer getMaxAnimalSize() {
        return null;
    }

    public Boolean getIsOpen(){
        return isOpen;
    }

    public String getShopName() {
        return shopName;
    }

    public Animal getAnimal(String id){
        Animal animal = queryById(id);
        return animal;
    }

    private Animal queryById(String id) {
        return null;
    }

    public Boolean add(Animal animal){
        if (animal == null) {
            throw new NullPointerException("add animal failed, animal is null");
        }
        return animalList.add(animal);
    }

    public void showAnimals(){
        for (Animal animal : animalList) {
            System.out.println(animal);
        }
    }

    public List<Animal> getAnimalList() {
        return new ArrayList<>(animalList);
    }

}
