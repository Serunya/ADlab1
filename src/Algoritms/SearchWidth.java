package Algoritms;

import Data.DataMapper;
import Graphics.*;

import java.awt.*;
import java.util.ArrayList;

public class SearchWidth extends Algoritm{
    public SearchWidth() {
        super("Поиск в Ширину");
    }

    boolean next = true;
    @Override
    protected void algoritm() {
        MainMenu.context.next_step_button.setBounds(625, 610, 150, 30);
        ArrayList<Dot> queue = new ArrayList<>();
        ArrayList<Dot> BFS = new ArrayList<>();
        queue.add(dots.remove(0));
        while(!dots.isEmpty()){
            System.out.println(dots.size());
            if(!queue.isEmpty()) {
                BFS.add(queue.get(0).setColor(Color.blue));
                dots.remove(queue.remove(0));
            }
            else{
                System.out.println("Наполняю очередь");
                for(Dot dot: BFS){
                    ArrayList<Dot> temp = DataMapper.get_child_dot(dot);
                    temp.removeAll(BFS);
                    queue.addAll(temp);
                }
                for(Dot dot: queue)
                    dot.setColor(Color.GRAY);
            }
            // Костыль на шаги)
            while(next){
                Thread.yield();
            }
            next = true;
        }
        MainMenu.context.next_step_button.setBounds(705,610,70,30);
        current_algoritm.end = true;
    }

    @Override
    public void next_step() {
        next = false;
    }

    @Override
    public void prev_step() {

    }
}
