import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchDeep implements Runnable {
    ArrayList<Dot> dots;
    ArrayList<Edge> edges;
    String search_item;

    SearchDeep(ArrayList<Dot> dots, ArrayList<Edge> edges, String search_item) {
        this.search_item = search_item;
        this.dots = dots;
        this.edges = edges;
    }

    public ArrayList<Dot> get_all_child(Dot root) {
        ArrayList<Dot> all_child = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.root == root) {
                all_child.add(edge.child);
            }
        }
        return all_child;
    }

    public Boolean isConnected(Dot root,Dot child){
        for(int i = 0;i < edges.size();i++){
            if(edges.get(i).root == root & edges.get(i).child == child){
                return true;
            }
        }
        return false;
    }

    public void out(ArrayList<Dot> arr){
        for(int i = 0;i < arr.size();i++){
            System.out.print(arr.get(i).data + " ");
        }
        System.out.println();
    }

    public Dot not_cheked_child(ArrayList<Dot> childs, ArrayList<Dot> cheked_dots) {
        if(cheked_dots.size() == 0 && childs.size() != 0){
            return childs.get(0);
        }
        for(int i = 0;i < childs.size();i++){
            if(!cheked_dots.contains(childs.get(i))){
                return childs.get(i);
            }
        }
        return null;
    }

    @Override
    public void run() {
        try {
            int steps = 0;
            /// Вершины
            Dot head = dots.get(0);
            Dot temp = dots.get(0);
            Dot prev = dots.get(0);
            /// Листы
            ArrayList<Dot> cheked_dots = new ArrayList<>();
            cheked_dots.add(head);
            ArrayList<Dot> childs;
            temp.setColor(Color.blue);
            Thread.sleep(500);
            temp.setColor(Color.green);
            do {
                childs = get_all_child(temp);
                cheked_dots.add(prev);
                Dot next_child = not_cheked_child(childs,cheked_dots);
                cheked_dots.remove(prev);
                if(temp.data.equals(search_item)){
                    temp.setColor(Color.magenta);
                    break;
                }

                if(temp == head && next_child == null){
                    steps = -1;
                    break;
                }

                if(next_child == null){
                    prev = head;
                    cheked_dots.add(temp);
                    temp.setColor(Color.pink);
                    temp = head;
                    steps = 0;
                }

                else{
                    prev = temp;
                    temp = next_child;
                }
                temp.setColor(Color.blue);
                Thread.sleep(500);
                temp.setColor(Color.green);
                steps += 1;
            } while (true);
            System.out.println("Количество шагов: " + steps);

        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
