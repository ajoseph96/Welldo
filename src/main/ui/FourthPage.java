package ui;


import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Fourth ui screen
public class FourthPage extends JFrame implements ActionListener {

    JLabel label = new JLabel();

    JLabel label2 = new JLabel();

    JLabel label3 = new JLabel();
    JButton addButton = new JButton();
    JButton removeButton = new JButton();
    JButton loadButton = new JButton();
    JButton saveButton = new JButton();
    JButton nextButton = new JButton();
    JPanel greyPanel = new JPanel();

    JTextField textField = new JTextField();

    int initialMood;

    JPanel bluePanel = new JPanel();

    private SadTasks sad;
    private HappyTasks happy;
    private AngryTasks angry;

    private static String JSON_STORE;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    String emotion;

    // EFFECTS: constructor for  FourthPage, creates initial frame with colour and buttons etc.
    @SuppressWarnings("methodlength")
    public FourthPage(String s, int n) {
        sad = new SadTasks();
        happy = new HappyTasks();
        angry = new AngryTasks();
        initialMood = n;
        emotion = s;
        buttonSetup();
        emotionSelectorLabel(s);
        this.setSize(2000, 1000); //sets dimension of window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label2.setText("I understand that these choices are fairly limited. Based on your selected emotion"
                + " is there another task you find helps you most during these times?");
        label3.setText(" If you would like to add a task please type the task in the textbox and click the add button."
                + " If you would like to load a previous task list please click load."
                + " Click remove to delete last added task, otherwise click next");
        this.getContentPane().setBackground(new Color(0x5B96A9));
        this.add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        textField.setPreferredSize(new Dimension(250, 40));
        greyPanel.setBackground(Color.GRAY);
        this.add(greyPanel, BorderLayout.SOUTH);
        this.add(bluePanel, BorderLayout.CENTER);
        greyPanel.add(textField);
        bluePanel.setBackground(new Color(0x5B96A9));
        bluePanel.add(label);
        bluePanel.add(label2);
        bluePanel.add(label3);
        greyPanel.add(addButton);
        greyPanel.add(loadButton);
        greyPanel.add(saveButton);
        this.setVisible(true);

    }

    //EFFECTS: returns a specific task list based on previous frame option
    public void emotionSelectorLabel(String s) {
        if (s == "s") {
            label.setText("I see you are feeling sad, lets see if we can help manage these difficult feelings, "
                    + "in these cases here are some tasks we suggest: " + sad.getTaskList());
            JSON_STORE = "./data/sadTasks.json";
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
        }
        if (s == "h") {
            label.setText("I see you are feeling happy, I'm so glad, "
                    + "in these cases here are some tasks we suggest to help maintain this mood: "
                    + happy.getTaskList());
            JSON_STORE = "./data/happyTasks.json";
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
        }
        if (s == "a") {
            label.setText("I see you are feeling angry, lets see if we can help manage these difficult feelings, "
                    + "in these cases here are some tasks we suggest: " + angry.getTaskList());
            JSON_STORE = "./data/angryTasks.json";
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
        }
    }

    //EFFECTS: returns a specific emotion based on previous frame option
    public Tasks emotionSelectorList() {
        if (emotion == "s") {
            return sad;
        }
        if (emotion == "h") {
            return happy;
        } else {
            return angry;
        }
    }

    //EFFECTS: initializes the many buttons in this frame.
    public void buttonSetup() {
        addButton.addActionListener(this);
        addButton.setText("Add");
        saveButton.addActionListener(this);
        saveButton.setText("Save");
        loadButton.addActionListener(this);
        loadButton.setText("Load");
        nextButton.addActionListener(this);
        nextButton.setText("Next");
        removeButton.addActionListener(this);
        removeButton.setText("Remove");
        greyPanel.add(addButton);
        greyPanel.add(saveButton);
        greyPanel.add(loadButton);
        greyPanel.add(nextButton);
        greyPanel.add(removeButton);
    }

    // EFFECTS: saves the workroom to file
    private void saveTasks(Tasks t) {
        try {
            jsonWriter.open();
            jsonWriter.write(t);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tasks from file
    private Tasks loadWorkRoom(Tasks ta) {
        try {
            ta = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
            return ta;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return ta;
    }

    @SuppressWarnings("methodlength")
    //EFFECTS: creates varied result based on which button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            removeButton.setEnabled(true);
            emotionSelectorList().addTaskList(textField.getText());
            label.setText("Here are your updated suggested tasks: " + emotionSelectorList().getTaskList());
        } else if (e.getSource() == removeButton) {
            int listSize = emotionSelectorList().getTaskList().size();
            if (listSize == 1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
                emotionSelectorList().getTaskList().remove((listSize - 1));
                label.setText("Here are your updated suggested tasks: " + emotionSelectorList().getTaskList());
            }
        } else if (e.getSource() == saveButton) {
            saveTasks(emotionSelectorList());
            System.out.println("Task saved!");
        } else if (e.getSource() == loadButton) {
            Tasks newt;
            newt = loadWorkRoom(emotionSelectorList());
            label.setText("Here are your updated suggested tasks: " + newt.getTaskList());
        } else if (e.getSource() == nextButton) {
            new FifthPage(initialMood);
            this.dispose();
        }
    }
}
