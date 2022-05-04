package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Dot extends JComponent{
    public int data;
    static boolean connection = false;
    static Dot root = null;
    private volatile int draggedAtX, draggedAtY;
    public ArrayList<Line> lines_root = new ArrayList<>();
    public ArrayList<Line> lines_child = new ArrayList<>();

    Color color = Color.GREEN;
    public CustomListenet listenet = new CustomListenet();

    public Dot(int data){
        this.data = data;
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                for (int i = 0; i < lines_child.size(); i++) {
                    Line line = lines_child.get(i);
                    line.x1 = e.getX() - draggedAtX + getLocation().x + 20;
                    line.y1 = e.getY() - draggedAtY + getLocation().y + 20;
                    lines_child.get(i).repaint();
                }
                for (int i = 0; i < lines_root.size(); i++) {
                    Line line = lines_root.get(i);
                    line.x = e.getX() - draggedAtX + getLocation().x + 20;
                    line.y = e.getY() - draggedAtY + getLocation().y + 20;
                    lines_root.get(i).repaint();
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
                    if (child != root && root != null && !Edge.is_connected(root, child)) {
                        int weight;
                        String inp = JOptionPane.showInputDialog("Введите вес линии");
                        if (inp == "") {
                            weight = 1;
                        } else {
                            weight = Integer.parseInt(inp);
                        }
                        Line line = new Line(root, child, weight);
                        new Edge(root, child, line, weight);
                        child.lines_child.add(line);
                        root.lines_root.add(line);
                        MainPanel.MainFrame.add(line);
                        line.setBounds(0, 0, 600, 600);
                    /*
                    if(MainPanel.neograph){
                        line = new Line(child,root,weight);
                        new Edge(child,root,line,weight);
                        child.lines_child.add(line);
                        root.lines_root.add(line);
                        MainPanel.MainFrame.add(line);
                        line.setBounds(0,0,600,600);
                    }
                     */
                        MainPanel.MainFrame.repaint();
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
                for(int i = 0; i < dot.lines_child.size();i++){
                    MainPanel.MainFrame.remove(dot.lines_child.get(i));
                    Edge.get_edges_from_line(dot.lines_child.get(i));
                    dot.lines_child.remove(i);
                }
                repaint();
                MainPanel.MainFrame.repaint();
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
