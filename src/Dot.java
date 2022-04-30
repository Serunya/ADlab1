import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Dot extends JComponent{
    String data;
    static boolean connection = false;
    static Dot root = null;
    private volatile int draggedAtX, draggedAtY;
    public ArrayList<Line> lines_root = new ArrayList<>();
    public ArrayList<Line> lines_child = new ArrayList<>();

    Color color = Color.GREEN;
    CustomListenet listenet = new CustomListenet();

    Dot(String data){
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
        g.drawString(data,13,25);
    }

    public class CustomListenet implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(!connection){
                if(root != (Dot) e.getSource()) {
                    root = (Dot) e.getSource();
                    color = Color.ORANGE;
                    repaint();
                }
                else{
                    if(root != null){
                        root.color = Color.green;
                        root.repaint();
                        root = null;
                    }
                }
            }
            else{
                Dot child = (Dot)e.getSource();
                if(child != root && root != null && !Edge.is_connected(root,child)){
                    new Edge(root,child);
                    Line line = new Line(root,child);
                    child.lines_child.add(line);
                    root.lines_root.add(line);
                    MainPanel.MainFrame.add(line);
                    line.setBounds(0,0,600,600);
                    MainPanel.MainFrame.repaint();
                }
                root.color = Color.green;
                root.repaint();
                repaint();
                root = null;
            }
            connection = !connection;
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
