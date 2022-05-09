package Graphics;
import Algoritms.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    public ArrayList<Dot> dots = new ArrayList<>();
    public static MainPanel MainFrame;
    public static CustomListener listener;
    public static boolean neograph = true;

    public void preflow(){
        Thread t = new Thread(new PreflowPushes(dots,Edge.edges));
        t.start();
    }

    public void search_deep(){
        Thread t = new Thread(new SearchDeep2(dots,Edge.edges,0));
        /*
        String search_item = null;
        search_item = JOptionPane.showInputDialog("Введи значение искомого элемента");
        Thread t = new Thread(new SearchDeep(dots,Edge.edges,Integer.parseInt(search_item)));
        */
        t.start();
    }

    public void ford_belman(){
        Thread t = new Thread(new FordBelman(dots,Edge.edges,dots.get(0)));
        t.start();
    }

    public void skeleton_graph(){
        Thread t = new Thread(new SkeletonGraph(dots,Edge.edges));
        t.start();
    }


    MainPanel(){
        super();
        new Thread().start();
        setBackground(Color.WHITE);
        MainFrame = this;
        listener = new CustomListener();
        addMouseListener(listener);
        setSize(600,600);
        setLayout(null);
        setVisible(true);
    }

    public class CustomListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            Dot dot = new Dot(dots.size());
            dots.add(dot);
            dot.setBounds(e.getX() - 20, e.getY() - 20, 43, 43);
            MainFrame.add(dot);
            MainFrame.repaint();
        }
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
