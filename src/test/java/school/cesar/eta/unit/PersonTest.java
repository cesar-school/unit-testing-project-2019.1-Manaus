package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        Assertions.assertEquals("Jon", person.getFirstName ());
        Assertions.assertEquals("Snow", person.getLastName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        Assertions.assertSame("Jon", person.getFirstName ());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");
        Assertions.assertSame("Snow", person.getLastName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        person.setName(null);
        person.setLastName(null);
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String errorMessage = "Name must be filled";

        assertNull(firstName, errorMessage);
        assertNull(lastName, errorMessage);
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person person = new Person();
        LocalDate date =LocalDate.now();
        person.setBirthday(LocalDate.of(date.getYear(),date.getMonthValue(), date.getDayOfMonth()));
        LocalDate birthdayDate = person.getBirthday();
        int today = birthdayDate.getDayOfMonth();
        int birthdayDay = person.getNow().getDayOfMonth()+1;
        int Month = birthdayDate.getMonthValue();
        int birthdayMonth = person.getNow().getMonthValue()+1;
        assertTrue(today!=birthdayDay);
        assertTrue(Month!=birthdayMonth);
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person person = new Person();
        LocalDate date =LocalDate.now();
        person.setBirthday(LocalDate.of(date.getYear(),date.getMonthValue(), date.getDayOfMonth()));
        LocalDate birthdayDate = person.getBirthday();
        int today = birthdayDate.getDayOfMonth();
        int birthdayDay = person.getNow().getDayOfMonth()+1;
        int Month = birthdayDate.getMonthValue();
        int birthdayMonth = person.getNow().getMonthValue();
        assertTrue(today!=birthdayDay);
        assertTrue(Month == birthdayMonth);
    }


    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new Person();
        LocalDate date =LocalDate.now();
        person.setBirthday(LocalDate.of(date.getYear(),date.getMonthValue(), date.getDayOfMonth()));
        LocalDate birthdayDate = person.getBirthday();
        int today = birthdayDate.getDayOfMonth();
        int birthdayDay = person.getNow().getDayOfMonth();
        int Month = birthdayDate.getMonthValue();
        int birthdayMonth = person.getNow().getMonthValue();
        assertTrue(today==birthdayDay);
        assertTrue(Month == birthdayMonth);
    }


    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person person = new Person();
        Person relativePerson = new Person();
        person.addToFamily(relativePerson);
        assertTrue(person.isFamily(relativePerson));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person = new Person();
        Person person2 = new Person();
        assertFalse(person.isFamily(person2));
        assertFalse(person2.isFamily(person));
        person.addToFamily(person2);
        Assertions.assertTrue(person.isFamily(person2));
        Assertions.assertTrue(person2.isFamily(person));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person relativePerson = new Person();
        Assertions.assertFalse(relativePerson.isFamily(relativePerson));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person relativePerson = new Person();
        relativePerson.addToFamily(relativePerson);
        Assertions.assertTrue(relativePerson.isFamily(relativePerson));
    }
}
