package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

//Third ui screen
public class ThirdPage extends JFrame implements ActionListener, WindowListener {

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
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    //EFFECTS: closes frame and prints Event Log in console.
    @Override
    public void windowClosing(WindowEvent e) {
        Iterator<Event> it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        this.dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
