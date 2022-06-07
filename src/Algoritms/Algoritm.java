package Algoritms;

import Data.DataMapper;
import Graphics.Dot;
import Graphics.Edge;

import java.util.ArrayList;

public abstract class Algoritm implements Runnable{
    private String name;
    ArrayList<Dot> dots;
    ArrayList<Edge> edges;

    Algoritm(String name){
        this.name = name;
        DataMapper.algoritms.add(this);
    }

    @Override
    public void run() {
        this.dots = DataMapper.getDots();
        this.edges = DataMapper.getEdges();
        algoritm();
    }

    public abstract void algoritm();

    public String getName() {
        return name;
    }
}
