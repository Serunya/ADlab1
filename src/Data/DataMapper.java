package Data;

import Algoritms.Algoritm;
import Graphics.Dot;
import Graphics.Edge;

import java.util.ArrayList;

public class DataMapper {
    public static ArrayList<Dot> dots = new ArrayList<>();
    public static ArrayList<Edge> edges = new ArrayList<>();
    private static final ArrayList<Algoritm> algoritms = new ArrayList<>();
    public static ArrayList<Algoritm> get_algoritms(){
        return algoritms;
    }

    public static ArrayList<Dot> get_child_dot(Dot temp){
        ArrayList<Dot> childs = new ArrayList<>();
        for(Edge edge:edges){
            Dot child = null;
            if(edge.root == temp)
                child = edge.child;
            if(edge.child == temp)
                child = edge.root;
            if(!childs.contains(child) && child != null)
                childs.add(child);

        }
        return childs;
    }
    public static boolean add_algoritm(Algoritm algoritm){
        for(Algoritm i: algoritms){
            if(i.getClass() == algoritm.getClass()){
                return false;
            }
        }
        algoritms.add(algoritm);
        return true;
    }


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
            if((v.root.data == i && v.child.data == j)||(v.root.data == j && v.child.data == i)){
                return v;
            }
        }
        return null;
    }

    public static boolean is_connected(Dot root, Dot child){
        for(int i = 0; i < edges.size();i++){
            if((edges.get(i).root == root && edges.get(i).child == child) || (edges.get(i).child == root && edges.get(i).root == child)){
                return true;
            }
        }
        return false;
    }

}
