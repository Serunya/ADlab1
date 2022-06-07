package Algoritms;

import Data.DataMapper;
import Graphics.Dot;
import Graphics.Edge;

import java.awt.*;
import java.util.ArrayList;

public class SearchDeep2 extends Algoritm{
    private final int r = -1;
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
    public SearchDeep2(){
        super("Поиск в глубину 2");
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
    public void algoritm() {
        this.dots = DataMapper.getDots();
        this.edges = DataMapper.getEdges();
        matrix = get_matrix();
        num = new int[dots.size()];
        ftr = new int[dots.size()];
        tk = new int[dots.size()];
        for(int i = 0; i < matrix.length;i++){
            num[i] = 0;
            ftr[i] = 0;
            tk[i] = 0;
        }
        for(int i = 0;i < matrix.length;i++){
            c++;
            if(num[i] == 0) {
                try {
                    Dot dot = get_dot(r);
                    dot.setColor(Color.blue);
                    Thread.sleep(500);
                    SDK(r);
                    dot.setColor(Color.pink);
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
        for(int j:matrix[i]){
            if(num[j] == 0) {
                Dot dot = get_dot(j);
                dot.setColor(Color.blue);
                Thread.sleep(500);
                ftr[j] = i;
                UT.add(new int[]{i, j});
                SDK(j);
                dot.setColor(Color.pink);
                Thread.sleep(500);
            }
            else {
                if (num[j]<num[i] && ftr[i] != j)
                    UB.add(new int[]{i,j});
               /* удалить
               if(num[j] > num[i]){
                   UF.add(new int[]{i,j});
               }
               else{
                   if(tk[j] == 0)
                       UB.add(new int[]{i,j});
                   else
                       UC.add(new int[]{i,j});
               }
               */
            }
        }
        tk[i] = k;
    }
}
