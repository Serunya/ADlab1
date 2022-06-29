package Algoritms;

import Data.DataMapper;
import Graphics.Dot;
import Graphics.Edge;
import Graphics.MainMenu;

import javax.swing.*;
import java.util.ArrayList;

import static java.lang.Math.min;

public class PreflowPushes extends Algoritm{
    int[] h;
    int[][] f;
    int[] e;
    final int INF = Integer.MAX_VALUE;

    private boolean next = true;
    public PreflowPushes(){
        super("Проталкивание предпотока");
    }

    public int c(int a,int b){
        for(int i = 0; i < edges.size();i++){
            if((edges.get(i).root.data == a && edges.get(i).child.data == b) || (edges.get(i).root.data == b && edges.get(i).child.data == a)){
                return edges.get(i).weight;
            }
        }
        return 0;
    }

    public void push(int u, int v){
        int d = min(e[u], c(u,v) - f[u][v]);
        f[u][v] += d;
        f[v][u] = -1 * f[u][v];
        e[u] -= d;
        e[v] += d;
    }

    public void lift(int u){
        int d = INF;
        for (int i = 0; i < dots.size(); i++)
            if (c(u,i) - f[u][i] > 0)
                d = min(d, h[i]);
        if (d == INF)
            return;
        h[u] = d + 1;
    }

    @Override
    public void algoritm() {
        MainMenu.context.next_step_button.setBounds(625,610,150,30);
        h = new int[dots.size()];
        for(int i = 0;i < dots.size();i++){
            h[i] = 0;
        }
        int n = dots.size();
        f = new int[n][n];
        for(int i = 1; i < dots.size();i++){
            f[0][i] = c(0,i);
            f[i][0] = -1 * c(0,i);
        }
        e = new int[n];
        h[0] = n;
        for(int i = 1; i < dots.size();i++){
            e[i] = f[0][i];
        }
        for(;;){
            while (next){
                Thread.yield();
            }
            next = true;
            output_flows();
            int i;
            for (i=1; i<n-1; i++)
                if (e[i] > 0)
                    break;
            if (i == n-1)
                break;
            int j;
            for (j=0; j<n; j++)
                if (c(i,j)-f[i][j] > 0 && h[i]==h[j]+1)
                    break;
            if (j < n)
                push (i, j);
            else {
                lift(i);
                next = false;
            }
        }
        int flow = 0;
        for (int i=0; i<n; i++)
            if (c(0,i) != INF) {
                flow += f[0][i];
            }
        MainMenu.context.next_step_button.setBounds(705,610,70,30);
        Algoritm.current_algoritm.end = true;
        JOptionPane.showMessageDialog(MainMenu.context,"Максимальный поток: " + flow,"Максимальный поток",JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Максимальный поток:" + flow);
    }

    private void output_flows(){
        for(int i = 0; i < f.length; i++){
            System.out.println(i + ": ");
            for(int j = 0; j < f.length;j++){
                if(c(i,j) > 0 && c(i,j) != INF){
                    System.out.println("\t" + (j + 1) + ": " + Math.abs(f[i][j]));
                    Edge v = DataMapper.get_edge(i,j);
                    v.line.setText(v.weight + "/" + Math.abs(f[i][j]));
                    v.line.repaint();
                }
            }
        }
    }

    @Override
    public void next_step() {
        next = false;
    }

    @Override
    public void prev_step() {

    }
}