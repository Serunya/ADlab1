import java.util.ArrayList;
import java.util.Random;

public class RandomGraph{

    public static ArrayList<Dot> Rand(int number_of_vertices){
        ArrayList<Dot>  dots = new ArrayList<Dot>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(int i = 0;i < number_of_vertices;i++){
            Dot dot = new Dot(Integer.toString(i));
            dots.add(dot);
            MainPanel.MainFrame.add(dot);
            dot.setBounds(600/((int)Math.log(i)+2),i*50,43,43);
        }
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for(int i = 0; i < dots.size();i++){
            for(int j = 0; j < dots.size();j++){
                if(rand.nextInt(0,100) >= 75){
                    if(i != j) {
                        new Edge(dots.get(i), dots.get(j));
                        Line line = new Line(dots.get(i), dots.get(j));
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
