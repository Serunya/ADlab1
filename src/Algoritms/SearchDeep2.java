package Algoritms;

import Graphics.Dot;
import Graphics.Edge;

import java.awt.*;
import java.util.ArrayList;

public class SearchDeep2 implements Runnable{
    private final int r;
    private ArrayList<Dot> dots;
    private ArrayList<Edge> edges;

    //
    int[][] matrix;
    int[] num;
    int[] ftr;
    int[] tk;
    int k = 1;
    int c = 0;

    //
    ArrayList<int[]> UT = new ArrayList<>();
    ArrayList<int[]> UB = new ArrayList<>();
    ArrayList<int[]> UF = new ArrayList<>();
    ArrayList<int[]> UC = new ArrayList<>();
    //
    public SearchDeep2(ArrayList<Dot> dots, ArrayList<Edge> edges, int r){
        this.dots = dots;
        this.edges = edges;
        this.r = r;
        matrix = get_matrix();
        num = new int[dots.size()];
        ftr = new int[dots.size()];
        tk = new int[dots.size()];
    }

    public Dot get_dot(int data){
        for(int i = 0;i < dots.size();i++){
            if(dots.get(i).data == data){
                return dots.get(i);
            }
        }
        return null;
    }

    public int[][] get_matrix(){
        int[][] matrix = new int[dots.size()][];
        for(int i = 0; i < dots.size(); i++) {
            ArrayList<Integer> ik = new ArrayList<Integer>();
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).root.data == i) {
                    int jk = edges.get(j).child.data;
                    ik.add(jk);
                }
            }
            matrix[i] = new int[ik.size()];
            for (int j = 0; j < ik.size(); j++) {
                matrix[i][j] = ik.get(j);
            }
        }

        return matrix;
    }

    @Override
    public void run() {
        for(int i = 0; i < matrix.length;i++){
            num[i] = 0;
            ftr[i] = 0;
            tk[i] = 0;
        }
        for(int i = 0;i < matrix.length;i++){
            c++;
            if(num[i] == 0) {
                try {
                    SDK(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Кол-во шагов: " + c);

        System.out.println("--КЛАССИФИКАЦИЯ ДУГ--");
        System.out.println("___-Древесные дуги-___");
        for(int i = 0; i < UT.size();i++){
            System.out.println(UT.get(i)[0] + " " +UT.get(i)[1]);
        }
        System.out.println("___-Обратные дуги-___");
        for(int i = 0; i < UB.size();i++){
            System.out.println(UB.get(i)[0] + " " + UB.get(i)[1]);
        }
        System.out.println("___-Прямые-___");
        for(int i = 0; i < UF.size();i++){
            System.out.println(UF.get(i)[0] + " " + UF.get(i)[1]);
        }
        System.out.println("___-Перекрестные-___");
        for(int i = 0; i < UC.size();i++){
            System.out.println(UC.get(i)[0] + " " + UC.get(i)[1]);
        }
        System.out.println("______________________________");


    }


    public void SDK(int i) throws InterruptedException {
        c++;
        num[i] = k;
        k++;
        Dot dot = get_dot(i);
        dot.setColor(Color.blue);
        Thread.sleep(500);
        for(int j:matrix[i]){
            dot.setColor(Color.GREEN);
            if(num[j] == 0) {
                ftr[j] = i;
                UT.add(new int[]{i, j});
                SDK(j);
            }
            else {
               if(num[j] > num[i]){
                   UF.add(new int[]{i,j});
               }
               else{
                   if(tk[j] == 0)
                       UB.add(new int[]{i,j});
                   else
                       UC.add(new int[]{i,j});
               }
            }
        }
        dot = get_dot(i);
        dot.setColor(Color.pink);
        Thread.sleep(500);
        tk[i] = k;
    }
}
