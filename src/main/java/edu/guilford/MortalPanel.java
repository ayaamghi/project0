package edu.guilford;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// We need MortalPanel to do everything a JPanel does, so we need the extends keyword
public class MortalPanel extends JPanel {

    // Add an attribute for the list of Mortal objects that will be provided by some
    // other part of the program
    private ArrayList<Mortal> mortals;
    // mortalIndex keeps track of which object in the list we're currently focused
    // on
    private int mortalIndex = 2317;

    // When we build this panel class, it's best to make each individual component
    // its
    // own attribute
    private JLabel mortalLabel;
    // Step 1: Declare the component as an attribute
    private JTextField mortalChoice;
    private JButton mortalChoiceButton;
    private JCheckBox boldCheckBox;
    private JCheckBox italicCheckBox;
    private JRadioButton arialButton;
    private JRadioButton papyrusButton;
    private JRadioButton timesButton;
    private JSlider fontSizeSlider;

    // font attributes
    private String fontFamily = "Arial";
    private int fontSize = 32;
    private int fontWeight = Font.BOLD;
    private int fontPosture = Font.ITALIC;

    // Start with an empty constructor
    public MortalPanel() {
        setPreferredSize(new Dimension(800, 600));
    }

    // This constructor gets a list of Mortal objects and builds the panel with that
    // info
    public MortalPanel(ArrayList<Mortal> mortals) {
        this.mortals = mortals;
        setPreferredSize(new Dimension(800, 600));
        // Just print out the first object so we know it made it here
        System.out.println(mortals.get(mortalIndex));
        // Instantiate the JLabel mortalLabel
        mortalLabel = new JLabel();
        // Get a Mortal object that will be the first one we display
        Mortal displayMortal = mortals.get(mortalIndex);
        // Set the text of the label
        mortalLabel.setText("Mortal " + mortalIndex + ": " + displayMortal.getSpecies());
        // Set the font for the label
        Font labelFont = new Font(fontFamily, fontWeight | fontPosture, fontSize);
        mortalLabel.setFont(labelFont);
        // Add the label to the display
        add(mortalLabel);

        // Step 2: Instantiate the component
        mortalChoice = new JTextField("" + mortalIndex);
        // Step 2a: Decorate the field
        mortalChoice.setColumns(6);
        // Step 3: Add it to the display
        add(mortalChoice);
        // Step 5: Connect the listener to the component
        // Instantiate a MortalFieldListener object
        MortalFieldListener mfListener = new MortalFieldListener();
        // Tell the mortalChoice text field to connect to mfListener
        mortalChoice.addActionListener(mfListener);

        // Step 2
        mortalChoiceButton = new JButton("=>");
        // Step 3
        add(mortalChoiceButton);
        // Step 5
        MortalButtonListener mbListener = new MortalButtonListener();
        mortalChoiceButton.addActionListener(mbListener);

        // Checkboxes
        boldCheckBox = new JCheckBox("Bold");
        italicCheckBox = new JCheckBox("Italic");
        add(boldCheckBox);
        add(italicCheckBox);
        CheckBoxListener cbListener = new CheckBoxListener();
        // Connect both checkboxes to the same listener!
        boldCheckBox.addActionListener(cbListener);
        italicCheckBox.addActionListener(cbListener);

        arialButton = new JRadioButton("Arial");
        papyrusButton = new JRadioButton("Papyrus");
        timesButton = new JRadioButton("Times New Roman");
        add(arialButton);
        add(papyrusButton);
        add(timesButton);
        // We need to tell Java which radio buttons should be grouped together so that only one can be selected
        ButtonGroup fontGroup = new ButtonGroup();
        fontGroup.add(arialButton);
        fontGroup.add(papyrusButton);
        fontGroup.add(timesButton);
        RadioButtonListener rbListener = new RadioButtonListener();
        arialButton.addActionListener(rbListener);
        papyrusButton.addActionListener(rbListener);
        timesButton.addActionListener(rbListener);

        // When instantiating a slider, tell the constructor the minimum and maximum values and 
        // where the handle should start
        fontSizeSlider = new JSlider(6, 40, fontSize);
        add(fontSizeSlider);
        SliderListener sListener = new SliderListener();
        fontSizeSlider.addChangeListener(sListener);
    }

    // behaviors
    // add a behavior that sets the weight and posture attributes of the font
    public void updateWeightPosture() {
        // reset weight and posture to the plain values
        fontWeight = Font.PLAIN;
        fontPosture = Font.PLAIN;
        // check each checkbox to see if it's checked and update weight and posture if it is
        // each JCheckBox has an attribute named selected that is true if the checkbox is checked
        if (boldCheckBox.isSelected()) {
            fontWeight = Font.BOLD;
        }
        if (italicCheckBox.isSelected()) {
            fontPosture = Font.ITALIC;
        }
        // update the font
        updateFont();
    }

    public void updateFont() {
        Font fontNew = new Font(fontFamily, fontWeight | fontPosture, fontSize);
        mortalLabel.setFont(fontNew);
    }

    // listeners
    // Listeners are objects, and to have an object we have to define a class
    // Step 4: Write the listener for the mortalChoice text field
    private class MortalFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // This method runs when the listener receives an event
            System.out.println("Event received on mortalField");
            // Get what the user typed into the textfield as an integer
            mortalIndex = Integer.parseInt(mortalChoice.getText());
            Mortal displayMortal = mortals.get(mortalIndex);
            mortalLabel.setText("Mortal " + mortalIndex + ": " + displayMortal.getSpecies());

        }
        // Anything that we want to be an ActionListener object must have an
        // actionPerformed method
        // The actionPerformed method defines what to do when this listener receives an
        // event

    }

    // Step 4
    private class MortalButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // increment mortalIndex by 1
            mortalIndex = mortalIndex + 1;
            Mortal displayMortal = mortals.get(mortalIndex);
            mortalLabel.setText("Mortal " + mortalIndex + ": " + displayMortal.getSpecies());

        }

    }

    private class CheckBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Print out information about the event that was triggered
            System.out.println("Checkbox event: " + e);
            // behavior that tells the JPanel how to update these attributes of the font
            updateWeightPosture();
        }
        
    }

    private class RadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Which button generated the event? Ask the event, get the response, and treat it as a JRadioButton
            JRadioButton selectedButton = (JRadioButton)e.getSource();
            // Use the label of the button to set the font
            fontFamily = selectedButton.getText();
            updateFont();
        }
        
    }

    // A slider generates an event everytime the user *changes* its value by moving the handle
    // We need a *ChangeListener*
    private class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            fontSize = fontSizeSlider.getValue();
            updateFont();
        }
        
    }
}
