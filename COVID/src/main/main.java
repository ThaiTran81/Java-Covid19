package main;

import javax.swing.*;
import View.*;
import View.User.MainFrame;
import View.User.*;

import java.awt.*;

public class main {
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConnectToDBView();
//                new MainFrame();
            }
        });
    }
}
