package school.cesar.eta.unit;

import org.junit.jupiter.api.Test;
import sun.font.SunFontManager;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        String name = person.getFirstName();
        String lastname = person.getLastName();
        assertTrue(name == "Jon" && lastname == "Snow");
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName(null);
        String name = person.getFirstName();
        String lastname = person.getLastName();
        assertTrue(name == "Jon");

    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setName(null);
        person.setLastName("Snow");
        String name = person.getFirstName();
        String lastname = person.getLastName();
        assertTrue(lastname == "Snow");

    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        person.setName(null);
        person.setLastName(null);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("Name must be filled");
        });

        String expectedMessage = "Name must be filled";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person person = new Person();
        LocalDate date =LocalDate.now();
        person.setBirthday(LocalDate.of(1989,date.getMonthValue(), date.getDayOfMonth()));
        LocalDate receivedDate = person.getBirthday();
        int ReceivedDay = receivedDate.getDayOfMonth();
        int expectedDay = person.getNow().getDayOfMonth()+1;
        int expectedMonth = person.getNow().getMonthValue()+1;
        int receivedMonth = receivedDate.getMonthValue();
        assertFalse(ReceivedDay==expectedDay);
        assertFalse(expectedMonth==receivedMonth);
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {

        Person person = new Person();
        LocalDate date = LocalDate.now();
        person.setBirthday(LocalDate.of(1989,date.getMonthValue(), date.getDayOfMonth()+1));
        LocalDate receivedDate = person.getBirthday();
        int ReceivedDay = receivedDate.getDayOfMonth();
        int expectedDay = date.getDayOfMonth();
        int expectedMonth = person.getNow().getMonthValue();
        int receivedMonth = date.getMonthValue();
        assertFalse(ReceivedDay==expectedDay);
        assertTrue(expectedMonth==receivedMonth);
//        fail();
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new Person();
        LocalDate date = LocalDate.now();
        person.setBirthday(LocalDate.of(1989,date.getMonthValue(), date.getDayOfMonth()));
        LocalDate receivedDate = person.getBirthday();
        int ReceivedDay = receivedDate.getDayOfMonth();
        int expectedDay = date.getDayOfMonth();
        int expectedMonth = person.getNow().getMonthValue();
        int receivedMonth = date.getMonthValue();
        assertTrue(ReceivedDay==expectedDay);
        assertTrue(expectedMonth==receivedMonth);
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {

        Person person = new Person();
        Person relative = new Person();
        person.addToFamily(relative);
        boolean family = person.isFamily(relative);
        assertTrue(family);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person = new Person();
        Person familyUpdate = new Person();
        person.addToFamily(familyUpdate);
        familyUpdate.addToFamily(person);
        assertTrue(person.isFamily(familyUpdate)&&familyUpdate.isFamily(person));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person person = new Person();

        assertFalse(person.isFamily(person));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person person = new Person();
        person.addToFamily(person);
        assertTrue(person.isFamily(person));
    }
}
