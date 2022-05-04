package Graphics;

import java.util.ArrayList;

public class Edge{
    public static ArrayList<Edge> edges = new ArrayList<Edge>();
    public Dot root;
    public Dot child;
    public Line line;
    public int weight;

    public static Edge get_edges_from_line(Line line){
        for(int i = 0; i < edges.size();i++){
            if(edges.get(i).line == line){
                edges.remove(i);
                return edges.get(i);
            }
        }
        return null;
    }
    Edge(Dot root, Dot child, Line line, int weight){
        this.root = root;
        this.child = child;
        this.weight = weight;
        this.line = line;
        edges.add(this);
    }

    public static ArrayList<Dot> get_edges(Dot root){
        ArrayList<Dot> return_edges = new ArrayList<Dot>();
        for(int i = 0; i < edges.size();i++){
            if (edges.get(i).root == root){
                return_edges.add(edges.get(i).child);
            }
        }
        return return_edges;
    }

    public static boolean is_connected(Dot root, Dot child){
        for(int i = 0; i < edges.size();i++){
            if(edges.get(i).root == root && edges.get(i).child == child){
                return true;
            }
        }
        return false;
    }
}
