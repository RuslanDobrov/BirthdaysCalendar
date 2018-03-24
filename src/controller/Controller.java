package controller;

import model.Model;
import view.ViewMain;

public class Controller {
    private ViewMain viewMain;
    private Model model;

    private Controller() {
        this.viewMain = new ViewMain(this);
        this.model = new Model();
}

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
