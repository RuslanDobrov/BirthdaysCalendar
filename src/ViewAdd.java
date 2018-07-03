import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ViewAdd extends ViewMain implements View {
    private ViewMain viewMain;
    private JTextField namePerson = new JTextField(15);
    private JFormattedTextField dateTextField = View.createDateField();
    private JButton buttonSave = new JButton("Сохранить");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//    private String addedString;
    private JFrame frame = new JFrame();
    public JFormattedTextField getDateTextField() {
        return dateTextField;
    }
    public JTextField getNamePerson() {
        return namePerson;
    }

    public ViewAdd(ViewMain viewMain) {
        this.viewMain = viewMain;
    }

//    public String getAddedString() {
//        return addedString;
//    }


    public JFrame getFrame() {
        return frame;
    }

    private void doWithWindowClose() {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                viewMain.setAdded(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    @Override
    public void init() {
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("resources/icon.png"));
        frame.setIconImage(image);
        frame.dispose();
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        doWithWindowClose();

        // вывод окна
        frame.add(createContent(), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
        View.centerFrame(frame);
    }

    @Override
    public JPanel createContent() {
        JPanel contents = new JPanel();
        contents.setLayout(new GridBagLayout());

        contents.add(new JLabel("Ф.И.О:"), new GridBagConstraints(0,0,1,1,0.0,0.9,GridBagConstraints.EAST,GridBagConstraints.CENTER,new Insets(5,2,5,2),0,0));
        contents.add(namePerson, new GridBagConstraints(1,0,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

        contents.add(new JLabel("Дата рождения:"), new GridBagConstraints(0,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(5,2,5,2),0,0));
        contents.add(dateTextField, new GridBagConstraints(1,1,1,1,0.0,0.9,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

        GridBagConstraints constraintsButtonSave = new GridBagConstraints();
        constraintsButtonSave.gridwidth = 2;
        constraintsButtonSave.gridx = 0;
        constraintsButtonSave.gridy = 2;
        constraintsButtonSave.anchor = GridBagConstraints.CENTER;
        constraintsButtonSave.insets = new Insets(5,2,5,2);
        contents.add(buttonSave, constraintsButtonSave);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewMain.isAdded()) {
                    String name = namePerson.getText();
                    Calendar date = null;
                    try {
                        date = getDateOfBirthday();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,
                                "Дата введена некорректно.",
                                "Внимание",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    if (name != null && name.length() > 0) {
                        viewMain.getController().writeToFile(name + ":" + viewMain.getController().convertDateToFormatString(date));
                        Person addedPerson = new Person(name, date);
//                        Controller.getPersons().get(date.get(Calendar.MONTH)).add(new Person(name, date));
                        Controller.getPersons().get(date.get(Calendar.MONTH)).add(addedPerson);
//                        addedString = name + " " + sdf.format(date.getTime());
//                        addedString = name + " " + sdf.format(date.getTime()) + " - " + ;
//                        System.out.println(date.get(Calendar.MONTH));
//                        System.out.println(viewMain.getMonthsJList().getSelectedIndex());

                        int selectedMonth = viewMain.getMonthsJList().getSelectedIndex();

                        if (date.get(Calendar.MONTH) == selectedMonth) {
                            viewMain.getListModel().addElement(addedPerson.toStringPersonWithAge());
                            int index = viewMain.getListModel().size() - 1;
                            viewMain.getPeopleJList().setSelectedIndex(index);
                            viewMain.getPeopleJList().ensureIndexIsVisible(index);
                        }



                        viewMain.getMonthModel().clear();
                        String [] months = viewMain.getController().updateMonth();
                        for (int i = 0; i < months.length; i++) {
                            viewMain.getMonthModel().addElement(months[i]);
                        }

                        viewMain.getMonthsJList().setSelectedIndex(selectedMonth);


                        namePerson.setText("");
                        dateTextField.setText("");


                        JOptionPane.showMessageDialog(null,
                                "Запись успешно внесена.",
                                "",
                                JOptionPane.PLAIN_MESSAGE);

//                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Имя не введено или введено некорретно.",
                                "Внимание",
                                JOptionPane.WARNING_MESSAGE);
                    }
//                    viewMain.setAdded(false);
                }
                if (viewMain.isEdit()) {
                    String name = namePerson.getText();
                    Calendar date = getDateOfBirthday();
                    viewMain.getController().updatePerson(viewMain.getPeopleJList().getSelectedValue(), new Person(name, date));

//                    viewMain.getListModel().set(viewMain.getPeopleJList().getSelectedIndex(), new Person(name, date).toString());
                    viewMain.getListModel().set(viewMain.getPeopleJList().getSelectedIndex(), new Person(name, date).toStringPersonWithAge());

                    viewMain.setEdit(false);
                    frame.dispose();
                }
            }
        });

        return contents;
    }

    /**
     * Конвертировать строку в Calendar
     * */
    public Calendar convertStringToCalendar(String text) {
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = fmt.parse(text);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Получаем дату из поля dateTextField класса ViewAddPerson
     * */
    private Calendar getDateOfBirthday() {
        return convertStringToCalendar(dateTextField.getText());
    }
}
