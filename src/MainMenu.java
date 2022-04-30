import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton search_deep_button = new JButton("Поиск в глубину");
        search_deep_button.addActionListener(new Search_Deep_Button());
        search_deep_button.setBounds(625,30,150,30);
        add(search_deep_button);

        JButton generate_graph = new JButton("Сгененрировать граф");
        generate_graph.addActionListener(new Generate_graph());
        generate_graph.setBounds(625,600,150,30);
        add(generate_graph);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Для создания графа:",15,670);
        g.drawString("Нажмите на любую точку панели - создаться вершина",30,690);
        g.drawString("Несколько вершин можно объеденить выберая одну(Подсветиться оранжевым)",30,710);
        g.drawString("Выбирая вторую вершины соединяться",30,730);
    }

    class Search_Deep_Button implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainPanel.MainFrame.search_deep();
        }
    }

    class Generate_graph implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainPanel.MainFrame.dots = RandomGraph.Rand(10);
        }
    }
}
