package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ActionListener {
    JLabel label = new JLabel();
    JButton button = new JButton();
    JPanel greyPanel = new JPanel();

    // EFFECTS: constructor for  MainFrame, creates initial frame with colour and buttons etc.
    public MainFrame() {
        this.setSize(2000, 1000); //sets dimension of window
        this.setTitle("Welldo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits out of application
        ImageIcon image = new ImageIcon("./data/welldologo.png");//creates image icon
        this.setIconImage(image.getImage());//change icon of frame
        this.getContentPane().setBackground(new Color(0x5B96A9)); //change background colour.
        label.setText("Welcome to Welldo! Your personalized Mental Health Task Tracker");//set label text
        label.setIcon(image);
        this.add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER); //centers label
        greyPanel.setBackground(Color.GRAY);
        button.addActionListener(this);
        button.setText("Lets Begin!");
        greyPanel.add(button);
        this.add(greyPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    //EFFECTS: creates result based on which button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            new SecondPage();
            this.dispose();
        }
    }
}
