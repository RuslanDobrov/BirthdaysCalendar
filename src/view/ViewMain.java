package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewMain extends JFrame {
    private Controller controller;
    private final String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private ArrayList<String> listJanuary = new ArrayList<>();
    private ArrayList<String> listFebruary = new ArrayList<>();
    private ArrayList<String> listMarch = new ArrayList<>();
    private ArrayList<String> listApril = new ArrayList<>();
    private ArrayList<String> listMay = new ArrayList<>();
    private ArrayList<String> listJune = new ArrayList<>();
    private ArrayList<String> listJuly = new ArrayList<>();
    private ArrayList<String> listAugust = new ArrayList<>();
    private ArrayList<String> listSeptember = new ArrayList<>();
    private ArrayList<String> listOctober = new ArrayList<>();
    private ArrayList<String> listNovember = new ArrayList<>();
    private ArrayList<String> listDecember = new ArrayList<>();

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
