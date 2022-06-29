package Algoritms;

import java.awt.*;
import java.util.ArrayList;

import Graphics.*;

public class SearchDeep extends Algoritm {
    int search_item;

    public void all_green(ArrayList<Dot> dots) {
        for (int i = 0; i < dots.size(); i++) {
            dots.get(i).setColor(Color.green);
        }
    }

    public SearchDeep() {
        super("Поиск в глубину");
    }

    public ArrayList<Dot> get_all_child(Dot root) {
        ArrayList<Dot> all_child = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.root == root) {
                all_child.add(edge.child);
            }
            if(edge.child == root){
                all_child.add(edge.root);
            }
        }
        return all_child;
    }

    public Boolean isConnected(Dot root, Dot child) {
        for (int i = 0; i < edges.size(); i++) {
            if ((edges.get(i).root == root && edges.get(i).child == child) || (edges.get(i).root == child && edges.get(i).child == root)) {
                return true;
            }
        }
        return false;
    }

    public Dot not_cheked_child(Dot root, ArrayList<Dot> cheked_dots) {
        ArrayList<Dot> childs = get_all_child(root);
        if (cheked_dots.size() == 0 && childs.size() != 0) {
            return childs.get(0);
        }
        for (int i = 0; i < childs.size(); i++) {
            if (!cheked_dots.contains(childs.get(i))) {
                return childs.get(i);
            }
        }
        return null;
    }


    private boolean next = true;
    private boolean have_dupliate(ArrayList<Dot> dots){
        for(int i = 0; i < dots.size()-1; i++){
            for(int j = i+1; j < dots.size();j++){
                if(dots.get(i) == dots.get(j))
                    return true;
            }
        }
        return false;
    }
    @Override
    public void algoritm() {
        MainMenu.context.next_step_button.setVisible(true);
        MainMenu.context.next_step_button.setBounds(625, 610, 150, 30);
        this.search_item = dots.size();
        all_green(dots);
        int steps = 0;
        /// Вершины
        Dot head = dots.get(0);
        Dot temp = dots.get(0);
        /// Листы
        ArrayList<Dot> cheked_dots = new ArrayList<>();
        ArrayList<Dot> history = new ArrayList<>();
        cheked_dots.add(head);
        ArrayList<Dot> childs;
        /// Красота
        temp.setColor(Color.blue);
        while (next) {
            Thread.yield();
        }
        next = true;
        /// Сам алгоритм
        do {
            temp.setColor(Color.green);
            history.add(temp);
            Dot next_child = not_cheked_child(temp, cheked_dots);
            // Проверка элемента на нахождение
            if (temp.data == search_item) {
                temp.setColor(Color.magenta);
                break;
            }
            // Если все элементы проверенны
            if (temp == head && next_child == null) {
                steps = -1;
                break;
            }
            if (history.contains(next_child)) {
                history.addAll(cheked_dots);
                next_child = not_cheked_child(temp,history);
                history.removeAll(cheked_dots);
                if(next_child == null) {
                    next_child = not_cheked_child(temp, cheked_dots);
                    cheked_dots.add(temp);
                    temp.setColor(Color.pink);
                }
            }
            // Возвращение назад
            if (next_child == null) {
                cheked_dots.add(temp);
                temp.setColor(Color.pink);
                temp = head;
                history.clear();
            } else {
                temp = next_child;
            }
            temp.setColor(Color.blue);
            while (next) {
                Thread.yield();
            }
            next = true;
        } while (true);
        MainMenu.context.next_step_button.setVisible(false);
        MainMenu.context.next_step_button.setBounds(705, 610, 70, 30);
        Algoritm.current_algoritm.end = true;
    }

    @Override
    public void next_step() {
        next = false;
    }

    @Override
    public void prev_step() {

    }
}