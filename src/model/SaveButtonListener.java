package model;

import view.ViewAddPerson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class SaveButtonListener implements ActionListener {
    private ViewAddPerson viewAddPerson;
    private Model model = new Model();

    public SaveButtonListener(ViewAddPerson viewAddPerson) {
        this.viewAddPerson = viewAddPerson;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = getName();
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
            model.writeToFile(name + ":" + model.convertDateToFormatString(date));
            JOptionPane.showMessageDialog(null,
                    "Запись успешно внесена.",
                    "",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Имя не введено или введено некорретно.",
                    "Внимание",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Получаем дату из поля dateTextField класса ViewAddPerson
     * */
    private Calendar getDateOfBirthday() {
        return model.convertStringToCalendar(viewAddPerson.getDateTextField().getText());
    }

    /**
     * Получаем имя из поля namePerson класса ViewAddPerson
     * */
    private String getName () {
        return viewAddPerson.getNamePerson().getText();
    }
}
