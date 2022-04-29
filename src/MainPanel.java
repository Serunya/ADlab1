import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainPanel extends JPanel {
    public ArrayList<Dot> dots = new ArrayList<>();
    public static MainPanel MainFrame;
    public static CustomListener listener;

    public boolean is_create_dot(String data){
        for(int i = 0; i < dots.size();i++){
            if(dots.get(i).data.equals(data)){
                return true;
            }
        }
        return false;
    }

    public void search_deep(){
        String search_item = null;
        search_item = JOptionPane.showInputDialog("Введи значение искомого элемента");
        Thread t = new Thread(new SearchDeep(dots,Edge.edges,search_item));
        t.start();
    }


    MainPanel(){
        super();
        new Thread().start();
        setBackground(Color.WHITE);
        MainFrame = this;
        listener = new CustomListener();
        addMouseListener(listener);
        setSize(600,600);
        setLayout(null);
        setVisible(true);
    }

    public class TempFrame extends JFrame {
        TempFrame(int x,int y){
            super("Введите значение точки");
            setSize(200,100);
            JFrame context = (JFrame) this;
            JTextField input = new JTextField(5);
            input.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean is_create = is_create_dot(input.getText());
                    dispose();
                    if(!is_create) {
                        Dot dot = new Dot(input.getText());
                        dots.add(dot);
                        dot.setBounds(x - 20, y - 20, 43, 43);
                        MainFrame.add(dot);
                        MainFrame.repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(context, "Вершина уже создана");
                    }
                }
            });
            input.setBounds(25,25,100,25);
            add(input);
            setLayout(null);
            setVisible(true);
        }

    }


    public class CustomListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            new TempFrame(e.getX(),e.getY());
        }
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
