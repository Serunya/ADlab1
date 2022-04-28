import javax.swing.*;
import java.awt.*;

public class Line extends JComponent {
    int x;
    int y;
    int x1;
    int y1;


    Line(Dot root,Dot child){
        x = root.getX()+20;
        y = root.getY()+20;
        x1 = child.getX()+20;
        y1 = child.getY()+20;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        double ang = Math.atan2(y1-y,x1-x);
        double tempc = Math.cos(ang);
        double temps = Math.sin(ang);
        int tempx1 = (int) (x1 - tempc * 23);
        int tempy1 = (int) (y1 - temps * 23);
        g2.setStroke(new BasicStroke(2));
        g.fillOval(tempx1-4,(tempy1-4),10,10);
        g.drawLine(x,y,x1,y1);
        doLayout();
    }
}
