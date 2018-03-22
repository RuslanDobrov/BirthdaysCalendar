package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ViewMain extends JFrame {
    private Controller controller;

    private JLabel infoBirthday = new JLabel("Ближайший день рождения у:", JLabel.CENTER);
    private JList soonBirthday = new JList();

    public ViewMain(Controller controller) {
        this.controller = controller;
    }

    /**
     * Инициируем окно, заполняем значениями из monthOfPeople
     * */
    public void init() {
        String title = "Календарь дней рождений";
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel(new BorderLayout());
        JPanel panelCenter = new JPanel(new GridLayout(4, 3));
        JPanel panelSouth = new JPanel(new GridLayout(2, 1));

        JLabel textField = new JLabel(title, JLabel.CENTER);

//        for (Map.Entry<String, String[]> jLabelJListEntry : monthOfPeople.entrySet()) {
//            JPanel panelMonth = new JPanel(new GridLayout(2, 1));
//            panelMonth.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
//            panelMonth.add(new JLabel(jLabelJListEntry.getKey()), JLabel.CENTER);
//            panelMonth.add(new JList<String>(jLabelJListEntry.getValue()));
//            panelCenter.add(panelMonth);
//        }

        panelNorth.add(textField, BorderLayout.CENTER);

        panelSouth.add(infoBirthday, BorderLayout.CENTER);
        panelSouth.add(soonBirthday, BorderLayout.CENTER);
        panelSouth.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);

        frame.setVisible(true);

        //делаем окно
        frame.setSize(500, 500);
//        frame.pack();
//        frame.setResizable(false);
        //центрируем окно
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
