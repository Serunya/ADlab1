package Algoritms;

import Graphics.Dot;
import Graphics.Edge;
import Graphics.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FordBelman implements Runnable{
    ArrayList<Edge> edges = new ArrayList<>();
    ArrayList<Dot> dots = new ArrayList<>();
    ArrayList<Double> labels = new ArrayList<>();
    double[] dist;

    public FordBelman(ArrayList<Dot> dots, ArrayList<Edge> edges, Dot src){
        this.edges.addAll(edges);
        this.dots.addAll(dots);
        dist = new double[dots.size()];
        dist[0] = 0;
        dots.set(dots.indexOf(src), dots.get(0));
        dots.set(0,src);
        for(int i = 1; i < dist.length; i++){
            dist[i] = Double.MAX_VALUE;
        }
    }

    public double get_weight(int a, int b){
        for(int i = 0;i < edges.size();i++){
            Edge edge = edges.get(i);
            if(edge.root.data == a && edge.child.data == b){
                return edge.weight;
            }
        }
        if(a == b){
            return 0;
        }
        return Double.MAX_VALUE;
    }

    public double minOfArrayList(ArrayList<Double> array){
        double min_elem = Double.MAX_VALUE;
        for(int i = 0; i < array.size();i++ ){
            if(array.get(i) < min_elem){
                min_elem = array.get(i);
            }
        }
        return min_elem;
    }

    @Override
    public void run() {
        for(int k = 1;k <= dots.size() - 1;k++){
            for(int i = 0;i < dots.size();i++){
                for(int j = 0; j < dots.size();j++){
                    labels.add(dist[j] + get_weight(j,i));
                }
                System.out.println();
                dist[i] = minOfArrayList(labels);
                labels.clear();
            }
        }
        //Заработало
        new visual_result(dist);
    }


    class visual_result extends JFrame{
        visual_result(double[] res){
            super("Форд белман");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setBackground(Color.WHITE);
            setSize(600,600);
            setLayout(null);
            setVisible(true);
            Dot dot = new Dot(dots.get(0).data);
            dots.set(0,dot);
            dot.setBounds(250,230,43,43);
            add(dot);
            dot.listenet = null;
            int R = 25 * dist.length-1;
            for(int i = 1;i < dist.length;i++){
                double ang = 2*3.1416*(i)/dist.length -2 ;
                int x = (int) (250 + R * Math.cos(ang));
                int y = (int) (230 + R * Math.sin(ang));
                dot = new Dot(dots.get(i).data);
                add(dot);
                dots.set(i,dot);
                dot.listenet = null;
                dot.setBounds(x,y,43,43);
            }

            for(int i = 1; i < dist.length;i++) {
                Line line;
                if(res[i] == Double.MAX_VALUE)
                    line = new Line(dots.get(0), dots.get(i), "∞");
                else
                    line = new Line(dots.get(0),dots.get(i), res[i]);
                line.setBounds(0, 0, 600, 600);
                add(line);
            }
        }
    }
}

