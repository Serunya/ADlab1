package Graphics;
import Algoritms.*;
import Data.DataMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GraphPanel extends JPanel {
    public static GraphPanel MainFrame;
    public static CustomListener listener;
    public static boolean neograph = true;

    GraphPanel(){
        super();
        setBackground(Color.WHITE);
        MainFrame = this;
        listener = new CustomListener();
        addMouseListener(listener);
        setSize(600,600);
        setLayout(null);
        setVisible(true);
    }

    public static void default_view(){
        for(Dot dot: DataMapper.getDots())
            dot.setColor(Color.green);
        for(Edge edge: DataMapper.getEdges())
            edge.line.setColor(Color.BLACK);
        MainFrame.repaint();
    }

    private class CustomListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            Dot dot = new Dot(DataMapper.dots.size());
            DataMapper.dots.add(dot);
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
