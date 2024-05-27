package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import model.*;
import model.Event;


//initial menu, seen when starting program with app logo.
public class MainFrame extends JFrame implements ActionListener, WindowListener {
    JLabel label = new JLabel();
    JButton button = new JButton();
    JPanel greyPanel = new JPanel();


    // EFFECTS: constructor for  MainFrame, creates initial frame with colour and buttons etc.
    public MainFrame() {
        this.setSize(2000, 1000); //sets dimension of window
        this.setTitle("Welldo");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
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
