package Algoritms;

import Data.DataMapper;
import Graphics.Dot;
import Graphics.Edge;
import Graphics.GraphPanel;
import Graphics.MainMenu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Algoritm implements Runnable{
    public volatile static Algoritm current_algoritm;
    protected ArrayList<Dot> dots = new ArrayList<>();
    protected ArrayList<Edge> edges = new ArrayList<>();
    private final String name;
    public boolean end = false;
    public String getName() {
        return name;
    }

    public Algoritm(String name){
        this.name = name;
        DataMapper.add_algoritm(this);
    }

    @Override
    public void run() {
        current_algoritm = this;
        dots.clear();
        edges.clear();
        dots.addAll(DataMapper.getDots());
        edges.addAll(DataMapper.getEdges());
        MainMenu.context.prev_step_button.setVisible(false);
        MainMenu.context.next_step_button.setVisible(true);
        MainMenu.context.end_algoritm_button.setVisible(true);
        MainMenu.context.repaint();
        try {
            algoritm();
        } catch (IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(MainMenu.context,"Вы не ввели граф","Не введен гарф",JOptionPane.ERROR_MESSAGE);
        }
        while (!end)
            Thread.yield();
        end = false;
        MainMenu.context.prev_step_button.setVisible(false);
        MainMenu.context.next_step_button.setVisible(false);
        MainMenu.context.end_algoritm_button.setVisible(false);
        current_algoritm = null;
        MainMenu.context.show_buttons_algoritm();
        GraphPanel.default_view();
        MainMenu.context.repaint();
    }

    protected abstract void algoritm();
    public abstract void next_step();
    public abstract void prev_step();
}
