package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {
    private String fio;
    private Calendar dateOfBirth;

    Person(String fio, GregorianCalendar dateOfBirth) {
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

    public String getDateOfBirthString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(dateOfBirth.getTime());
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
