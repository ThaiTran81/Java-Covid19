package main;

import View.ConnectToDBView;

import java.awt.*;

public class main {
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConnectToDBView();
//                new MainFrame("189393343");
            }
        });
    }
}
