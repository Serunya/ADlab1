package Graphics;

import Data.DataMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Dot extends JComponent{
    static boolean connection = false;
    static Dot root = null;


    public int data;
    private volatile int draggedAtX, draggedAtY;
    public ArrayList<Edge> edges_root = new ArrayList<>();
    public ArrayList<Edge> edges_child = new ArrayList<>();

    Color color = Color.GREEN;
    public CustomListenet listenet = new CustomListenet();

    public Dot(int data){
        this.data = data;
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                for (int i = 0; i < edges_child.size(); i++) {
                    Edge edge = edges_child.get(i);
                    edge.getLine().x1 = e.getX() - draggedAtX + getLocation().x + 20;
                    edge.getLine().y1 = e.getY() - draggedAtY + getLocation().y + 20;
                    edges_child.get(i).getLine().repaint();
                }
                for (Edge v: edges_root) {
                    v.getLine().x = e.getX() - draggedAtX + getLocation().x + 20;
                    v.getLine().y = e.getY() - draggedAtY + getLocation().y + 20;
                    v.getLine().repaint();
                }
                setLocation(e.getX() - draggedAtX + getLocation().x,
                        e.getY() - draggedAtY + getLocation().y);;
            }
        });
    }

    public void setColor(Color color){
        this.color = color;
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        removeMouseListener(listenet);
        addMouseListener(listenet);
        super.paint(g);
        g.setColor(color);
        g.fillOval(1, 1, 40, 40);
        g.setColor(Color.black);
        g.drawString(Integer.toString(data),13,25);
    }

    public class CustomListenet implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (!connection) {
                    if (root != (Dot) e.getSource()) {
                        root = (Dot) e.getSource();
                        color = Color.ORANGE;
                        repaint();
                    } else {
                        if (root != null) {
                            root.color = Color.green;
                            root.repaint();
                            root = null;
                        }
                    }
                } else {
                    Dot child = (Dot) e.getSource();
                    if (child != root && root != null && !DataMapper.is_connected(root, child)) {
                        int weight;
                        String inp = JOptionPane.showInputDialog("Введите вес линии");
                        if (inp == "") {
                            weight = 1;
                        } else {
                            weight = Integer.parseInt(inp);
                        }
                        Edge edge = new Edge(root, child, weight);
                        child.edges_child.add(edge);
                        root.edges_root.add(edge);
                        GraphPanel.MainFrame.add(edge.getLine());
                        edge.getLine().setBounds(0, 0, 600, 600);
                        GraphPanel.MainFrame.repaint();
                    }
                    root.color = Color.green;
                    root.repaint();
                    repaint();
                    root = null;
                }
                connection = !connection;
            }
            if(e.getButton() == MouseEvent.BUTTON3){
                Dot dot = (Dot)e.getSource();
                for(Edge edge: dot.edges_child){
                    GraphPanel.MainFrame.remove(edge.getLine());
                }
                DataMapper.edges.removeIf(edge -> edge.child == dot);
                GraphPanel.MainFrame.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            draggedAtX = e.getX();
            draggedAtY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Dot dot = (Dot)e.getSource();
            dot.setColor(Color.CYAN);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Dot dot = (Dot) e.getSource();
            if(connection){
                if(dot != root) {
                    dot.setColor(Color.GREEN);
                }
                else{
                    dot.setColor(Color.orange);
                }
            }
            else {
                dot.setColor(Color.green);
            }
        }
    }
}
