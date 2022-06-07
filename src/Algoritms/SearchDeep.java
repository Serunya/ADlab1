package Algoritms;
import java.awt.*;
import java.util.ArrayList;

import Data.DataMapper.*;
import Data.DataMapper;
import Graphics.Dot;
import Graphics.Edge;

public class SearchDeep extends Algoritm {
    int search_item;

    public void all_green(ArrayList<Dot> dots){
        for(int i = 0; i < dots.size(); i++){
            dots.get(i).setColor(Color.green);
        }
    }

    public SearchDeep() {
        super("Поиск в глубину 1");
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

    public Boolean isConnected(Dot root, Dot child){
        for(int i = 0;i < edges.size();i++){
            if(edges.get(i).root == root & edges.get(i).child == child){
                return true;
            }
        }
        return false;
    }

    public Dot not_cheked_child(Dot root, ArrayList<Dot> cheked_dots) {
        ArrayList<Dot> childs = get_all_child(root);
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
    public void algoritm() {
        try {
            this.search_item = dots.size();
            all_green(dots);
            int steps = 0;
            /// Вершины
            Dot head = dots.get(0);
            Dot temp = dots.get(0);
            /// Листы
            ArrayList<Dot> cheked_dots = new ArrayList<>();
            ArrayList<Dot> history = new ArrayList<>();
            // list for dream
            ArrayList<int[]> UT = new ArrayList<int[]>();
            ArrayList<int[]> UB = new ArrayList<int[]>();
            ArrayList<int[]> UF = new ArrayList<int[]>();
            ArrayList<int[]> UC = new ArrayList<int[]>();

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
                Dot next_child = not_cheked_child(temp,cheked_dots);
                //Против циклов
                if(history.contains(next_child)){
                    history.addAll(cheked_dots);
                    next_child = not_cheked_child(temp,history);
                    history.removeAll(cheked_dots);
                }
                // Проверка элемента на нахождение
                if(temp.data == search_item){
                    temp.setColor(Color.magenta);
                    break;
                }
                // Если все элементы проверенны
                if(temp == head && next_child == null){
                    steps = -1;
                    break;
                }
                // Возвращение назад
                if(next_child == null){
                    if(isConnected(temp,history.get(history.size()-2))){
                        temp.setColor(Color.pink);
                        temp = history.get(history.size()-2);
                        steps -= 2;
                    }
                    else {
                        cheked_dots.add(temp);
                        temp.setColor(Color.pink);
                        temp = head;
                        steps = 0;
                        history.clear();
                    }
                }
                else{
                    UT.add(new int[]{temp.data,next_child.data});
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