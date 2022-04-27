import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainPanel extends JFrame {
    public ArrayList<Dot> dots = new ArrayList<>();
    public static MainPanel MainFrame;

    MainPanel(){
        super("Графы");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        MainFrame = this;
        addMouseListener(new CustomListener());
        setSize(600,600);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainPanel();
    }


    public class TempFrame extends JFrame {
        TempFrame(int x,int y){
            super("Введите значение точки");
            setSize(200,100);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            JTextField input = new JTextField(5);
            input.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Dot dot = new Dot(input.getText());
                    dots.add(dot);
                    dot.setBounds(x-30,y-50,43,43);
                    MainFrame.add(dot);
                    MainFrame.repaint();
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
