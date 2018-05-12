package view;

import model.Model;
import model.SaveButtonListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.GregorianCalendar;

public class ViewAddPerson {
    private Model model = new Model();
    private JTextField namePerson = new JTextField(15);
//    private JTextField dd = new JTextField(2);
//    private JTextField mm = new JTextField(2);
//    private JTextField yyyy = new JTextField(4);
//    private GregorianCalendar date;
    private JFormattedTextField dateTextField = model.createDateField();
    private JButton buttonSave = new JButton("Сохранить");
//    private JButton buttonClear = new JButton("Очистить");

    public JFormattedTextField getDateTextField() {
        return dateTextField;
    }

    public JTextField getNamePerson() {
        return namePerson;
    }

    public ViewAddPerson() {
//        init();
    }

//    public JTextField getDateTextField() {
//        return dateTextField;
//    }
//
//    public void setDateTextField(JTextField dateTextField) {
//        this.dateTextField = dateTextField;
//    }

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
//        createDateField();

        JPanel contents = new JPanel();
        contents.setLayout(new GridBagLayout());

        contents.add(new JLabel("Ф.И.О:"), new GridBagConstraints(0,0,1,1,0.0,0.9,GridBagConstraints.EAST,GridBagConstraints.CENTER,new Insets(5,2,5,2),0,0));
        contents.add(namePerson, new GridBagConstraints(1,0,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

        contents.add(new JLabel("Дата рождения:"), new GridBagConstraints(0,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
        contents.add(dateTextField, new GridBagConstraints(1,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("День"), new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(dd, new GridBagConstraints(0,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("Месяц"), new GridBagConstraints(1,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(mm, new GridBagConstraints(1,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(new JLabel("Год"), new GridBagConstraints(2,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(yyyy, new GridBagConstraints(2,3,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

//        contents.add(buttonSave, new GridBagConstraints(0,2,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
//        contents.add(buttonClear, new GridBagConstraints(1,4,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));

        GridBagConstraints constraintsButtonSave = new GridBagConstraints();
        constraintsButtonSave.gridwidth = 2;
        constraintsButtonSave.gridx = 0;
        constraintsButtonSave.gridy = 2;
        constraintsButtonSave.anchor = GridBagConstraints.CENTER;
        constraintsButtonSave.insets = new Insets(5,2,5,2);
        contents.add(buttonSave, constraintsButtonSave);

        buttonSave.addActionListener(new SaveButtonListener(this));
//        buttonClear.addActionListener(new SaveChangeButtonListener(this));

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

//    /**
//     *  Сбросить значение поля dateTextField до вида __.__.____
//     * */
//    private void createDateField() {
//        MaskFormatter mf = null;
//        try {
//            mf = new MaskFormatter("##.##.####");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        mf.setPlaceholderCharacter('_');
//        dateTextField = new JFormattedTextField(mf);
//    }
}
