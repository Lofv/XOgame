package Controller;

import View.Window;

import java.awt.*;


public class Main {
    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Window win=new Window();
               // win.setVisible(true);
            }
        });

    }
}