import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Person {
    private String name;
    private Calendar birthday;

    public Person() {
    }

    public Person(String name, Calendar birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        return sdf.format(birthday.getTime());
        return birthday;
    }

    public String getBirthdayInFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(birthday.getTime());
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String toStringForExport() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return name + ':' + sdf.format(birthday.getTime());
    }

    public String toStringPersonWithAge() {
        int age = Calendar.getInstance().get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        String postfix = Controller.getPostfixAge(age);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return name + ' ' + sdf.format(birthday.getTime()) + " - " + age + " " + postfix;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return name + ' ' + sdf.format(birthday.getTime());
    }
}
