package model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MonthListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        // выделенная строка
        ((JList<?>)e.getSource()).getSelectedIndex();
//        int selected = ((JList<?>)e.getSource()).
//                getSelectedIndex();
//        System.out.println ("Выделенная строка : " +
//                String.valueOf(selected));
    }
}
