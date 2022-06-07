package Data;

import Algoritms.Algoritm;
import Graphics.Dot;
import Graphics.Edge;

import java.util.ArrayList;

public class DataMapper {
    public static ArrayList<Dot> dots = new ArrayList<>();
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static ArrayList<Algoritm> algoritms = new ArrayList<>();


    public static boolean add_edges(Edge edge){
        if(!edges.contains(edge)){
            edges.add(edge);
            return true;
        }
        return false;
    }

    public static boolean add_dots(Dot dot){
        if(!dots.contains(dot)){
            dots.add(dot);
            return true;
        }
        return false;
    }

    public static ArrayList<Dot> getDots() {
        return dots;
    }

    public static ArrayList<Edge> getEdges() {
        return edges;
    }

    //Работа с связями
    public static ArrayList<Dot> get_edges(Dot root){
        ArrayList<Dot> return_edges = new ArrayList<Dot>();
        for(int i = 0; i < edges.size();i++){
            if (edges.get(i).root == root){
                return_edges.add(edges.get(i).child);
            }
        }
        return return_edges;
    }

    public static Edge get_edge(int i, int j){
        for(Edge v: edges){
            if(v.root.data == i && v.child.data == j){
                return v;
            }
        }
        return null;
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
