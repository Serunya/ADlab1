package Graphics.Setting;

import Graphics.Edge;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EdgeSetting extends JComponent {
    Edge edge;
    EdgeSetting(Edge edge){
        this.edge = edge;
    }

    @Override
    public void paint(Graphics g) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setBounds(0,0,150,20);
        g.drawString(edge.root.data + " ---> " + edge.child.data,10,10);
    }

    @Override
    public void paintComponent(Graphics g){

    }

}
