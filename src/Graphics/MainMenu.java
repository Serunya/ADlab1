package Graphics;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    public static MainMenu context;
    public MainPanel main_panel;
    public static void main(String[] args) {
        new MainMenu();
    }

    MainMenu(){
        super("Графы");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setSize(800,800);
        setLayout(null);
        setVisible(true);
        main_panel = new MainPanel();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        main_panel.setBorder(blackline);
        main_panel.setBounds(10,10,610,610);
        add(main_panel);



        JToggleButton toggleButton = new JToggleButton("ОрГраф");
        ItemListener itemListener = itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                toggleButton.setText("НеоГраф");
                MainPanel.neograph = false;
                repaint();
            }
            else {
                toggleButton.setText("ОрГраф");
                MainPanel.neograph = true;
                repaint();
            }
        };
        toggleButton.addItemListener(itemListener);
        toggleButton.setBounds(625,570,150,30);
        add(toggleButton);

        // Кнопка поиска в глубину
        JButton button_search_deep = new JButton("Поиск в глубину");
        button_search_deep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.MainFrame.search_deep();
            }
        });
        button_search_deep.setBounds(625,30,150,30);
        add(button_search_deep);

        // Кнопка остаф графа
        JButton button_seleton_graph = new JButton("Остав графа");
        button_seleton_graph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.MainFrame.skeleton_graph();
            }
        });
        button_seleton_graph.setBounds(625,70,150,30);
        add(button_seleton_graph);

        // Кнопка метожа Белмана-форда
        JButton button_ford_belman = new JButton("Белман-Форд");
        button_ford_belman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.MainFrame.ford_belman();
            }
        });
        button_ford_belman.setBounds(625,110,150,30);
        add(button_ford_belman);

        // Кнопка метожа Белмана-форда
        JButton button_preflow_pushes = new JButton("Проталкивание предпотока");
        button_preflow_pushes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.MainFrame.preflow();
            }
        });
        button_preflow_pushes.setBounds(625,150,150,30);
        add(button_preflow_pushes);

        // Кнопка сгененрировать граф
        JButton button_generate_graph = new JButton("Сгенерировать граф");
        button_generate_graph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.MainFrame.dots = RandomGraph.Rand(7);
            }
        });
        button_generate_graph.setBounds(625,625,150,30);
        add(button_generate_graph);
    }
}
