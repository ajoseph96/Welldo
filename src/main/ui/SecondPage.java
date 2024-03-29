package ui;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class SecondPage extends JFrame implements ActionListener {

    JLabel label = new JLabel();
    JButton button = new JButton();
    JPanel greyPanel = new JPanel();

    JTextField textField = new JTextField();

    // EFFECTS: constructor for SecondPage, creates initial frame with colour and buttons etc.
    public SecondPage() {
        this.setSize(2000, 1000); //sets dimension of window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setText("\tHow are you feeling today? Please rate your mood on a scale of 1-10.");
        this.getContentPane().setBackground(new Color(0x5B96A9));
        this.add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        textField.setPreferredSize(new Dimension(250, 40));
        greyPanel.setBackground(Color.GRAY);
        this.add(greyPanel, BorderLayout.SOUTH);
        greyPanel.add(textField);
        button.addActionListener(this);
        button.setText("Submit");
        greyPanel.add(button);
        this.setVisible(true);

    }

    //EFFECTS: creates varied result based on which button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            int num = Integer.parseInt(textField.getText());
            new ThirdPage(num);
            this.dispose();
        }

    }
}
