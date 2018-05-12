package model;

import view.ViewAddPerson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ViewAddPerson viewAddPerson = new ViewAddPerson();
        viewAddPerson.init();
    }
}
