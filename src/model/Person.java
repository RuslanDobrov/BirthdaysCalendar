package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {
    private String fio;
    private GregorianCalendar dateOfBirth;

    public Person(String fio, GregorianCalendar dateOfBirth) {
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return fio + ' ' + sdf.format(dateOfBirth.getTime());
    }
}
