package Algoritms;

import Graphics.Dot;
import Graphics.Edge;

import java.util.ArrayList;

import static java.lang.Math.min;

public class PreflowPushes implements Runnable{
    ArrayList<Dot> dots;
    ArrayList<Edge> edges;
    int[] h;
    int[][] f;
    int[] e;
    final int INF = Integer.MAX_VALUE;


    public PreflowPushes(ArrayList<Dot> dots, ArrayList<Edge> edges){
        this.dots = dots;
        this.edges = edges;
        h = new int[dots.size()];
        for(int i = 0;i < dots.size();i++){
            h[i] = 0;
        }
    }

    public int c(int a,int b){
        for(int i = 0; i < edges.size();i++){
            if(edges.get(i).root.data == a && edges.get(i).child.data == b){
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
    public void run() {
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
            else
                lift (i);
        }
        int flow = 0;
        for (int i=0; i<n; i++)
            if (c(0,i) != INF)
                flow += f[0][i];
        System.out.println("Максимальный поток:" + flow);
    }
}
