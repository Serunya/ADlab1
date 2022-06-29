package Data;

import Graphics.Dot;
import Graphics.Edge;
import Graphics.GraphPanel;
import Graphics.MainMenu;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFile {

    static File file;

    public static void ChooseFile() {
        JFileChooser exploer = new JFileChooser();
        exploer.setDialogTitle("Выбор файла");
        exploer.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int ret = exploer.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = exploer.getSelectedFile();
        }
        try {
            GraphPanel.MainFrame.removeAll();
            DataMapper.edges.clear();
            DataMapper.dots.clear();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] edges = line.split(" ");
                Dot dot = new Dot(DataMapper.dots.size());
                DataMapper.dots.add(dot);
                dot.setBounds(Integer.parseInt(edges[edges.length-2]), Integer.parseInt(edges[edges.length-1]), 43, 43);
                GraphPanel.MainFrame.add(dot);
                GraphPanel.MainFrame.repaint();
            }
            scanner = new Scanner(file);
            ArrayList<Dot> dots = DataMapper.getDots();
            int count = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] edges = line.split(" ");
                Dot root = dots.get(count);
                for(int i = 0; i < edges.length - 2 ;i+= 2){
                    Dot child = dots.get(Integer.parseInt(edges[i]));
                    if(DataMapper.edges.contains(DataMapper.get_edge(root.data,child.data))){
                        continue;
                    }
                    Edge edge = new Edge(root,child, Integer.parseInt(edges[i+1]));
                    child.edges_child.add(edge);
                    root.edges_root.add(edge);
                    GraphPanel.MainFrame.add(edge.getLine());
                    edge.getLine().setBounds(0, 0, 600, 600);
                }
                count++;
            }
            GraphPanel.MainFrame.repaint();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(MainMenu.context,"Файл не выбран!","Ошибка",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void SaveFile(){
        try {
            if (file == null) {
                JFileChooser exploer = new JFileChooser();
                exploer.setDialogTitle("Сохранить файл");
                exploer.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                int ret = exploer.showDialog(null, "Сохранить файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = exploer.getSelectedFile();
                }
            }
            StringBuilder file_string = new StringBuilder();
            for (Dot dot : DataMapper.getDots()) {
                ArrayList<Dot> dot_edges = DataMapper.get_child_dot(dot);
                for (Dot child : dot_edges) {
                    file_string.append(child.data).append(" ").append(DataMapper.get_edge(dot.data,child.data).weight).append(" ");
                }
                file_string.append(dot.getX()).append(" ").append(dot.getY()).append("\n");
            }
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(file_string.toString());
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }catch (NullPointerException a) {
            file = null;
        }
    }

    public static void SaveFileHow(){
        file = null;
        SaveFile();
    }

}
