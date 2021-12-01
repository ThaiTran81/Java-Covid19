package main;

import javax.swing.*;
import View.*;
import View.User.UserMainFrame;

public class main {
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserMainFrame m = new UserMainFrame();
            }
        });
    }
}
