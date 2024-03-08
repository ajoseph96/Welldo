package ui;

import java.io.FileNotFoundException;

//main class running interface
public class Main {
    public static void main(String[] args) {
        try {
            new Welldo();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}
