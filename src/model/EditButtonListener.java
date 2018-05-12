package model;

import view.ViewAddPerson;
import view.ViewEditPerson;
import view.ViewMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonListener implements ActionListener {
    private ViewMain viewMain;

    public EditButtonListener(ViewMain viewMain) {
        this.viewMain = viewMain;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewEditPerson viewEditPerson = new ViewEditPerson(viewMain);
        viewEditPerson.init();
    }
}
