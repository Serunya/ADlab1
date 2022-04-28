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
    public static void input(){
        System.out.println(edges.size());
        for(int i = 0;i < edges.size();i++){
            Edge r = edges.get(i);
            System.out.println(r.root.data);
        }
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
