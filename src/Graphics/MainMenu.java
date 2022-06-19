package Graphics;

import Algoritms.Algoritm;
import Data.DataMapper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    public static MainMenu context;
    public ArrayList<JButton> buttons_algoritm = new ArrayList<>();
    public final JButton next_step_button = new JButton("=>");
    public final JButton prev_step_button = new JButton("<=");
    public final JButton end_algoritm_button = new JButton("Завершить");
    public MainMenu(){
        super("Графы");
        context = this;
        prev_step_button.setBounds(625,610,70,30);
        add(prev_step_button);
        next_step_button.setBounds(705,610,70,30);
        add(next_step_button);
        end_algoritm_button.setBounds(625,645,150,30);
        add(end_algoritm_button);
        MainMenu.context.prev_step_button.setVisible(false);
        MainMenu.context.next_step_button.setVisible(false);
        MainMenu.context.end_algoritm_button.setVisible(false);
        end_algoritm_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algoritm.current_algoritm.end = true;
            }
        });
        next_step_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algoritm.current_algoritm.next_step();
            }
        });
        prev_step_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algoritm.current_algoritm.prev_step();
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setSize(800,800);
        setLayout(null);
        setVisible(true);
        GraphPanel main_panel = new GraphPanel();
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
        for(Algoritm a: DataMapper.get_algoritms()){
            JButton button_algoritm = new JButton((a.getName()));
            buttons_algoritm.add(button_algoritm);
            button_algoritm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hide_buttons_algoritm();
                    if(Algoritm.current_algoritm == null) {
                        Thread t = new Thread(a);
                        t.start();
                    }
                }
            });
            button_algoritm.setBounds(625,70+i*35,150,30);
            add(button_algoritm);
            i++;
        }
        repaint();
    }

    public void hide_buttons_algoritm(){
        for(JButton button: buttons_algoritm){
            button.setVisible(false);
        }
    }
    public void show_buttons_algoritm() {
        for (JButton button : buttons_algoritm) {
            button.setVisible(true);
        }
    }
}
