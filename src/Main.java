import javax.swing.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            View.changeFont("Verdana", 14);
            Person model = new Person();
            ViewMain view = new ViewMain();
            Controller controller = new Controller(model, view);
            view.setController(controller);
            Map<String, Calendar> infoFromFile = controller.readFromFile();
            controller.fillMonthToPeople(infoFromFile);
//        controller.getView().setMonthsJList(new JList<>(controller.updateMonth()));
            controller.getView().init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
