package Graphics;
import Data.DataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Edge{
    public Dot root;
    public Dot child;
    public Line line;
    public int weight;

    Edge(Dot root, Dot child, int weight){
        this.root = root;
        this.child = child;
        this.weight = weight;
        this.line = new Line();
        DataMapper.add_edges(this);
    }

    public Edge(Dot root, Dot child, double weight){
        this.root = root;
        this.child = child;
        this.weight = (int)weight;
        this.line = new Line();
        DataMapper.add_edges(this);
    }

    public Edge(Dot root, Dot child, String text){
        this.root = root;
        this.child = child;
        this.weight = Integer.MAX_VALUE;
        this.line = new Line();
        DataMapper.add_edges(this);
        line.setText(text);
    }


    public Line getLine() {
        return line;
    }

    //Отрисока Линии
    public class Line extends JComponent { ;
        public int x;
        public int y;
        public int x1;
        public int y1;
        String text_weight;
        Color color = Color.black;

        public void setColor(Color color){
            this.color = color;
        }

        private Line(){
            x = root.getX()+20;
            y = root.getY()+20;
            x1 = child.getX()+20;
            y1 = child.getY()+20;
            this.text_weight = Integer.toString(weight);
        }

        public void setText(String text){
            this.text_weight = text;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(color);
            Graphics2D g2 = (Graphics2D) g;
            if(!GraphPanel.neograph) {
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
            g.drawLine(x, y, x1, y1);
            g.setColor(Color.red);
            g2.setFont(new Font( "M", Font.PLAIN, 18 ));
            g.drawString(text_weight,(x+x1)/2,(y+y1)/2);
            doLayout();
        }

    }
}
