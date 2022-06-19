package Algoritms;

import Data.DataMapper;
import Graphics.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchWidth extends Algoritm{
    private class Data{
        private Dot root;
        private Dot child;
        Data(Dot root,Dot child){
            this.root = root;
            this.child = child;
        }
    }


    public SearchWidth() {
        super("Поиск в ширину");
    }

    private ArrayList<Dot> history = new ArrayList<>();
    private ArrayList<Dot> checked_dots = new ArrayList<>();
    private ArrayList<Data> root_child = new ArrayList<>();
    private ArrayList<Dot> queue = new ArrayList<>();
    private Dot temp_dot;
    private Dot head;
    @Override
    protected void algoritm() {
        head = dots.get(0);
        temp_dot = dots.get(0);
        history.add(temp_dot);
        queue = get_child_dot(temp_dot);
        temp_dot.setColor(Color.blue);
        checked_dots.add(temp_dot);
    }
    @Override
    public void next_step() {
        if(queue.isEmpty()){
            temp_dot.setColor(Color.pink);
            checked_dots.add(temp_dot);
            root_child.add(new Data(temp_dot,get_next_child()));
            temp_dot = root_child.get(root_child.size()-1).child;
            if(temp_dot == null){
                temp_dot = get_root(temp_dot);
                for(Data d: root_child){
                    System.out.println(d.root.data + " " + d.child.data);
                }
                if(temp_dot == null){
                    MainMenu.context.next_step_button.setVisible(false);
                }
            }
            queue = get_child_dot(temp_dot);
            history.add(temp_dot);
            temp_dot.setColor(Color.blue);
        }
        else{
            Dot temp = queue.get(0);
            temp.setColor(Color.MAGENTA);
            history.add(temp);
            queue.remove(temp);
        }
    }

    @Override
    public void prev_step() {

    }

    private ArrayList<Dot> get_child_dot(Dot temp){
        ArrayList<Dot> child = new ArrayList<>();
        for(Edge edge: edges){
            if(edge.root == temp && !checked_dots.contains(edge.child))
                child.add(edge.child);
        }
        return child;
    }

    private Dot get_root(Dot dot){
        for(Data d: root_child){
            if(d.child == dot){
                System.out.println(d.child.data);
                System.out.println(d.root.data);
                return d.root;
            }
        }
        return null;
    }

    private Dot get_next_child(){
        ArrayList<Dot> childs = DataMapper.get_child_dot(temp_dot);
        for(Dot dot: childs){
            if(!checked_dots.contains(dot)){
                return dot;
            }
        }
        return null;
    }
}
