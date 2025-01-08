package edu.guilford;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

// this part of the project is our *driver* program: where everything starts
// it contains the *main* method, which is the place Java first looks when it runs the program
public class MortalProjectDriver {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Let's instantiate a Mortal object with the empty *constructor*
        // Constructors tells the Mortal class how to build a new object
        Mortal theMortal = new Mortal();

        // Can we tell this object what its age is?
        // Not using this line, because age is not a visible field (attribute) of Mortal
        // We want to send actual messages to a Mortal object so it will use its behaviors
        // theMortal.age = 37;

        // Send a setter message to theMortal to tell it what its age is
        theMortal.setAge(37);
        // We use a getter message to ask theMortal what its age is, and we print out that value
        System.out.println("theMortal age: " + theMortal.getAge());
        System.out.println("Law abiding " + theMortal.isLawAbiding());

        // We really want to instantiate an object will all its attribute values set
        // Tell it its age and its height and its weight and ...
        // To do that we need a more complex constructor

        Mortal otherMortal = new Mortal(85, 150., 70, 
        false, false, true, 1, 
        "alien", true, 'f');
        System.out.println("The otherMortal object has age: " + otherMortal.getAge() + 
        " and height: " + otherMortal.getHeight());

        System.out.println("Other mortal: " + otherMortal.toString());
        System.out.println("Its BMI is " + otherMortal.calcBMI());

        // Let's create a randomized Mortal object using the empty constructor
        Mortal randomMortal = new Mortal();
        // When printing an object, we can use randomMortal that is the same as randomMortal.toString()
        System.out.println("Random Mortal: " + randomMortal);
        System.out.println("Value for random mortal: " + 
            String.format("%.2f", randomMortal.calculateValue()));

        // Create 18357 Mortal objects and store them in an ArrayList object--we'll use a counting loop
        // Instantiate an ArrayList object that will hold our Mortal objects
        ArrayList<Mortal> mortals = new ArrayList<Mortal>();
        int iMortal = 0; // what we'll use to keep track of where we are in the loop--loop index
        int nMortals = 18357; // how many we want
        // start the loop
        while (iMortal < nMortals) {
            // instantiate a random Mortal object and add it to the list, then increment the index
            Mortal nextMortal = new Mortal();
            mortals.add(nextMortal); // use the add message for ArrayList to add to end of list
            iMortal = iMortal + 1;
        }

        // Print out the information for one of the objects in the list using the get method
        System.out.println("Mortal 2317: " + mortals.get(2317));
        System.out.println("and its value is " + mortals.get(2317).calculateValue());

        // Let's find out how many of our objects have a value greater than 100
        int countHighValue = 0; // keep track of how many high value mortals we have
        for (Mortal mortal : mortals) {
            if (mortal.calculateValue() > 100) {
                countHighValue = countHighValue + 1;
            }
        }
        System.out.println("The number of high value mortals is " + countHighValue);

        // Add a window with a panel that will eventually hold a MortalPanel object
        // Instantiate a JFrame that will be our window
        JFrame mortalFrame = new JFrame("Mortal interaction");
        // If we don't have the next line, the program won't die when we close the window
        mortalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Instantiate a JPanel container and add it to the window
        JPanel primary = new JPanel();
        mortalFrame.getContentPane().add(primary);

        // Add a MortalPanel object that is a JPanel that knows about Mortal objects
        // and is given the list of objects we've already built
        MortalPanel mortalPanel = new MortalPanel(mortals);
        primary.add(mortalPanel);

        // Make the window visible
        mortalFrame.pack();
        mortalFrame.setVisible(true);


    }
}