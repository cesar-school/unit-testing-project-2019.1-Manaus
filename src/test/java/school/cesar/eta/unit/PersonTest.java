package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    Person person = new Person();

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        person.setName("Jon");
        person.setLastName("Snow");

        String expected = person.getFirstName() + person.getLastName();

        Assertions.assertEquals(expected, person.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {

        person.setName("Jon");
        String expected = person.getFirstName();

        Assertions.assertEquals(expected, person.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {

        person.setLastName("Snow");
        String expected = person.getLastName();

        Assertions.assertEquals(expected, person.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class, () -> person.getName());
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        int todayMonth = getDifferentMonth(person.getNow().getMonthValue());
        int today = getDifferentDay(person.getNow().getDayOfMonth());

        LocalDate dateOfBirth = LocalDate.of(1988, todayMonth, today);

        person.setBirthday(dateOfBirth);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        int todayMonth = person.getNow().getMonthValue();
        int today = getDifferentDay(person.getNow().getDayOfMonth());

        LocalDate dateOfBirth = LocalDate.of(1988, todayMonth, today);
        person.setBirthday(dateOfBirth);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {

        int todayMonth = person.getNow().getMonthValue();
        int today = person.getNow().getDayOfMonth();

        LocalDate dateOfBirth = LocalDate.of(1988, todayMonth, today);
        person.setBirthday(dateOfBirth);
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person relative = new Person();

        Assertions.assertFalse(person.isFamily(relative));

        person.addToFamily(relative);

        Assertions.assertTrue(person.isFamily(relative));

    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {

        Person relative = new Person();
        Assertions.assertFalse(person.isFamily(relative));
        Assertions.assertFalse(relative.isFamily(person));

        person.addToFamily(relative);
        Assertions.assertTrue(person.isFamily(relative));
        Assertions.assertTrue(relative.isFamily(person));

    }

    @Test
    public void isFamily_nonRelativePerson_false() {

        Person relative = new Person();

        Assertions.assertFalse(person.isFamily(relative));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person relative = new Person();
        person.addToFamily(relative);

        Assertions.assertTrue(person.isFamily(relative));

    }

    private int getDifferentMonth(int month){

        if (month == 12){
            return month -= 1;
        }else{
            return month +=1;
        }
    }

    private int getDifferentDay(int day){
        if(day >= 29){
            return day = 28;
        }else {
            return day -= 1;
        }
    }
}
