package school.cesar.eta.unit;
import java.time.LocalDate;
import java.util.List;

public class PersonStub extends Person {

    public boolean isBirthdayTodayStub(LocalDate rightnow) {
        //LocalDate now = rigth;
        return rightnow.getDayOfMonth() == getBirthday().getDayOfMonth()
                && rightnow.getMonth() == getBirthday().getMonth();
    }

    public List<Person> GetFamily () {
        return family;

    }







}
