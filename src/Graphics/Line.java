package Graphics;

import javax.swing.*;
import java.awt.*;

public class Line extends JComponent {
    int x;
    int y;
    int x1;
    int y1;
    String weight;
    Color color = Color.black;

    public void setColor(Color color){
        this.color = color;
    }

    public Line(Dot root, Dot child, double weight){
        x = root.getX()+20;
        y = root.getY()+20;
        x1 = child.getX()+20;
        y1 = child.getY()+20;
        this.weight = Integer.toString((int)weight);

    }

    public Line(Dot root, Dot child, int weight){
        x = root.getX()+20;
        y = root.getY()+20;
        x1 = child.getX()+20;
        y1 = child.getY()+20;
        this.weight = Integer.toString(weight);

    }

    public Line(Dot root, Dot child, String weight){
        x = root.getX()+20;
        y = root.getY()+20;
        x1 = child.getX()+20;
        y1 = child.getY()+20;
        this.weight = weight;

    }

    public void setText(String text){
        this.weight = text;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        if(!MainPanel.neograph) {
            double ang = Math.atan2(y1 - y, x1 - x);
            double tempc = Math.cos(ang);
            double temps = Math.sin(ang);
            int tempx1 = (int) (x1 - tempc * 23);
            int tempy1 = (int) (y1 - temps * 23);
            g.fillOval(tempx1 - 4, (tempy1 - 4), 10, 10);
        }
        else {
            g2.setStroke(new BasicStroke(3));
        }
        g.drawLine(x,y,x1,y1);
        g.setColor(Color.red);
        g2.setFont(new Font( "M", Font.PLAIN, 18 ));
        g.drawString(weight,(x+x1)/2,(y+y1)/2);
        doLayout();
    }
}
