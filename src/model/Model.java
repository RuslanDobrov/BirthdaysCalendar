package model;

import javax.swing.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Model {
    /**
    * Читаем файл с ФИО и датами и заносим его в Map<JLabel, JList>
    * */
    public Map<JLabel, JList> createMapForFile (String txtFile) {
        Map<JLabel, JList> monthAndPeople = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(txtFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return monthAndPeople;
    }
}
