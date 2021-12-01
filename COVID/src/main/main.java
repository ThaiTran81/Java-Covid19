package main;

import javax.swing.*;
import View.*;

public class main {
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddPatientView();
            }
        });
    }
}
