import javax.swing.*;
import java.util.ArrayList;

public class Edge{
    public static ArrayList<Edge> edges = new ArrayList<Edge>();
    public Dot root;
    public Dot child;

    Edge(Dot root,Dot child){
        this.root = root;
        this.child = child;
        edges.add(this);
    }

    public static ArrayList<Edge> get_edges(Dot root){
        ArrayList<Edge> return_edges = new ArrayList<Edge>();
        for(int i = 0; i < edges.size();i++){
            if (edges.get(i).root == root){
                return_edges.add(edges.get(i));
            }
        }
        return return_edges;
    }

    public static boolean is_connected(Dot root, Dot child){
        for(int i = 0; i < edges.size();i++){
            if(edges.get(i).root == root && edges.get(i).child == child){
                return true;
            }
            if(edges.get(i).root == child && edges.get(i).child == root){
                return true;
            }
        }
        return false;
    }
}
