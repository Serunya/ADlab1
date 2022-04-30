import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchDeep implements Runnable {
    ArrayList<Dot> dots;
    ArrayList<Edge> edges;
    String search_item;

    public void all_green(ArrayList<Dot> dots){
        for(int i = 0; i < dots.size(); i++){
            dots.get(i).setColor(Color.green);
        }
    }

    SearchDeep(ArrayList<Dot> dots, ArrayList<Edge> edges, String search_item) {
        this.search_item = search_item;
        this.dots = dots;
        this.edges = edges;
        all_green(dots);
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
            ArrayList<Dot> history = new ArrayList<>();
            ///
            cheked_dots.add(head);
            ArrayList<Dot> childs;
            /// Красота
            temp.setColor(Color.blue);
            Thread.sleep(500);
            temp.setColor(Color.green);
            /// Сам алгоритм
            do {
                history.add(temp);
                childs = get_all_child(temp);
                Dot next_child = not_cheked_child(childs,cheked_dots);

                //Против циклов
                if(history.contains(next_child)){
                    history.addAll(cheked_dots);
                    next_child = not_cheked_child(childs,history);
                }
                // Проверка элемента на нахождение
                if(temp.data.equals(search_item)){
                    temp.setColor(Color.magenta);
                    break;
                }
                // Если все элементы проверенны
                if(temp == head && next_child == null){
                    steps = -1;
                    break;
                }
                // Возвращение в голову
                if(next_child == null){
                    cheked_dots.add(temp);
                    temp.setColor(Color.pink);
                    temp = head;
                    steps = 0;
                    history.clear();
                }
                else{
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
