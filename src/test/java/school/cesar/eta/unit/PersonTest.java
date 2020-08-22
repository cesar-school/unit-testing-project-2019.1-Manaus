package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class PersonTest {

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        String expectedName = person.getFirstName() + person.getLastName();
        Assertions.assertEquals(expectedName, person.getName());

    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        String expectedName = person.getFirstName();
        Assertions.assertEquals(expectedName, person.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");
        String expectedName = person.getLastName();
        Assertions.assertEquals(expectedName, person.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        Assertions.assertThrows(RuntimeException.class, person::getName);

    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate today = LocalDate.now();
        Person person = new Person();
        int todayMonth = differentMonth(today.getMonthValue());
        int todayDay = differentDay(today.getDayOfMonth());
        LocalDate birthday = LocalDate.of(1993, todayMonth, todayDay);
        person.setBirthday(birthday);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person person = new Person();
        LocalDate today = LocalDate.now();
        int todayMonth = today.getMonthValue();
        int todayDay = differentDay(today.getDayOfMonth());
        LocalDate birthday = LocalDate.of(1993, todayMonth, todayDay);
        person.setBirthday(birthday);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new Person();
        LocalDate today = LocalDate.now();
        person.setBirthday(today);
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person person = new Person();
        Person newMember = new Person();
        Assertions.assertFalse(person.isFamily(newMember));
        person.addToFamily(newMember);
        Assertions.assertTrue(person.isFamily(newMember));
    }


    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person = new Person();
        Person newMember = new Person();
        Assertions.assertFalse(newMember.isFamily(person));
        newMember.addToFamily(person);
        Assertions.assertTrue(newMember.isFamily(person));
        }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person person = new Person();
        Person newMember = new Person();
        Assertions.assertFalse(person.isFamily(newMember));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person person = new Person();
        Person newMember = new Person();
        person.addToFamily(newMember);
        Assertions.assertTrue(person.isFamily(newMember));
    }

    //gerar dia e mes diferentes de hoje
    private int differentDay(int day){
        if (day >=28){
            day = day - 1;
        }else{
            day = day + 1;
        }
        return day;
    }

    private int differentMonth(int month){
        if (month == 12){
            month = month - 1;
        }else{
            month = month + 1;
        }
        return month;
    }
}
