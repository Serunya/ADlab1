package Graphics;

import Algoritms.FordBelman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MethodButton extends JButton {
    Runnable method;
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread t = new Thread(method);
            t.start();
        }
    };


    MethodButton(String button_name,Runnable run_method){
        super(button_name);
        method = run_method;
        addActionListener(listener);
    }
}
