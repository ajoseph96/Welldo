package ui;

import model.CompletedTasks;
import model.MoodScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FifthPage extends JFrame implements ActionListener {

    JLabel label = new JLabel();
    JButton nextButton = new JButton();
    JButton submitButton = new JButton();
    JButton addButton = new JButton();
    JPanel greyPanel = new JPanel();
    JTextField textField = new JTextField();
    MoodScore mood;

    CompletedTasks completed;

    // EFFECTS: constructor for  FifthPage, creates initial frame with colour and buttons etc.
    public FifthPage(int n) {
        completed = new CompletedTasks();
        mood = new MoodScore();
        mood.setInitialMoodNum(n);
        this.setSize(2000, 1000); //sets dimension of window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setText("Did you complete any of the previously mentioned tasks today? If so type the tasks in the"
                + " in the textbox and click add, otherwise click next");
        this.getContentPane().setBackground(new Color(0x5B96A9));
        this.add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        textField.setPreferredSize(new Dimension(250, 40));
        greyPanel.setBackground(Color.GRAY);
        this.add(greyPanel, BorderLayout.SOUTH);
        greyPanel.add(textField);
        submitButton.addActionListener(this);
        submitButton.setText("Submit");
        addButton.addActionListener(this);
        addButton.setText("Add");
        nextButton.addActionListener(this);
        nextButton.setText("Next");
        greyPanel.add(addButton);
        greyPanel.add(nextButton);
        this.setVisible(true);
    }

    //EFFECTS: creates varied result based on which button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            completed.addTaskList(textField.getText());
            label.setText("Here are your updated completed tasks: " + completed.getTaskList()
                    + " click next if done adding");
        } else if (e.getSource() == nextButton) {
            greyPanel.add(submitButton);
            addButton.setEnabled(false);
            nextButton.setEnabled(false);
            label.setText("As we are now coming to the end of the day please tell us how your mood has changed."
                    + " Please rate your current mood on a scale of 1-10 in text field below and click Submit");
        } else if (e.getSource() == submitButton) {
            int num = Integer.parseInt(textField.getText());
            mood.setFinalMoodNum(num);
            submitButton.setEnabled(false);
            summaryStats();
        }
    }

    //MODIFIES: this
    // EFFECTS: calculates mood score difference and provides a summary for the day.
    public void summaryStats() {
        if (mood.calculateMoodNumDifference()) {
            if (((completed.getTaskList()).isEmpty())) {
                label.setText("It seems like your mood changed for the better! I'm so glad."
                        + " Your mood score improved by" + " " + Math.abs(mood.getMoodNumDifference()) + "."
                        + " It seems no tasks were completed today but regardless your mood improved!"
                        + " Thank-you for using Welldo, I hope this information was helpful."
                        + " Hope you continue to have a great day!");
            } else {
                label.setText("It seems like your mood changed for the better! I'm so glad."
                        + " Your mood score improved by " + Math.abs(mood.getMoodNumDifference())
                        + " and these task(s) seemed to help:" + completed.getTaskList() + "."
                        + " Thank-you for using Welldo, I hope this information was helpful."
                        + " Hope you continue to have a great day!");
            }
        } else {
            label.setText("It seems like your mood did not improve on this day."
                    + " That's ok! Maybe you were already feeling great or"
                    + " today was just a tougher day, regardless I am so proud you tried your best."
                    + " There is always tomorrow, I'll see you then!");
        }
    }
}
