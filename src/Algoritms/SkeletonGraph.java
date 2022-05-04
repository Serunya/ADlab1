package Algoritms;

import java.awt.*;
import java.util.ArrayList;
import Graphics.Dot;
import Graphics.Edge;
import Graphics.Line;

public class SkeletonGraph implements Runnable {
    ArrayList<Dot> dots = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    ArrayList<Edge> skeletone_edges = new ArrayList<Edge>();

    public SkeletonGraph(ArrayList<Dot> dots, ArrayList<Edge> edges){
        this.dots.addAll(dots);
        this.edges.addAll(edges);
    }
    public void out(){
        for(int i = 0; i < edges.size();i++){
            System.out.print("Родитель: ");
            System.out.println(edges.get(i).root);
            System.out.print("Ребенок: ");
            System.out.println(edges.get(i).child);
        }
    }
    public Dot get_min_edge(ArrayList<Dot> roots){
        int min_weight = 10000;
        Edge min_edge = null;
        for(int i = 0;i < roots.size();i++){
            for(int j = 0;j < edges.size();j++){
                if(edges.get(j).root == roots.get(i) && !roots.contains(edges.get(j).child)){
                    if(edges.get(j).weight < min_weight){
                        min_weight = edges.get(j).weight;
                        min_edge = edges.get(j);
                    }
                }
                if(edges.get(j).child == roots.get(i) && !roots.contains(edges.get(j).root)){
                    if(edges.get(j).weight < min_weight){
                        min_weight = edges.get(j).weight;
                        min_edge = edges.get(j);
                    }
                }
            }
        }
        skeletone_edges.add(min_edge);
        edges.remove(min_edge);
        min_edge.line.setColor(Color.MAGENTA);
        min_edge.line.repaint();
        if(roots.contains(min_edge.root)){
            return  min_edge.child;
        }
        else {
            return min_edge.root;
        }
    }

    @Override
    public void run() {
        try {
            Dot temp = dots.get(0);
            dots.remove(temp);
            ArrayList<Dot> skeleton = new ArrayList<>();
            while (dots.size() > 0){
                Thread.sleep(1000);
                temp.setColor(Color.MAGENTA);
                skeleton.add(temp);
                temp = get_min_edge(skeleton);
                dots.remove(temp);
            }
            Thread.sleep(1000);
            temp.setColor(Color.MAGENTA);
            /// Просмотр результата
            for(int i = 0; i < edges.size();i++){
                edges.get(i).line.setVisible(false);
            }
            Thread.sleep(20000);
            for(int i = 0; i < edges.size();i++){
                edges.get(i).line.setVisible(true);
            }
            for(int i = 0; i < skeletone_edges.size();i++){
                skeletone_edges.get(i).line.setColor(Color.black);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
