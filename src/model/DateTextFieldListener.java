package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateTextFieldListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFormattedTextField source = (JFormattedTextField) e.getSource();
        Object value = source.getValue();
        System.out.println("Class: " + value.getClass());
        System.out.println("Value: " + value);
    }
}
