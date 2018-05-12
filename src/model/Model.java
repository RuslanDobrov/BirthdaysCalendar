package model;

import controller.Controller;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model {
//    private static final String pathToFile = System.getProperty("user.home") + "\\JavaBirthdayCalendar\\BirthdaysCalendar.txt";
    private static final String pathToFile = "D:\\Ruslan\\test\\1.txt";

    /**
     * Записываем строку text в файл
     * */
    public void writeToFile(String text) {
        Path path = Paths.get(pathToFile);
        // действия, если папка существует
        if (Files.exists(path)) {
            try
            {
                // открываем поток для записи
                BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile, true));
                // пишем данные
                bw.write(text + System.lineSeparator());
                // закрываем поток
                bw.close();
            } catch (IOException e)
            {
                System.out.println("Ошибка записи файла.");
            }
            // если папка не существует, создаем его и вызываем метод еще раз
        } else {
            File file = new File(pathToFile);
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла.");
            }
            writeToFile(text);
        }
    }

    /**
     * Читаем строки из файла
     * */
    public Map<String, Calendar> readFromFile() {
        Map<String, Calendar> mapNameAndBirthday = new TreeMap<>();
        // проверяем наличие файла для чтения
        Path path = Paths.get(pathToFile);
        // если файл существует, то читаем его
        if (Files.exists(path)) {
            try {
                FileInputStream fstream = new FileInputStream(pathToFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                while ((strLine = br.readLine()) != null){
                    mapNameAndBirthday.putAll(convertStingToMap(strLine));
                }
            } catch (IOException e){
                System.out.println("Ошибка чтения файла.");
            }
        }

        for (Map.Entry<String, Calendar> pair : mapNameAndBirthday.entrySet())
        {
            String key = pair.getKey();                      //ключ
            Calendar value = pair.getValue();                  //значение
            System.out.println(key + ":" + convertDateToFormatString(value));
        }

        return mapNameAndBirthday;
    }

    /**
     * Конвертировать строку из файла в Map<String, Calendar>
     * */
    public Map<String, Calendar> convertStingToMap (String text) {
        Map<String, Calendar> mapNameAndBirthday = new TreeMap<>();
        String[] nameAndBirthday = text.split(":");
        mapNameAndBirthday.put(nameAndBirthday[0], convertStringToCalendar(nameAndBirthday[1]));
        return mapNameAndBirthday;
    }

    /**
     * Конвертировать строку в Calendar
     * */
    public Calendar convertStringToCalendar(String text) {
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = fmt.parse(text);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Получить дату в виде dd.MM.yyyy
     * */
    public String convertDateToFormatString (Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date.getTime());
    }

    /**
     * Заполняем месяцы данными из файла, если он есть
     **/
    public void fillMonthToPeople(Map<String, Calendar> mapNameDate, ArrayList<ArrayList<Person>> listMonths) {
        for (Map.Entry<String, Calendar> pair : mapNameDate.entrySet()) {
            int day = pair.getValue().get(Calendar.DATE);
            int month = pair.getValue().get(Calendar.MONTH);
            int year = pair.getValue().get(Calendar.YEAR);
            String name = pair.getKey();
            listMonths.get(month).add(new Person(name, new GregorianCalendar(year, month, day)));
        }
    }

    /**
     * Обновляем количество людей в списке месяцов
     * */
    public String[] updateMonth (String[] months, ArrayList<ArrayList<Person>> listMonthsAndPeople) {
        String [] updateMonths = new String[12];
        StringBuilder monthInformation = new StringBuilder();
        for (int i = 0; i < months.length; i++) {
            monthInformation.append("  ").append(months[i]).append(" - ").append(listMonthsAndPeople.get(i).size()).append("  ");
            updateMonths[i] = monthInformation.toString();
            monthInformation.setLength(0);
        }
        return updateMonths;
    }

    /**
     * Заменяем строку в файле
     * */
    public void updateInfomation(String source, String replace) {
        List<String> fileContent = null;
            try {
                fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).replace(":"," ").equals(source)) {
                        fileContent.set(i, replace);
                        break;
                    }
                }

                Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println("Ошибка при записи нового значения в файл.");
            }
    }

    /**
     *  Сбросить значение поля dateTextField до вида __.__.____
     * */
//    public JFormattedTextField createDateField(JFormattedTextField textField) {
//        MaskFormatter mf = null;
//        try {
//            mf = new MaskFormatter("##.##.####");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        mf.setPlaceholderCharacter('_');
//        textField = new JFormattedTextField(mf);
//        return textField;
//    }

    public JFormattedTextField createDateField () {
        MaskFormatter mf = null;
        try {
            mf = new MaskFormatter("##.##.####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf.setPlaceholderCharacter('_');
        JFormattedTextField textField = new JFormattedTextField(mf);
        return textField;
    }

//    public JFormattedTextField setDate(JFormattedTextField textField, GregorianCalendar date) {
//        textField.setValue(date);
//        return textField;
//    }
}
