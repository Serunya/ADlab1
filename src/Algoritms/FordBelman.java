package Algoritms;

import Data.DataMapper;
import Graphics.Dot;
import Graphics.Edge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FordBelman extends Algoritm {
    ArrayList<Double> labels = new ArrayList<>();
    double[] dist;

    public FordBelman(){
        super("Форд-Белман");
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
    public void algoritm() {
        dist = new double[dots.size()];
        dist[0] = 0;
        for(int i = 1; i < dist.length; i++){
            dist[i] = Double.MAX_VALUE;
        }
        for(int i = 1; i < dots.size();i++){
            for(Edge v: edges){
                if( dist[v.child.data] > dist[v.root.data] + v.weight)
                    dist[v.child.data] = dist[v.root.data] + v.weight;
            }
        }

        for(int k = 1;k < dots.size();k++) {
            for (int i = 0; i < dots.size(); i++) {
                for (int j = 0; j < dots.size(); j++) {
                    labels.add(dist[j] + get_weight(j, i));
                }
                dist[i] = minOfArrayList(labels);
                labels.clear();
            }
        }
        //Заработало
        new visual_result(dist);
        Algoritm.current_algoritm.end = true;
    }

    @Override
    public void next_step() {

    }

    @Override
    public void prev_step() {

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
            dot.removeMouseListener(dot.listenet);
            int R = 25 * dist.length-1;
            for(int i = 1;i < dist.length;i++){
                double ang = 2*3.1416*(i)/dist.length -2 ;
                int x = (int) (250 + R * Math.cos(ang));
                int y = (int) (230 + R * Math.sin(ang));
                dot = new Dot(dots.get(i).data);
                add(dot);
                dots.set(i,dot);
                dot.removeMouseListener(dot.listenet);
                dot.setBounds(x,y,43,43);
            }

            for(int i = 1; i < dist.length;i++) {
                Edge edge;
                if(res[i] == Double.MAX_VALUE)
                    edge = new Edge(dots.get(0), dots.get(i), "∞");
                else
                    edge = new Edge(dots.get(0),dots.get(i), res[i]);
                edge.getLine().setBounds(0, 0, 600, 600);
                add(edge.getLine());
            }
        }
    }
}