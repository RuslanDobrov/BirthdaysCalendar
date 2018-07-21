
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
    private String pathToFile = "D:\\Ruslan\\test\\1.txt";
    private static final String mainPath = "D:\\Ruslan\\test\\1.txt";

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
        String[] tmp = line.split(" - ");
        line = tmp[0];

        List<String> fileContent = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
//            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
//            Files.lines(Paths.get(pathToFile), Charset.forName("windows-1251"));
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), Charset.forName("Windows-1251")));
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

//            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
            Files.write(Paths.get(pathToFile), fileContent, Charset.forName("Windows-1251"));
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
        String[] tmp = line.split(" - ");
        line = tmp[0];

        List<String> fileContent = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
//            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), Charset.forName("Windows-1251")));
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

//            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
            Files.write(Paths.get(pathToFile), fileContent, Charset.forName("Windows-1251"));
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
//            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), Charset.forName("Windows-1251")));
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

//            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
            Files.write(Paths.get(pathToFile), fileContent, Charset.forName("Windows-1251"));
        } catch (IOException e) {
            System.out.println("Ошибка при поиске в файле.");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void exportData () {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Экспорт");
        setUpdateUI(fc);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        StringBuilder info = new StringBuilder();
        if (fc.showSaveDialog(view.getFrame()) == JFileChooser.APPROVE_OPTION) {
            try {
                File exportFile = new File(fc.getSelectedFile().getAbsolutePath(), "calendar.txt");
                FileOutputStream fileStream = new FileOutputStream(exportFile);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileStream, "utf-8");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileStream, Charset.forName("Windows-1251"));
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


//        JFileChooser fc = new JFileChooser();
////        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        StringBuilder info = new StringBuilder();
//        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//            try {
//                FileOutputStream fileStream = new FileOutputStream(fc.getSelectedFile());
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileStream, "utf-8");
//                for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
//                    for (int i = 0; i < pair.getValue().size(); i++) {
//                        info.append(pair.getValue().get(i).toStringForExport());
//                        info.append(System.getProperty("line.separator"));
//                    }
//                }
//                outputStreamWriter.write(info.toString());
//                outputStreamWriter.flush();
//                outputStreamWriter.close();
//            }
//            catch (Exception ex) {
//                System.out.println("Что-то пошло не так...");
//            }
//        }
    }

    public void importData () {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Импорт");
        setUpdateUI(fc);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fc.showOpenDialog(view.getFrame()) == JFileChooser.APPROVE_OPTION) {

            PrintWriter pw = null;
            try {
                pw = new PrintWriter(pathToFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pw.close();
            for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
               pair.getValue().clear();
            }

            Map<String, Calendar> infoFromFile = readFromFile(fc.getSelectedFile().getAbsolutePath());
            fillMonthToPeople(infoFromFile);
            for (Map.Entry<String, Calendar> pair : infoFromFile.entrySet()) {
                writeToFile(pair.getKey() + ":" + convertDateToFormatString(pair.getValue()));
            }

        }
        updateMonth();

//        JFileChooser fc = new JFileChooser();
//        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this directory: " +
//                    fc.getSelectedFile().getAbsolutePath());
//        }


//        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//            try {
//                FileOutputStream fileStream = new FileOutputStream(fc.getSelectedFile());
//                ObjectOutputStream os = new ObjectOutputStream(fileStream);
//                os.writeObject(что хотите сохранить);
//            }
//            catch (Exception e) {
//                System.out.println("Что-то пошло не так...");
//            }
//        }
    }

    private JFileChooser setUpdateUI(JFileChooser choose) {
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.lookInLabelText", "Смотреть в");
        UIManager.put("FileChooser.fileNameLabelText", "Имя файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Тип файла");

        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.saveButtonToolTipText", "Сохранить");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.openButtonToolTipText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Отмена");

        UIManager.put("FileChooser.lookInLabelText", "Папка");
        UIManager.put("FileChooser.saveInLabelText", "Папка");
//        UIManager.put("FileChooser.fileNameLabelText", "Имя файла2");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Тип файлов");

        UIManager.put("FileChooser.upFolderToolTipText", "На один уровень вверх");
        UIManager.put("FileChooser.newFolderToolTipText", "Создание новой папки");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Список");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Таблица");
        UIManager.put("FileChooser.fileNameHeaderText", "Имя");
        UIManager.put("FileChooser.fileSizeHeaderText", "Размер");
        UIManager.put("FileChooser.fileTypeHeaderText", "Тип");
        UIManager.put("FileChooser.fileDateHeaderText", "Изменен");
        UIManager.put("FileChooser.fileAttrHeaderText", "Атрибуты");

        UIManager.put("FileChooser.acceptAllFileFilterText", "Все файлы");



//        UIManager.put("FileChooser.foldersLabelText", "1");

//        UIManager.put("FileChooser.acceptAllFileFilterText","1");
//        UIManager.put("FileChooser.cancelButtonText","2");
//        UIManager.put("FileChooser.cancelButtonToolTipText","3");
//        UIManager.put("FileChooser.deleteFileButtonMnemonic","4");
//        UIManager.put("FileChooser.deleteFileButtonText","5");
//        UIManager.put("FileChooser.deleteFileButtonToolTipText","6");
//        UIManager.put("FileChooser.detailsViewButtonAccessibleName","7");
//        UIManager.put("FileChooser.detailsViewButtonToolTipText","8");
//        UIManager.put("FileChooser.directoryDescriptionText","9");
//        UIManager.put("FileChooser.directoryOpenButtonText","10");
//        UIManager.put("FileChooser.directoryOpenButtonToolTipText","11");
//        UIManager.put("FileChooser.enterFilenameLabelText","12");
//        UIManager.put("FileChooser.fileDescriptionText","13");
//        UIManager.put("FileChooser.fileNameLabelText","14");
//        UIManager.put("FileChooser.filesLabelText","15");
//        UIManager.put("FileChooser.filesOfTypeLabelText","16");
//        UIManager.put("FileChooser.filterLabelText)","17");
//        UIManager.put("FileChooser.foldersLabelText","18");
//        UIManager.put("FileChooser.helpButtonText","19");
//        UIManager.put("FileChooser.helpButtonToolTipText","20");
//        UIManager.put("FileChooser.homeFolderAccessibleName","21");
//        UIManager.put("FileChooser.homeFolderToolTipText","22");
//        UIManager.put("FileChooser.listViewBackground","23");
//        UIManager.put("FileChooser.listViewButtonAccessibleName","24");
//        UIManager.put("FileChooser.listViewButtonToolTipText","25");
//        UIManager.put("FileChooser.lookInLabelText","26");
//        UIManager.put("FileChooser.newFolderAccessibleName","27");
//        UIManager.put("FileChooser.newFolderButtonText","28");
//        UIManager.put("FileChooser.newFolderButtonToolTipText","29");
//        UIManager.put("FileChooser.newFolderDialogText","30");
//        UIManager.put("FileChooser.newFolderErrorSeparator","31");
//        UIManager.put("FileChooser.newFolderErrorText","32");
//        UIManager.put("FileChooser.newFolderToolTipText","33");
//        UIManager.put("FileChooser.openButtonText","34");
//        UIManager.put("FileChooser.openButtonToolTipText","35");
//        UIManager.put("FileChooser.openDialogTitleText","36");
//        UIManager.put("FileChooser.other.newFolder","37");
//        UIManager.put("FileChooser.other.newFolder.subsequent","38");
//        UIManager.put("FileChooser.win32.newFolder","38");
//        UIManager.put("FileChooser.win32.newFolder.subsequent","39");
//        UIManager.put("FileChooser.pathLabelText","40");
//        UIManager.put("FileChooser.renameFileButtonText","41");
//        UIManager.put("FileChooser.renameFileButtonToolTipText","42");
//        UIManager.put("FileChooser.renameFileDialogText","43");
//        UIManager.put("FileChooser.renameFileErrorText","44");
//        UIManager.put("FileChooser.renameFileErrorTitle","45");
//        UIManager.put("FileChooser.saveButtonText","46");
//        UIManager.put("FileChooser.saveButtonToolTipText","47");
//        UIManager.put("FileChooser.saveDialogTitleText","48");
//        UIManager.put("FileChooser.saveInLabelText","49");
//        UIManager.put("FileChooser.updateButtonText","50");
//        UIManager.put("FileChooser.updateButtonToolTipText","51");
//        UIManager.put("FileChooser.upFolderAccessibleName","52");
//        UIManager.put("FileChooser.upFolderToolTipText","53");



//        UIManager.put("FileChooser.acceptAllFileFilterText","Все файлы");
//        UIManager.put("FileChooser.cancelButtonText","Отмена");
//        UIManager.put("FileChooser.cancelButtonToolTipText","Отмена");
//        UIManager.put("FileChooser.deleteFileButtonText","Удалить");
//        UIManager.put("FileChooser.deleteFileButtonToolTipText","Удалить файл");
//        UIManager.put("FileChooser.detailsViewButtonAccessibleName","Подробно");
//        UIManager.put("FileChooser.detailsViewButtonToolTipText","Подробно");
//        UIManager.put("FileChooser.directoryDescriptionText","Папка");
//        UIManager.put("FileChooser.directoryOpenButtonText","Открыть");
//        UIManager.put("FileChooser.directoryOpenButtonToolTipText","Открыть");
//        UIManager.put("FileChooser.enterFilenameLabelText","Имя");
//        UIManager.put("FileChooser.fileDescriptionText","Описание");
//        UIManager.put("FileChooser.fileNameLabelText","Имя файла");
//        UIManager.put("FileChooser.filesLabelText","Файлы");
//        UIManager.put("FileChooser.filesOfTypeLabelText","Типы файлов");
//        UIManager.put("FileChooser.filterLabelText","Тип(ы) файла");
//        UIManager.put("FileChooser.foldersLabelText","Папка");
//        UIManager.put("FileChooser.helpButtonText","Помощь");
//        UIManager.put("FileChooser.helpButtonToolTipText","Помощь");
//        UIManager.put("FileChooser.homeFolderAccessibleName","Дом");
//        UIManager.put("FileChooser.homeFolderToolTipText","Дом");
//        UIManager.put("FileChooser.listViewButtonAccessibleName","Список");
//        UIManager.put("FileChooser.listViewButtonToolTipText","Список");
//        UIManager.put("FileChooser.lookInLabelText","Католог:");
//        UIManager.put("FileChooser.newFolderAccessibleName","Создать папку");
//        UIManager.put("FileChooser.newFolderButtonText","Создать папку");
//        UIManager.put("FileChooser.newFolderButtonToolTipText","Создать папку");
//        UIManager.put("FileChooser.newFolderDialogText","Создать папку");
//        UIManager.put("FileChooser.newFolderErrorSeparator","Ошибка создания");
//        UIManager.put("FileChooser.newFolderErrorText","Ошибка создания");
//        UIManager.put("FileChooser.newFolderToolTipText","Создать папку");
//        UIManager.put("FileChooser.openButtonText","Открыть");
//        UIManager.put("FileChooser.openButtonToolTipText","Открыть");
//        UIManager.put("FileChooser.openDialogTitleText","Открыть");
//        UIManager.put("FileChooser.other.newFolder","Создать папку");
//        UIManager.put("FileChooser.other.newFolder.subsequent","Создать папку");
//        UIManager.put("FileChooser.win32.newFolder","Создать папку");
//        UIManager.put("FileChooser.win32.newFolder.subsequent","Создать папку");
//        UIManager.put("FileChooser.pathLabelText","Путь");
//        UIManager.put("FileChooser.renameFileButtonText","Переименовать");
//        UIManager.put("FileChooser.renameFileButtonToolTipText","Переименовать");
//        UIManager.put("FileChooser.renameFileDialogText","Переименовать");
//        UIManager.put("FileChooser.renameFileErrorText","Ошибка переименования");
//        UIManager.put("FileChooser.renameFileErrorTitle","Ошибка переименования");
//        UIManager.put("FileChooser.saveButtonText","Сохранить");
//        UIManager.put("FileChooser.saveButtonToolTipText","Сохранить");
//        UIManager.put("FileChooser.saveDialogTitleText","Сохранить");
//        UIManager.put("FileChooser.saveInLabelText","Католог:");
//        UIManager.put("FileChooser.updateButtonText","Обновить");
//        UIManager.put("FileChooser.updateButtonToolTipText","Обновить");
//        UIManager.put("FileChooser.upFolderAccessibleName","Вверх");
//        UIManager.put("FileChooser.upFolderToolTipText","Вверх");



        choose.updateUI();

        return choose;
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
                printData.append(name).append(" ").append(date).append(" - ").append(age).append(" ").append(postfix).append(System.getProperty("line.separator"));
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
//                BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile, true), StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToFile, true), "Windows-1251"));
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
    public Map<String, Calendar> readFromFile(String ... paths) {
        if (paths.length > 0) {
            pathToFile = paths[0];
        }

        Map<String, Calendar> mapNameAndBirthday = new TreeMap<>();
        // проверяем наличие файла для чтения
        Path path = Paths.get(pathToFile);
        // если файл существует, то читаем его
        if (Files.exists(path)) {
            try {
                FileInputStream fstream = new FileInputStream(pathToFile);
//                BufferedReader br = new BufferedReader(new InputStreamReader(fstream, StandardCharsets.UTF_8));
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "Windows-1251"));
                String strLine;
                while ((strLine = br.readLine()) != null){
                    if (!strLine.isEmpty() && strLine.contains(":")) {
                        mapNameAndBirthday.putAll(convertStingToMap(strLine));
                    }
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

        pathToFile = mainPath;

        return mapNameAndBirthday;
    }

    /**
     * Конвертировать строку из файла в Map<String, Calendar>
     * */
    public Map<String, Calendar> convertStingToMap (String text) {
        Map<String, Calendar> mapNameAndBirthday = new TreeMap<>();
        String[] nameAndBirthday = text.split(":");

//            String str = new String(nameAndBirthday[0].getBytes());
//            mapNameAndBirthday.put(str, convertStringToCalendar(nameAndBirthday[1]));

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
     * Поиск
     * */
    public List<String> doSearch (String searchSting) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, LinkedList<Person>> pair : persons.entrySet()) {
            for (int i = 0; i < pair.getValue().size(); i++) {
                if (pair.getValue().get(i).getName().toLowerCase().startsWith(searchSting.toLowerCase().trim())) {
                    String name = pair.getValue().get(i).getName();
                    String date = pair.getValue().get(i).getBirthdayInFormat();
                    int age = Calendar.getInstance().get(Calendar.YEAR) - pair.getValue().get(i).getBirthday().get(Calendar.YEAR);
                    String postfix = getPostfixAge(age);
                    sb.append(name).append(" ").append(date).append(" - ").append(age).append(" ").append(postfix);
                    result.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        return result;
    }
//
//    /**
//     * Обновляем количество людей в списке месяцов
//     * */
//    public String[] updateMonth (String[] months, ArrayList<ArrayList<Person>> listMonthsAndPeople) {
//        String [] updateMonths = new String[12];
//        StringBuilder monthInformation = new StringBuilder();
//        for (int i = 0; i < months.length; i++) {
//            monthInformation.append("  ").append(months[i]).append(" - ").append(listMonthsAndPeople.get(i).size()).append("  ");
//            updateMonths[i] = monthInformation.toString();
//            monthInformation.setLength(0);
//        }
//        return updateMonths;
//    }
//
//    /**
//     * Заменяем строку в файле
//     * */
//    public void updateInfomation(String source, String replace) {
//        List<String> fileContent = null;
//        try {
//            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8));
//            for (int i = 0; i < fileContent.size(); i++) {
//                if (fileContent.get(i).replace(":"," ").equals(source)) {
//                    fileContent.set(i, replace);
//                    break;
//                }
//            }
//
//            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            System.out.println("Ошибка при записи нового значения в файл.");
//        }
//    }
//
//    public JFormattedTextField createDateField () {
//        MaskFormatter mf = null;
//        try {
//            mf = new MaskFormatter("##.##.####");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        mf.setPlaceholderCharacter('_');
//        JFormattedTextField textField = new JFormattedTextField(mf);
//        return textField;
//    }
}
