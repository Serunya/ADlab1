import javax.swing.*;
import java.awt.*;

public class Line extends JComponent {
    int x;
    int y;
    int x1;
    int y1;

    Line(Dot root,Dot child){
        x = root.getX()+40;
        y = root.getY()+40;
        x1 = child.getX()+40;
        y1 = child.getY()+40;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawLine(x,y,x1,y1);
    }
}
