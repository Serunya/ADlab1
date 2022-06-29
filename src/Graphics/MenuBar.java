package Graphics;

import Data.InputFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MenuBar extends JMenuBar {
    private final String[][] fileMenu = {
            {"Файл"     ,  "Ф",  "", ""},
            {"Открыть"  ,  "О", "O", ""},
            {"Сохранить",  "С", "S", ""}
    };

    private JMenu createMenuItems(final String[][] items){
        JMenu menu = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть",new ImageIcon("images/open.png"));
        open.setMnemonic('O');
        open.setAccelerator(KeyStroke.getKeyStroke('O',KeyEvent.CTRL_MASK));
        JMenuItem save = new JMenuItem("Сохранить ");
        save.setMnemonic('S');
        save.setAccelerator(KeyStroke.getKeyStroke('S',KeyEvent.CTRL_MASK));
        JMenuItem saveHow = new JMenuItem("Сохранить как*");
        saveHow.setMnemonic('S');
        saveHow.setAccelerator(KeyStroke.getKeyStroke('S',KeyEvent.SHIFT_MASK));
        menu.add(open);
        menu.add(save);
        menu.add(saveHow);
        menu.addSeparator();
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputFile.ChooseFile();
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputFile.SaveFile();
            }
        });
        saveHow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputFile.SaveFileHow();
            }
        });
        return menu;
    }


    private JMenu createSubmenus()
    {
        JMenu text = new JMenu("Текст");
        // и несколько вложенных меню
        JMenu style = new JMenu("Стиль");
        JMenuItem bold = new JMenuItem("Жирный");
        JMenuItem italic = new JMenuItem("Курсив");
        JMenu font = new JMenu("Шрифт");
        JMenuItem arial = new JMenuItem("Arial");
        JMenuItem times = new JMenuItem("Times");
        font.add(arial); font.add(times);
        // размещаем все в нужном порядке
        style.add(bold);
        style.add(italic);
        style.addSeparator();
        style.add(font);
        text.add(style);
        return text;
    }
    public MenuBar(){
        add(createMenuItems(fileMenu));
        add(Box.createHorizontalGlue());
        repaint();
    }
}
