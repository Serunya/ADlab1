package Algoritms;

import Graphics.*;

import java.awt.*;
import java.util.ArrayList;

public class SkeletonGraph extends Algoritm {
    public SkeletonGraph() {
        super("Остов графа");
    }

    private ArrayList<Dot> visited = new ArrayList<>();
    private ArrayList<Edge> skeleton_edges = new ArrayList<>();
    private int count_dots;
    private Dot temp;

    @Override
    protected void algoritm() {
        visited.clear();
        skeleton_edges.clear();
        count_dots = dots.size();
        temp = dots.get(0);
        temp.setColor(Color.MAGENTA);
        visited.add(temp);
        dots.remove(temp);
    }

    @Override
    public void next_step() {
        temp = get_min_edge(visited);
        assert temp != null;
        temp.setColor(Color.MAGENTA);
        visited.add(temp);
        dots.remove(temp);
        show_interace_button();
    }

    @Override
    public void prev_step() {
        dots.add(visited.get(visited.size()-1));
        dots.get(dots.size()-1).setColor(Color.green);
        edges.add(skeleton_edges.get(skeleton_edges.size()-1));
        edges.get(edges.size()-1).line.setColor(Color.BLACK);
        visited.remove(visited.size()-1);
        skeleton_edges.remove(skeleton_edges.size()-1);
        show_interace_button();
    }

    public void show_interace_button(){
        GraphPanel.MainFrame.repaint();
        if (!dots.isEmpty())
            MainMenu.context.next_step_button.setVisible(true);
        else
            MainMenu.context.next_step_button.setVisible(false);
        if (visited.size() < 2)
            MainMenu.context.prev_step_button.setVisible(false);
        else
            MainMenu.context.prev_step_button.setVisible(true);
        MainMenu.context.repaint();
    }

    private Dot get_min_edge(ArrayList<Dot> roots) {
        int min_weight = 10000;
        Edge min_edge = null;
        for (int i = 0; i < roots.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).root == roots.get(i) && !roots.contains(edges.get(j).child)) {
                    if (edges.get(j).weight < min_weight) {
                        min_weight = edges.get(j).weight;
                        min_edge = edges.get(j);
                    }
                }
                if (edges.get(j).child == roots.get(i) && !roots.contains(edges.get(j).root)) {
                    if (edges.get(j).weight < min_weight) {
                        min_weight = edges.get(j).weight;
                        min_edge = edges.get(j);
                    }
                }
            }
        }
        if(min_edge == null)
            return null;
        skeleton_edges.add(min_edge);
        edges.remove(min_edge);
        min_edge.line.setColor(Color.MAGENTA);
        min_edge.line.repaint();
        if(roots.contains(min_edge.root)){
            return  min_edge.child;
        }
        else {
            return min_edge.root;
        }
    }
}