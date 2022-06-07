package Graphics;

import Algoritms.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import static Data.DataMapper.algoritms;


public class MainMenu extends JFrame {
    public static MainMenu context;
    public GraphPanel main_panel;

    public MainMenu(){
        super("Графы");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setSize(800,800);
        setLayout(null);
        setVisible(true);
        main_panel = new GraphPanel();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        main_panel.setBorder(blackline);
        main_panel.setBounds(10,10,610,610);
        add(main_panel);


        JToggleButton toggleButton = new JToggleButton("ОрГраф");
        ItemListener itemListener = itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                toggleButton.setText("НеоГраф");
                GraphPanel.neograph = false;
                repaint();
            }
            else {
                toggleButton.setText("ОрГраф");
                GraphPanel.neograph = true;
                repaint();
            }
        };
        toggleButton.addItemListener(itemListener);
        toggleButton.setBounds(625,570,150,30);
        add(toggleButton);
        int i = 0;

        for(Algoritm a: algoritms){
            MethodButton but_check = new MethodButton(a.getName(), a);
            but_check.setBounds(625,70 + i * 30,150,30);
            add(but_check);
            i++;
        }

        repaint();
    }
}
