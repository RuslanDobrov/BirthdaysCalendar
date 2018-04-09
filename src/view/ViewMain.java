package view;

import controller.Controller;
import model.MonthListener;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class ViewMain extends JFrame {
    private Controller controller;  // экземпляр контроллера
    private final String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};   //список месяцев
    // месяцы являются ArrayList для возможности добавления новых людей
    private ArrayList<Person> listJanuary = new ArrayList<>();
    private ArrayList<Person> listFebruary = new ArrayList<>();
    private ArrayList<Person> listMarch = new ArrayList<>();
    private ArrayList<Person> listApril = new ArrayList<>();
    private ArrayList<Person> listMay = new ArrayList<>();
    private ArrayList<Person> listJune = new ArrayList<>();
    private ArrayList<Person> listJuly = new ArrayList<>();
    private ArrayList<Person> listAugust = new ArrayList<>();
    private ArrayList<Person> listSeptember = new ArrayList<>();
    private ArrayList<Person> listOctober = new ArrayList<>();
    private ArrayList<Person> listNovember = new ArrayList<>();
    private ArrayList<Person> listDecember = new ArrayList<>();

    // список списков listMonthsAndPeople людей в каждом месяце
    private ArrayList<ArrayList<Person>> listMonthsAndPeople = new ArrayList<>();

    // заполняем listMonthsAndPeople списком людей
    {
        listMonthsAndPeople.add(listJanuary);
        listMonthsAndPeople.add(listFebruary);
        listMonthsAndPeople.add(listMarch);
        listMonthsAndPeople.add(listApril);
        listMonthsAndPeople.add(listMay);
        listMonthsAndPeople.add(listJune);
        listMonthsAndPeople.add(listJuly);
        listMonthsAndPeople.add(listAugust);
        listMonthsAndPeople.add(listSeptember);
        listMonthsAndPeople.add(listOctober);
        listMonthsAndPeople.add(listNovember);
        listMonthsAndPeople.add(listDecember);

        //  тестовая запись
        listJanuary.add(new Person("Иван", new GregorianCalendar(1975, Calendar.DECEMBER, 31)));
    }

    // поле для вывода информации о людях при выборе месяца
    private JTextArea peoplesTextArea;

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

        // вывод окна
        frame.add(createContent(), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
//      frame.setSize(500, 500);
        centerFrame(frame);
    }

    /**
     * Создаем основное содержимое окна
     * */
    private JPanel createContent () {
        JPanel contents = new JPanel(); // создание списка месяцов и peoplesTextArea для людей
        contents.setLayout(new GridBagLayout());    // назначем Layout для панели
        final JList<String> list = new JList<>(updateMonth(months, listMonthsAndPeople));   // создаем JList и заполняем его месяцами
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);   // размещаем месяца в два столбца
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        peoplesTextArea = new JTextArea(5, 20); // создание текстового поля для вывода информации о людях
        list.addListSelectionListener(new MonthListener()); // подключение слушателя
        // Подключение слушателя мыши
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if ( e.getClickCount() == 1 ) {
                    // Получение элемента
                    int selected = list.locationToIndex(e.getPoint());
                    int i = 0;
                    StringBuilder messageJTextArea = new StringBuilder();
                    while (i < listMonthsAndPeople.get(selected).size())
                        messageJTextArea.append(listMonthsAndPeople.get(selected).get(i++)).append("\n");
                    peoplesTextArea.setText(messageJTextArea.toString());
                }
            }
        });

        // размещение компонентов в интерфейсе
        contents.add(new JLabel("Выберите месяц:"), new GridBagConstraints(1,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        contents.add(new JScrollPane(list), new GridBagConstraints(1,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        contents.add(new JLabel("Содержимое разделов"), new GridBagConstraints(1,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        contents.add(new JScrollPane(peoplesTextArea), new GridBagConstraints(1,4,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

        return contents;
    }


    /**
     * Обновляем количество людей в списке месяцов
    * */
    private String[] updateMonth (String[] months, ArrayList<ArrayList<Person>> listMonthsAndPeople) {
        for (int i = 0; i < months.length; i++) {
            months[i] = "        " + months[i] + " - " + listMonthsAndPeople.get(i).size();
        }
        return months;
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
