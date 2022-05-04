package Graphics;

import java.util.ArrayList;
import java.util.Random;

public class RandomGraph{

    public static ArrayList<Dot> Rand(int number_of_vertices){
        ArrayList<Dot>  dots = new ArrayList<Dot>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        Dot dot = new Dot(0);
        dots.add(dot);
        dot.setBounds(280,280,43,43);
        MainPanel.MainFrame.add(dot);
        int R = 25 * number_of_vertices;
        for(int i = 0;i < number_of_vertices;i++){
            double ang = 2*3.1416*i/number_of_vertices;
            int x = (int) (280 + R * Math.cos(ang));
            int y = (int) (280 + R * Math.sin(ang));
            dot = new Dot(i+1);
            dots.add(dot);
            MainPanel.MainFrame.add(dot);
            dot.setBounds(x,y,43,43);
        }
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for(int i = 0; i < dots.size();i++){
            for(int j = 0; j < dots.size();j++){
                if(rand.nextInt(0,100) >= 75){
                    if(i != j) {
                        int weight = rand.nextInt(0,15);
                        Line line = new Line(dots.get(i), dots.get(j),weight);
                        new Edge(dots.get(i), dots.get(j),line,weight);
                        dots.get(j).lines_child.add(line);
                        dots.get(i).lines_root.add(line);
                        MainPanel.MainFrame.add(line);
                        line.setBounds(0, 0, 600, 600);
                        MainPanel.MainFrame.repaint();
                    }
                }
            }
        }
        return dots;
    }

}
