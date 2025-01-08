package edu.guilford;

import java.util.Random;

// this file is the *class definition file* for the Mortal class
public class Mortal {

    // Attributes
    private int age; // 1-100 years
    private double weight; // 10-250 lbs
    private double height; // 24-72 in
    private boolean animal;
    private boolean lawAbiding;
    private boolean pregnant;
    private int socialClass;
    private String species; // {cat, dog, alien, turtle, rat}
    private boolean inCar;
    private char gender;

    // Constructors
    
    // empty constructor--it has no parameters, so every attribute will be unassigned
    public Mortal() {
       // System.out.println("We just created life");
        Random rand = new Random();
        
        age = rand.nextInt(1, 100);
        height = rand.nextDouble(12, 72);
        weight = rand.nextDouble(10, 250);
        inCar = rand.nextBoolean(); // 50-50 chance the object is in the car
        socialClass = rand.nextInt(2); // possible values are 0, 1, 2

        // generating a random character
        boolean randGender = rand.nextBoolean();
        if (randGender) {
            gender = 'f';
        } else {
            gender = 'm';
        }

        // if gender is 'f' and some random condition holds, then pregnant = true
        double pChoice = rand.nextDouble();
        if (gender == 'f' && pChoice > 0.80) {
            pregnant = true;
        }

        // Get a new floating point number
        double choice = rand.nextDouble();
        // If choice is greater than 0.75, the object will not be law abiding
        // We use a *conditional statement* with the keywords if and else
        if (choice > 0.75) {
            // what we do if the Boolean expression is true: if-block
            lawAbiding = false;
        } else {
            // what we do if the Boolean expression is false: else-block
            lawAbiding = true;
        }
        // Is this random Mortal object human?
        choice = rand.nextDouble();
        if (choice > 0.80) {
            animal = true;
        } else {
            animal = false;
        }
        // Let's make the species human
        species = "human";
        // But if this is an animal, not a human, we'll have to change the species
        if (animal) {
            int aChoice = rand.nextInt(5);
            if (aChoice == 0) {
                species = "cat";
            } else if (aChoice == 1) {
                species = "dog";
            } else if (aChoice == 2) {
                species = "alien";
            } else if (aChoice == 3) {
                species = "turtle";
            } else if (aChoice == 4) {
                species = "rat";
            }
            
        }

        

    }

   
    // this constructor assigns values to all the attributes for a Mortal object
    public Mortal(int age, double weight, double height, boolean animal, boolean lawAbiding, boolean pregnant,
            int socialClass, String species, boolean inCar, char gender) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.animal = animal;
        this.lawAbiding = lawAbiding;
        this.pregnant = pregnant;
        this.socialClass = socialClass;
        this.species = species;
        this.inCar = inCar;
        this.gender = gender;
    }



    // Behaviors
    // This is the definition of the getter for the age attribute
    public int getAge() {
        return age;
    }
    
    // This is the definition of the setter for the age attribute
    public void setAge(int age) {
        this.age = age;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public boolean isAnimal() {
        return animal;
    }
    public void setAnimal(boolean animal) {
        this.animal = animal;
    }
    public boolean isLawAbiding() {
        return lawAbiding;
    }
    public void setLawAbiding(boolean lawAbiding) {
        this.lawAbiding = lawAbiding;
    }
    public boolean isPregnant() {
        return pregnant;
    }
    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }
    public int getSocialClass() {
        return socialClass;
    }
    public void setSocialClass(int socialClass) {
        this.socialClass = socialClass;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public boolean isInCar() {
        return inCar;
    }
    public void setInCar(boolean inCar) {
        this.inCar = inCar;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Mortal [age=" + age + ", weight=" + weight + ", height=" + height + ", animal=" + animal
                + ", lawAbiding=" + lawAbiding + ", pregnant=" + pregnant + ", socialClass=" + socialClass
                + ", species=" + species + ", inCar=" + inCar + ", gender=" + gender + "]";
    }

    // Calculate the bmi, assuming weight is in pounds and height in inches
    public double calcBMI() {
        double bmi = 703. * weight / Math.pow(height, 2);
        return bmi;
    }
    
    // Define a method that uses the attributes to assign a value to this Mortal object
    // Methods need signatures: this will be public, return a double value and have the
    // identifier calculateValue. The parameter list will be empty because the object already 
    // has all the information it needs about itself
    public double calculateValue() {
       // Random rand = new Random();
        double value = 100 - calcBMI(); //everyone starts with a value based on how fit it is

        // if an object is not elderly (age < 70), then add 30 to its value
        if (age < 70) {
            value = value + 30;
        }

        // if socialClass is 2, add 20 to value
        if (socialClass == 2) {
            value = value + 20;
        }

        // if it's an alien and it's not law abiding, cut its value in half
        if (species == "alien" && !lawAbiding) {
            value = value / 2;
        }

        return value; // then we'll return whatever we calculate before this line
    }
}
