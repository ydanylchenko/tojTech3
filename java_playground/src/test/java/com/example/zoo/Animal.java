package com.example.zoo;

public class Animal {

    int age;
    boolean isHungry = true;
    String cageId = "hostel";

    Animal() {
    }

    Animal(String targetLocation, int currentAge) {
        cageId = targetLocation;
        age = currentAge;
    }

    public void feed() {
        isHungry = false;
    }

    public void relocate(String newLocation) {
        int aaa;
        String previousLocation = cageId;
        cageId = newLocation;
    }

    public static void main(String[] args) {
        Animal horse = new Animal();
        System.out.println("horse: is it hungry?:" + horse.isHungry + " living in the cage #: " + horse.cageId + " it's age is: " + horse.age);
        Animal bear = new Animal();
        System.out.println("bear: is it hungry?:" + bear.isHungry + " living in the cage #: " + bear.cageId + " it's age is: " + bear.age);
        horse.relocate("Area 27");
        System.out.println("horse: is it hungry?:" + horse.isHungry + " living in the cage #: " + horse.cageId + " it's age is: " + horse.age);
        bear.feed();
        System.out.println("bear: is it hungry?:" + bear.isHungry + " living in the cage #: " + bear.cageId + " it's age is: " + bear.age);
        Animal dog = new Animal("home", 2);
        System.out.println("dog: is it hungry?:" + dog.isHungry + " living in the cage #: " + dog.cageId + " it's age is: " + dog.age);
    }
}
