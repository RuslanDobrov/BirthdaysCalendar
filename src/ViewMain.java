import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Map;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class ViewMain implements View {
    JMenuBar menuBar = new JMenuBar();
    // пункты меню в панели
    JMenu menuFile = new JMenu("Файл"); // меню "Файл"
    JMenu menuEdit = new JMenu("Правка"); // меню "Правка"
    JMenu menuHelp = new JMenu("Помощь");   // меню "Помощь"

    // пункты в меню "Файл"
    JMenuItem menuImport = new JMenuItem("Импорт...", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/import.png"))));  // пукт меню "Импорт..."
    JMenuItem menuExport = new JMenuItem("Экспорт...", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/export.png"))));  // пукт меню "Экспрт..."
    JMenuItem menuPrint = new JMenuItem("Печать", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/print.png"))));  // пукт меню "Экспрт..."
    JMenuItem menuExit = new JMenuItem("Выход", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/exit.png"))));  // пукт меню "Экспрт..."

    // пункты в меню "Правка"
    JMenuItem menuCorrection = new JMenuItem("Редактировать", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/edit.png"))));    // пукт меню "Редактировать"
    JMenuItem menuAddition = new JMenuItem("Добавить", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/addition.png"))));    // пукт меню "Внести"
    JMenuItem menuDelete = new JMenuItem("Удалить", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/delete.png"))));    // пукт меню "Внести"

    // пункты в меню "Помощь"
    JMenuItem menuAboutProgram = new JMenuItem("О программе", new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/about.png"))));  // пукт меню "О программе"
    // поле для вывода информации о людях при выборе месяца
    private JFrame frame = new JFrame("Календарь дней рождений");
    private JPanel contents = new JPanel(); // создание списка месяцов и peoplesTextArea для людей
//    private JTextArea peoplesTextArea;
    private DefaultListModel listModel = new DefaultListModel();
    private JList<String> peopleJList = new JList<>(listModel);
    private JButton editButton = new JButton("Редактировать");  // кнопка "Редактировать"
    private JButton addButton = new JButton("Добавить");    // кнопка "Внести"
    private JButton removeButton = new JButton("Удалить");    // кнопка "Внести"
    private Controller controller;
    private boolean isAdded = false;
    private boolean isEdit = false;
    private DefaultListModel monthModel = new DefaultListModel();
    private JList<String> monthsJList = new JList<>(monthModel);
//    private int selectedMonth = viewMain.getMonthsJList().getAnchorSelectionIndex();

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public void setController (Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public JList<String> getPeopleJList() {
        return peopleJList;
    }

    public DefaultListModel getMonthModel() {
        return monthModel;
    }

    public JList<String> getMonthsJList() {
        return monthsJList;
    }

    public void setMonthsJList(JList<String> monthsJList) {
        this.monthsJList = monthsJList;
    }

    /**
     * Создаем строку меню вверху
     * */
    private JMenuBar createMenuBar() {
        //  Шрифт для меню
//        Font fontVerdana = new Font("Verdana", Font.PLAIN, 11);

        // панель меню
//        JMenuBar menuBar = new JMenuBar();  // панель меню

//        // пункты меню в панели
//        JMenu menuFile = new JMenu("Файл"); // меню "Файл"
//        JMenu menuEdit = new JMenu("Правка"); // меню "Правка"
//        JMenu menuHelp = new JMenu("Помощь");   // меню "Помощь"
//
//        // пункты в меню "Файл"
//        JMenuItem menuImport = new JMenuItem("Импорт...");  // пукт меню "Импорт..."
//        JMenuItem menuExport = new JMenuItem("Экспорт...");  // пукт меню "Экспрт..."
//        JMenuItem menuExit = new JMenuItem("Выход");  // пукт меню "Экспрт..."
//
//        // пункты в меню "Правка"
//        JMenuItem menuCorrection = new JMenuItem("Редактировать");    // пукт меню "Редактировать"
//        JMenuItem menuAddition = new JMenuItem("Добавить");    // пукт меню "Внести"
//        JMenuItem menuDelete = new JMenuItem("Удалить");    // пукт меню "Внести"
//
//        // пункты в меню "Помощь"
//        JMenuItem menuAboutProgram = new JMenuItem("О программе");  // пукт меню "О программе"

        //  применяем шрифт для всего меню
//        menuFile.setFont(fontVerdana);
//        menuEdit.setFont(fontVerdana);
//        menuHelp.setFont(fontVerdana);
//        menuImport.setFont(fontVerdana);
//        menuExport.setFont(fontVerdana);
//        menuExit.setFont(fontVerdana);
//        menuCorrection.setFont(fontVerdana);
//        menuAddition.setFont(fontVerdana);
//        menuDelete.setFont(fontVerdana);
//        menuAboutProgram.setFont(fontVerdana);

        // добавляем в меню "Файл" пункты меню: "Импорт...", "Экспорт...", "Выход"
        menuFile.add(menuImport);
        menuFile.add(menuExport);
        menuFile.add(menuPrint);
        menuFile.addSeparator();    //вносим разделитель
        menuFile.add(menuExit);

        // добавляем в меню "Правка" пункты меню: "Внести", "Редактировать"
        menuEdit.add(menuCorrection);
        menuEdit.add(menuAddition);
        menuEdit.add(menuDelete);

        // добавляем в меню "Помощь" пункт меню "О программе"
        menuHelp.add(menuAboutProgram);

        //  добавляем слушателя на пункт меню "Выход" меню "Файл" с помощью лямда функции
        menuExit.addActionListener(e -> System.exit(0));
        menuCorrection.addActionListener(e -> editButton.doClick());
        menuAddition.addActionListener(e -> addButton.doClick());
        menuDelete.addActionListener(e -> removeButton.doClick());

        menuAboutProgram.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Версия 1.0.0.1\nСоздал Добров Руслан Валериевич в 2018 году\nСвязаться со мной: RuslanDobrov.RD@gmail.com",
                "О программе",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/about_big.png")))));
        menuImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.importData();
                monthModel.clear();
                String [] months = controller.updateMonth();
                for (int i = 0; i < months.length; i++) {
                    monthModel.addElement(months[i]);
                }

                listModel.clear();
            }
        });
        menuExport.addActionListener(e -> controller.exportData());
        menuPrint.addActionListener(e -> controller.printToPrinter());

        //  добавляем на панель следующие меню: "Файл", "Правка", "Помощь"
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);

        return menuBar;
    }

    /**
     * Инициировать окно
     * */
    public void init() {
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/icon.png"));
        frame.setIconImage(image);

//        contents.setBackground(new Color(35, 53, 65));
//        menuBar.setBackground(new Color(11, 30, 42));
//        menuBar.setOpaque(true);
//
//        editButton.setBackground(new Color(11, 30, 42));
//        editButton.setContentAreaFilled(false);
//        editButton.setOpaque(true);
//
//        addButton.setBackground(new Color(11, 30, 42));
//        addButton.setContentAreaFilled(false);
//        addButton.setOpaque(true);
//
//        removeButton.setBackground(new Color(11, 30, 42));
//        removeButton.setContentAreaFilled(false);
//        removeButton.setOpaque(true);
//
//        peopleJList.setBackground(new Color(119, 144, 160));
//        monthsJList.setBackground(new Color(119, 144, 160));

        frame.setJMenuBar(createMenuBar()); // назначаем фрейму верхнее меню
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // вывод окна
        frame.add(createContent(), BorderLayout.CENTER);
        frame.setVisible(true);
//        frame.setSize(600, 321);
        frame.pack();
        frame.setResizable(false);
//        System.out.println("Ширина - " + frame.getWidth() + " высота - " + frame.getHeight() + " список - " + peopleJList.getWidth());
        centerFrame(frame);
    }

    /**
     * Создаем основное содержимое окна
     * */
    public JPanel createContent() {
        contents.setLayout(new GridBagLayout());    // назначем Layout для панели
//        model.fillMonthToPeople(model.readFromFile(), getListMonthsAndPeople());
//        monthsJList = new JList<>(model.updateMonth(MONTHS, listMonthsAndPeople));   // создаем JList и заполняем его месяцами
        monthsJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);   // размещаем месяца в два столбца
        monthsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        peopleJList.setLayoutOrientation(JList.VERTICAL_WRAP);   // размещаем месяца в два столбца
//        peopleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        peopleJList.setPrototypeCellValue("01234567890123456789012345678901234567890123456789");
//        peopleJList.setVisibleRowCount(0);
//        peoplesTextArea = new JTextArea(6, 17); // создание текстового поля для вывода информации о людях
//        monthsJList.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                // выделенная строка
//                ((JList<?>)e.getSource()).getSelectedIndex();
//            }
//        }); // подключение слушателя
//        // Подключение слушателя мыши
        monthsJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if ( e.getClickCount() == 1 ) {
                    // Получение элемента
                    int selected = monthsJList.locationToIndex(e.getPoint());
                    int i = 0;
                    StringBuilder message = new StringBuilder();
                    if (listModel.size() > 0) {
                        listModel.clear();
                    }
                    while (i < Controller.persons.get(selected).size()) {
//                        message.append(Controller.persons.get(selected).get(i++)).append("\n");
                        message.append(Controller.persons.get(selected).get(i++).toStringPersonWithAge()).append("\n");
                        listModel.addElement(message.toString());
                        message.setLength(0);
                    }
                }
            }
        });

        peopleJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting() && peopleJList.getSelectedIndex() != -1) {
//                    System.out.println(peopleJList.getSelectedIndex());
                    editButton.setEnabled(true);
                    removeButton.setEnabled(true);
                    menuCorrection.setEnabled(true);
                    menuDelete.setEnabled(true);
                } else {
                    editButton.setEnabled(false);
                    removeButton.setEnabled(false);
                    menuCorrection.setEnabled(false);
                    menuDelete.setEnabled(false);
                }
            }
        });

        String [] months = controller.updateMonth();
        for (int i = 0; i < months.length; i++) {
            monthModel.addElement(months[i]);
        }

        // убрать горизонтальный скролл в списке людей
        JScrollPane scrollPanePeopleJList = new JScrollPane(peopleJList);
        scrollPanePeopleJList.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        // размещение компонентов в интерфейсе
        contents.add(new JLabel("Выберите месяц:"), new GridBagConstraints(0,0,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
        contents.add(new JScrollPane(monthsJList), new GridBagConstraints(0,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        contents.add(new JLabel("Содержимое месяца:"), new GridBagConstraints(1,0,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
        contents.add(scrollPanePeopleJList, new GridBagConstraints(1,1,1,4,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.VERTICAL,new Insets(2,2,2,2),0,0));
//        contents.add(new JScrollPane(peoplesTextArea), new GridBagConstraints(1,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

        contents.add(editButton, new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,3,2),0,0));
        contents.add(addButton, new GridBagConstraints(0,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(3,2,3,2),0,0));
        contents.add(removeButton, new GridBagConstraints(0,4,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(3,2,10,2),0,0));

//        addButton.addActionListener(new AddButtonListener());
//        editButton.addActionListener(new EditButtonListener(this));

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Да", "Нет"};
//                JOptionPane p = new JOptionPane();
                int n = JOptionPane.showOptionDialog(null, "Вы действительно хотите удалить значение?","Подтверждение", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/about_32x32.png"))), options, options[0]);
                if (n == 0) {
                    controller.removeFromFile(listModel.get(peopleJList.getSelectedIndex()).toString());
//                System.out.println(listModel.get(peopleJList.getSelectedIndex()).toString());
                    listModel.remove(peopleJList.getSelectedIndex());

                    monthModel.clear();
                    String [] months = controller.updateMonth();
                    for (int i = 0; i < months.length; i++) {
                        monthModel.addElement(months[i]);
                    }
                }
//                if (n == 1) { //<----------а тут должно закрываться диалоговое окно
//                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//                    SwingUtilities.windowForComponent(p).dispose();
//                }




//                        frame.invalidate();
//        frame.revalidate();
//        frame.validate();
//        frame.repaint();
//        frame.update(contents.getGraphics());
//
//        contents.invalidate();
//        contents.revalidate();
//        contents.validate();
//        contents.repaint();
//        contents.update(contents.getGraphics());
//
//        monthsJList.invalidate();
//        monthsJList.revalidate();
//        monthsJList.validate();
//        monthsJList.repaint();
//        monthsJList.update(contents.getGraphics());
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAdded = true;
                ViewAdd viewAdd = new ViewAdd(ViewMain.this);

                viewAdd.getFrame().setTitle("Добавить");

                viewAdd.init();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEdit = true;
                ViewAdd viewAdd = new ViewAdd(ViewMain.this);

                viewAdd.getFrame().setTitle("Редактировать");

                viewAdd.init();
                Person person = controller.getPersonFromString(peopleJList.getSelectedValue());
                viewAdd.getNamePerson().setText(person.getName());
                viewAdd.getDateTextField().setText(person.getBirthdayInFormat());
            }
        });

        editButton.setEnabled(false);
        removeButton.setEnabled(false);
        menuCorrection.setEnabled(false);
        menuDelete.setEnabled(false);

        return contents;
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

    public void update() {
//        frame.invalidate();
//        frame.revalidate();
//        frame.validate();
//        frame.repaint();
//        frame.update(contents.getGraphics());
//
//        contents.invalidate();
//        contents.revalidate();
//        contents.validate();
//        contents.repaint();
//        contents.update(contents.getGraphics());
//
//        monthsJList.invalidate();
//        monthsJList.revalidate();
//        monthsJList.validate();
//        monthsJList.repaint();
//        monthsJList.update(contents.getGraphics());
//
//        monthsJList.ensureIndexIsVisible(2);
    }
}
