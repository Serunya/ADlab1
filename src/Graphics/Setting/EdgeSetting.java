package Graphics.Setting;

import Graphics.Edge;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EdgeSetting extends JComponent {
    private static Image ico_delete;

    static {
        try {
            ico_delete = ImageIO.read(new File("res/Image/ico/delete_icon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Image ico_edit;
    Edge edge;
    public EdgeSetting(Edge edge){
        this.edge = edge;
    }

    @Override
    public void paint(Graphics g) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        g.drawString(edge.root.data + " ---> " + edge.child.data,10,10);
        g.drawImage(ico_delete,10,50,null);
    }

    @Override
    public void paintComponent(Graphics g){

    }

}
