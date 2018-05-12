package model;

import view.ViewEditPerson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveChangeButtonListener implements ActionListener {
    private ViewEditPerson viewEditPerson;
    private Model model = new Model();

    public SaveChangeButtonListener(ViewEditPerson viewEditPerson) {
        this.viewEditPerson = viewEditPerson;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = viewEditPerson.getPersonList().getSelectedValue();
        String newName = viewEditPerson.getNewName().getText();
        String newDate = viewEditPerson.getNewDate().getText();

        if (!selectedItem.equals(newName + " " + newDate)) {
            model.updateInfomation(selectedItem, newName + ":" + newDate);
        }
    }
}
