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
        g.drawLine(x,y,x1,y1);
        System.out.println("f");
    }
}
