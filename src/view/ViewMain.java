package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ViewMain extends JFrame {
    private Controller controller;

    public ViewMain(Controller controller) {
        this.controller = controller;
    }

    /**
     * Инициируем окно, заполняем значениями из monthOfPeople
     * */
    public void init() {
        JFrame frame = new JFrame("Календарь дней рождений");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.setVisible(true);
        frame.setSize(500, 500);
        centerFrame(frame);
    }

    /**
     * Централизировать окно при запуске
     * */
    private static void centerFrame(Window frame) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        if (x < 0) {
            x = 0;
        }
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        if (y < 0) {
            y = 0;
        }
        frame.setBounds(x, y, frame.getWidth(), frame.getHeight());
    }
}
