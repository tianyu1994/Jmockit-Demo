package com.ty.java.jmockit.entity;

public abstract class Animal {
    private Integer id;
    protected Boolean isHealthy;
    private String name;
    private Integer age;
    private Double price;

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAge() {
        return age;
    }

    public abstract Boolean isHealthy();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (name != null ? !name.equals(animal.name) : animal.name != null) return false;
        return age != null ? age.equals(animal.age) : animal.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "isHealthy=" + isHealthy +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}';
    }
}
