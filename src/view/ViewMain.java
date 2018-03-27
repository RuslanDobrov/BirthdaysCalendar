package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ViewMain extends JFrame {
    private Controller controller;
    private LinkedHashMap <String, ArrayList<String>> mapMonthandPeople = new LinkedHashMap<>();

    {
        mapMonthandPeople.put("Январь", new ArrayList<>());
        mapMonthandPeople.put("Февраль", new ArrayList<>());
        mapMonthandPeople.put("Март", new ArrayList<>());
        mapMonthandPeople.put("Апрель", new ArrayList<>());
        mapMonthandPeople.put("Май", new ArrayList<>());
        mapMonthandPeople.put("Июнь", new ArrayList<>());
        mapMonthandPeople.put("Июль", new ArrayList<>());
        mapMonthandPeople.put("Август", new ArrayList<>());
        mapMonthandPeople.put("Сентябрь", new ArrayList<>());
        mapMonthandPeople.put("Октябрь", new ArrayList<>());
        mapMonthandPeople.put("Ноябрь", new ArrayList<>());
        mapMonthandPeople.put("Декабрь", new ArrayList<>());
    }

    public ViewMain(Controller controller) {
        this.controller = controller;
        init();
    }

    /**
     * Создаем строку меню вверху
     * */
    private JMenuBar createMenuBar() {
        //  Шрифт для меню
        Font fontVerdana = new Font("Verdana", Font.PLAIN, 11);

        // панель меню
        JMenuBar menuBar = new JMenuBar();  // панель меню

        // пункты меню в панели
        JMenu menuFile = new JMenu("Файл"); // меню "Файл"
        JMenu menuEdit = new JMenu("Правка"); // меню "Правка"
        JMenu menuHelp = new JMenu("Помощь");   // меню "Помощь"

        // пункты в меню "Файл"
        JMenuItem menuImport = new JMenuItem("Импорт...");  // пукт меню "Импорт..."
        JMenuItem menuExport = new JMenuItem("Экспорт...");  // пукт меню "Экспрт..."
        JMenuItem menuExit = new JMenuItem("Выход");  // пукт меню "Экспрт..."

        // пункты в меню "Правка"
        JMenuItem menuBringIn = new JMenuItem("Внести");    // пукт меню "Внести"
        JMenuItem menuCorrection = new JMenuItem("Редактировать");    // пукт меню "Редактировать"

        // пункты в меню "Помощь"
        JMenuItem menuAboutProgram = new JMenuItem("О программе");  // пукт меню "О программе"

        //  применяем шрифт для всего меню
        menuFile.setFont(fontVerdana);
        menuEdit.setFont(fontVerdana);
        menuHelp.setFont(fontVerdana);
        menuImport.setFont(fontVerdana);
        menuExport.setFont(fontVerdana);
        menuExit.setFont(fontVerdana);
        menuBringIn.setFont(fontVerdana);
        menuCorrection.setFont(fontVerdana);
        menuAboutProgram.setFont(fontVerdana);

        // добавляем в меню "Файл" пункты меню: "Импорт...", "Экспорт...", "Выход"
        menuFile.add(menuImport);
        menuFile.add(menuExport);
        menuFile.addSeparator();    //вносим разделитель
        menuFile.add(menuExit);

        // добавляем в меню "Правка" пункты меню: "Внести", "Редактировать"
        menuEdit.add(menuBringIn);
        menuEdit.add(menuCorrection);

        // добавляем в меню "Помощь" пункт меню "О программе"
        menuHelp.add(menuAboutProgram);

        //  добавляем слушателя на пункт меню "Выход" меню "Файл" с помощью лямда функции
        menuExit.addActionListener(e -> System.exit(0));

        //  добавляем на панель следующие меню: "Файл", "Правка", "Помощь"
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);

        return menuBar;
    }

    /**
     * Шаблон для расположения элемента
    * */
    private JPanel createMonths() {
//        JPanel panelMonths = new JPanel();
//
//        return panelMonths;
//        return new GridBagConstraints(x, y, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0);
        return null;
    }

    /**
     * Инициировать окно
     * */
    private void init() {
        JFrame frame = new JFrame("Календарь дней рождений");
        frame.setJMenuBar(createMenuBar()); // назначаем фрейму верхнее меню
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
