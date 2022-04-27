import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Dot extends JComponent {
    String data;
    static boolean connection = false;
    static Dot root = null;
    Color color = Color.GREEN;
    CustomListenet listenet = new CustomListenet();
    Dot(String data){
        this.data = data;
    }

    Dot(int data){
        this.data = Integer.toString(data);
    }

    @Override
    public void paint(Graphics g) {
        removeMouseListener(listenet);
        addMouseListener(listenet);
        super.paint(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.fillOval(1, 1, 40, 40);
        g2.setColor(Color.black);
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
