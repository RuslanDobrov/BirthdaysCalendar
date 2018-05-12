package controller;

import model.Model;
import model.Person;
import view.ViewMain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Controller {
    private ViewMain viewMain;
    private Model model;

    private Controller() {
        this.model = new Model();
        this.viewMain = new ViewMain(this, model);
}

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.model.fillMonthToPeople(controller.model.readFromFile(), controller.viewMain.getListMonthsAndPeople());
//        controller.model.updateMonth(controller.viewMain.getMonths(), controller.viewMain.getListMonthsAndPeople());
        controller.viewMain.init();
//        Model model = new Model();
//        model.writeToFile("Иванов Иван Иванович2:02.03.1990");
//        model.writeToFile("Петров Петр Петрович2:03.04.1995");
//        model.readFromFile();
    }
}
