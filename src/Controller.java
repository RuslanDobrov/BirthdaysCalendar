
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {
    private Person model;
    private ViewMain view;
    public static volatile Map<Integer, LinkedList<Person>> persons = new LinkedHashMap<>();
    private static final String pathToFile = "D:\\Ruslan\\test\\1.txt";

    public Controller(Person model, ViewMain view) {
        this.model = model;
        this.view = view;
        fillPersonsMap(persons);
    }

    public Person getModel() {
        return model;
    }

    public ViewMain getView() {
        return view;
    }

    public static Map<Integer, LinkedList<Person>> getPersons() {
        return persons;
    }

    private void fillPersonsMap(Map<Integer, LinkedList<Person>> persons) {
        persons.put(Calendar.JANUARY, new LinkedList<Person>());
        persons.put(Calendar.FEBRUARY, new LinkedList<Person>());
        persons.put(Calendar.MARCH, new LinkedList<Person>());
        persons.put(Calendar.APRIL, new LinkedList<Person>());
        persons.put(Calendar.MAY, new LinkedList<Person>());
        persons.put(Calendar.JUNE, new LinkedList<Person>());
        persons.put(Calendar.JULY, new LinkedList<Person>());
        persons.put(Calendar.AUGUST, new LinkedList<Person>());
        persons.put(Calendar.SEPTEMBER, new LinkedList<Person>());
        persons.put(Calendar.OCTOBER, new LinkedList<Person>());
        persons.put(Calendar.NOVEMBER, new LinkedList<Person>());
        persons.put(Calendar.DECEMBER, new LinkedList<Person>());
    }

    /**
     * Обновляем количество людей в списке месяцов
     * */
    public String[] updateMonth () {
        String [] result = new String[12];
        StringBuilder info = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        String[] monthNames = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };

        for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
            calendar.set(Calendar.MONTH,pair.getKey());
            result[pair.getKey()] = info.append(monthNames[calendar.get(Calendar.MONTH)]).append(" - ").append(pair.getValue().size()).toString();
            info.setLength(0);

//            /**
//             * тестовая запись
//             * */
//            pair.getValue().add(new Person("Vasya" + monthNames[pair.getKey()], Calendar.getInstance()));
//            pair.getValue().add(new Person("Petya" + monthNames[pair.getKey()], Calendar.getInstance()));
        }

        return result;
    }

    /**
     * Удаляем строку из файла
     * */
    public void removeFromFile (String line) {
        List<String> fileContent = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
//                System.out.println(fileContent.get(i).replace(":", " ") + " - " + line);
                if (fileContent.get(i).replace(":"," ").trim().equals(line.trim())) {
                    System.out.println("удалено");
                    String[] personNameAndDate = fileContent.get(i).split(":");
                    String name = personNameAndDate[0];
                    Calendar date = Calendar.getInstance();
                    date.setTime(sdf.parse(personNameAndDate[1]));
                    for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
                        if (pair.getKey() == date.get(Calendar.MONTH)) {
                            for (int j = 0; j < pair.getValue().size(); j++) {
                                if (pair.getValue().get(j).getName().equals(name)) {
                                    pair.getValue().remove();
                                }
                            }
                        }
//                        persons.get(date.get(Calendar.MONTH)).remove();
                    }
                    fileContent.remove(i);
//                    updateMonth();
//                    view.update();
//                    view.setMonthsJList(new JList<>(updateMonth()));
//                    fileContent.set(i, "");
                    break;
                }
            }

            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Ошибка при удалении значения в файле.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обновляем информацию
     * */
    public void updatePerson (String line, Person person) {
        List<String> fileContent = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
//                System.out.println(fileContent.get(i).replace(":", " ") + " - " + line);
                if (fileContent.get(i).replace(":"," ").trim().equals(line.trim())) {
                    System.out.println("заменено");
                    String[] personNameAndDate = fileContent.get(i).split(":");
                    String name = personNameAndDate[0];
                    Calendar date = Calendar.getInstance();
                    date.setTime(sdf.parse(personNameAndDate[1]));
                    for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
                        if (pair.getKey() == date.get(Calendar.MONTH)) {
                            for (int j = 0; j < pair.getValue().size(); j++) {
                                if (pair.getValue().get(j).getName().equals(name)) {
                                    pair.getValue().remove();
                                    pair.getValue().add(person);
                                }
                            }
                        }
//                        persons.get(date.get(Calendar.MONTH)).remove();
                    }
                    fileContent.remove(i);
                    fileContent.add(person.getName() + ":" + convertDateToFormatString(person.getBirthday()));
//                    updateMonth();
//                    view.update();
//                    view.setMonthsJList(new JList<>(updateMonth()));
//                    fileContent.set(i, "");
                    break;
                }
            }

            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Ошибка при удалении значения в файле.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

/**
 * Поиск нужного Person по строке
 * */
    public Person getPersonFromString (String line) {
        String[] tmp = line.split(" - ");
        line = tmp[0];


        Person person = null;
        List<String> fileContent = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).replace(":"," ").trim().equals(line.trim())) {
                    String[] personNameAndDate = fileContent.get(i).split(":");
                    String name = personNameAndDate[0];
                    Calendar date = Calendar.getInstance();
                    date.setTime(sdf.parse(personNameAndDate[1]));
                    for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
                        if (pair.getKey() == date.get(Calendar.MONTH)) {
                            for (int j = 0; j < pair.getValue().size(); j++) {
                                if (pair.getValue().get(j).getName().equals(name)) {
                                    person = pair.getValue().get(j);
                                    System.out.println("нашел");
                                }
                            }
                        }
                    }
                }
            }

            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Ошибка при поиске в файле.");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void exportData () {
        JFileChooser fc = new JFileChooser();
        StringBuilder info = new StringBuilder();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileStream = new FileOutputStream(fc.getSelectedFile());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileStream, "utf-8");
                for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        info.append(pair.getValue().get(i).toStringForExport());
                        info.append(System.getProperty("line.separator"));
                    }
                }
                outputStreamWriter.write(info.toString());
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            catch (Exception ex) {
                System.out.println("Что-то пошло не так...");
            }
        }
    }


    /**
     * Метод для печати
     * Формируем строку и печатаем с помощью класса OutputPrinter
     * */
    public void printToPrinter()
    {
        StringBuilder printData = new StringBuilder();
        for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
            for (int i = 0; i < pair.getValue().size(); i++) {
                String name = pair.getValue().get(i).getName();
                String date = pair.getValue().get(i).getBirthdayInFormat();
                int age = Calendar.getInstance().get(Calendar.YEAR) - pair.getValue().get(i).getBirthday().get(Calendar.YEAR);
                String postfix = getPostfixAge(age);
                printData.append(name).append(" ").append(date).append(" - ").append(age).append(postfix).append(System.getProperty("line.separator"));
            }
        }

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new OutputPrinter(printData.toString()));
        boolean doPrint = job.printDialog();
        if (doPrint)
        {
            try
            {
                job.print();
            }
            catch (PrinterException e)
            {
                // Print job did not complete.
            }
        }
    }

    /**
    * Определяем постфикса к возрасту
    * */
    public static String getPostfixAge (int age) {
        int ageLastNumber = age % 10;
        boolean exclusion = (age % 100 >= 11) && (age % 100 <= 14);
        String old = "";

        if (ageLastNumber == 1)
            old = "год";
        else if(ageLastNumber == 0 || ageLastNumber >= 5 && ageLastNumber <= 9)
            old = "лет";
        else if(ageLastNumber >= 2 && ageLastNumber <= 4)
            old = "года";
        if (exclusion)
            old = "лет";

        return old;
    }



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
    public void fillMonthToPeople(Map<String, Calendar> mapNameDate) {
        for (Map.Entry<String, Calendar> pair : mapNameDate.entrySet()) {
            int day = pair.getValue().get(Calendar.DATE);
            int month = pair.getValue().get(Calendar.MONTH);
            int year = pair.getValue().get(Calendar.YEAR);
            String name = pair.getKey();
            persons.get(month).add(new Person(name, new GregorianCalendar(year, month, day)));
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
}
