package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThirdPage extends JFrame implements ActionListener {

    JLabel label = new JLabel();
    JButton sadButton = new JButton();
    JButton happyButton = new JButton();
    JButton angryButton = new JButton();
    JPanel greyPanel = new JPanel();

    int initialMood;


    // EFFECTS: constructor for  ThirdPage, creates initial frame with colour and buttons etc.
    public ThirdPage(int n) {
        initialMood = n;
        this.setSize(2000, 1000); //sets dimension of window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setText("\tThank-you for sharing, I'm here to help. Next can you tell me what mood you're primarily "
                + "feeling? (Only if possible)");
        this.getContentPane().setBackground(new Color(0x5B96A9));
        this.add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        greyPanel.setBackground(Color.GRAY);
        this.add(greyPanel, BorderLayout.SOUTH);
        buttonSetup();
        this.setVisible(true);
    }

    //EFFECTS: initializes the many buttons in this frame.
    public void buttonSetup() {
        sadButton.addActionListener(this);
        sadButton.setText("Sad");
        happyButton.addActionListener(this);
        happyButton.setText("Happy");
        angryButton.addActionListener(this);
        angryButton.setText("Angry");
        greyPanel.add(sadButton);
        greyPanel.add(happyButton);
        greyPanel.add(angryButton);
    }

    //EFFECTS: creates varied result based on which button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sadButton) {
            new FourthPage("s", initialMood);
            this.dispose();

        } else if (e.getSource() == happyButton) {
            new FourthPage("h", initialMood);
            this.dispose();

        } else if (e.getSource() == angryButton) {
            new FourthPage("a", initialMood);
            this.dispose();

        }
    }
}
