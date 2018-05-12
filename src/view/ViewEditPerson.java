package view;

import model.Model;
import model.MonthListener;
import model.Person;
import model.SaveChangeButtonListener;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ViewEditPerson {
    private Model model = new Model();
    private ViewMain viewMain;
    private JList<String> personList = null;
    private JTextField newName = new JTextField( 15);
//    private JFormattedTextField newDate = new JFormattedTextField();
    private JFormattedTextField newDate = model.createDateField();
//    private JFormattedTextField newDate;
//
//    {
//        try {
//            MaskFormatter mf = new MaskFormatter("##.##.####");
//            newDate = new JFormattedTextField(mf);
//            mf.setPlaceholderCharacter('_');
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    private JButton saveChangeButton = new JButton("Сохранить изменения");

    public ViewEditPerson(ViewMain viewMain) {
        this.viewMain = viewMain;
    }

    public JList<String> getPersonList() {
        return personList;
    }

    public JTextField getNewName() {
        return newName;
    }

    public JFormattedTextField getNewDate() {
        return newDate;
    }

    /**
     * Инициировать окно
     * */
    public void init() {
        JFrame frame = new JFrame();
        frame.dispose();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // вывод окна
        frame.add(createContent(), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
        centerFrame(frame);
    }

    /**
     * Создаем основное содержимое окна
     * */
    private JPanel createContent () {
//        model.createDateField(newDate);
        JPanel contents = new JPanel();
        contents.setLayout(new GridBagLayout());

        getPersonList(viewMain.getMonthsJList());
        personList.setLayoutOrientation(JList.VERTICAL_WRAP);
        personList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        personList.addListSelectionListener(new MonthListener()); // подключение слушателя
//        // Подключение слушателя мыши
//        personList.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                if ( e.getClickCount() == 1 ) {
//                    ArrayList<ArrayList<Person>> listMonthsAndPeople = viewMain.getListMonthsAndPeople();
//                    ArrayList<Person> selectedPersonList = listMonthsAndPeople.get(viewMain.getMonthsJList().getSelectedIndex());
//                    newName.setText(selectedPersonList.get(personList.getSelectedIndex()).getFio());
//                    newDate.setValue("12.12.1202");
////                    newDate.setText(selectedPersonList.get(personList.getSelectedIndex()).getDateOfBirthString());
////                    newDate.setText(personList.getSelectedValue());
//                }
//            }
//        });

        contents.add(new JScrollPane(personList), new GridBagConstraints(0,0,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
        contents.add(newName, new GridBagConstraints(0,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(model.createDateField(newDate), new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
        contents.add(newDate, new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));

        GridBagConstraints constraintsButtonSave = new GridBagConstraints();
        constraintsButtonSave.gridwidth = 1;
        constraintsButtonSave.gridx = 0;
        constraintsButtonSave.gridy = 3;
        constraintsButtonSave.anchor = GridBagConstraints.CENTER;
        constraintsButtonSave.insets = new Insets(5,2,5,2);
        contents.add(saveChangeButton, constraintsButtonSave);

//        contents.add(saveChangeButton, new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(new JLabel("Ф.И.О:"), new GridBagConstraints(0,0,1,1,0.0,0.9,GridBagConstraints.EAST,GridBagConstraints.CENTER,new Insets(5,2,5,2),0,0));
//        contents.add(namePerson, new GridBagConstraints(1,0,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("Дата рождения:"), new GridBagConstraints(0,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(dateTextField, new GridBagConstraints(1,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("День"), new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(dd, new GridBagConstraints(0,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("Месяц"), new GridBagConstraints(1,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(mm, new GridBagConstraints(1,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("Год"), new GridBagConstraints(2,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(yyyy, new GridBagConstraints(2,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(buttonSave, new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(buttonClear, new GridBagConstraints(1,4,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));

//        GridBagConstraints constraintsButtonSave = new GridBagConstraints();
//        constraintsButtonSave.gridwidth = 2;
//        constraintsButtonSave.gridx = 0;
//        constraintsButtonSave.gridy = 2;
//        constraintsButtonSave.anchor = GridBagConstraints.CENTER;
//        constraintsButtonSave.insets = new Insets(5,2,5,2);
//        contents.add(buttonSave, constraintsButtonSave);

//        buttonSave.addActionListener(new SaveButtonListener(this));
//        getPersonList(viewMain.getMonthsJList());


        // Подключение слушателя мыши
        personList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if ( e.getClickCount() == 1 ) {
                    ArrayList<ArrayList<Person>> listMonthsAndPeople = viewMain.getListMonthsAndPeople();
                    ArrayList<Person> selectedPersonList = listMonthsAndPeople.get(viewMain.getMonthsJList().getSelectedIndex());
                    newName.setText(selectedPersonList.get(personList.getSelectedIndex()).getFio());
//                    newDate.setValue(new GregorianCalendar(1975, Calendar.DECEMBER, 31));



                    // Определение маски и поля ввода даты
//                    DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
// Форматирующий объект даты
//                    DateFormatter dateFormatter = new DateFormatter(date);
//                    dateFormatter.setAllowsInvalid(false);
//                    dateFormatter.setOverwriteMode(true);

// Создание форматированного текстового поля даты
//                    newDate = new JFormattedTextField();
//                    newDate.setColumns(32);
//                    System.out.println(newDate.getValue());
//                    newDate.setValue(new Date());
//                    try {
//                        newDate.commitEdit();
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }
//                    System.out.println(newDate.getValue());
//                    newDate.setText("12.12.2222");
//                    System.out.println(newDate.getValue());
//        textField.setValue(new Date());

//                    try {
//                        newDate.commitEdit();
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }
//                    try {
//                        newDate.getFormatter().stringToValue("12.12.1221");
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }

//                    newDate.setText(selectedPersonList.get(personList.getSelectedIndex()).getDateOfBirthString());
                    newDate.setValue(selectedPersonList.get(personList.getSelectedIndex()).getDateOfBirthString());

//                    newDate.setText(personList.getSelectedValue());
                }
            }
        });

        saveChangeButton.addActionListener(new SaveChangeButtonListener(this));
//        System.out.println("Отрисовка");
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

    private void getPersonList (JList monthsList) {
        ArrayList<ArrayList<Person>> listMonthsAndPeople = viewMain.getListMonthsAndPeople();
        ArrayList<Person> selectedPersonList = listMonthsAndPeople.get(monthsList.getSelectedIndex());
        String[] personToString = new String[selectedPersonList.size()];
        for (int i = 0; i < selectedPersonList.size(); i++) {
            personToString[i] = selectedPersonList.get(i).toString();
        }
        personList = new JList<>(personToString);
    }
//    public ViewEditPerson() {
//        String[] data = {"a","b","c","d","e","f","g"};
//        DefaultTableModel dm = new DefaultTableModel();
//        Vector dummyHeader = new Vector();
//        dummyHeader.addElement("");
//        dm.setDataVector(
//                strArray2Vector(data),
//                dummyHeader);
//        JTable table = new JTable( dm );
//        table.setShowGrid(false);
//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        JScrollPane scrollTable = new JScrollPane( table );
//        scrollTable.setColumnHeader(null);
//        scrollTable.setMinimumSize(new Dimension(100,80));
//        Box tableBox = new Box(BoxLayout.Y_AXIS);
//        tableBox.add(scrollTable);
//
//        Container c = getContentPane();
//        c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
//        c.add(new JSeparator(SwingConstants.VERTICAL));
//        c.add(tableBox);
//        setSize( 220, 130 );
//        setVisible(true);
//    }
//
//    private Vector strArray2Vector(String[] str) {
//        Vector vector = new Vector();
//        for (int i=0;i<str.length;i++) {
//            Vector v = new Vector();
//            v.addElement(str[i]);
//            vector.addElement(v);
//        }
//        return vector;
//    }
//
//    public static void main(String[] args) {
//        final ViewEditPerson frame = new ViewEditPerson();
//        frame.addWindowListener( new WindowAdapter() {
//            public void windowClosing( WindowEvent e ) {
//                System.exit(0);
//            }
//        });
//    }
}
