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
        Assertions.assertEquals("Jon",person.getFirstName());
        Assertions.assertEquals("Snow",person.getLastName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        Assertions.assertEquals("Jon",person.getFirstName());
        Assertions.assertNull(person.getLastName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");
        Assertions.assertNull(person.getFirstName());
        Assertions.assertEquals("Snow",person.getLastName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        String msgExpected = "Name must be filled";

        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> person.getName());
        String msgActual = exception.getMessage();
        Assertions.assertEquals(msgExpected,msgActual);
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person person = new Person();
        LocalDate date =LocalDate.now();
        person.setBirthday(LocalDate.of(1987,date.getMonthValue(), date.getDayOfMonth()));
        LocalDate birthday = person.getBirthday();
        int ReceivedDay = birthday.getDayOfMonth();
        int expectedDay = person.getNow().getDayOfMonth()+1;
        int expectedMonth = person.getNow().getMonthValue()+1;
        int receivedMonth = birthday.getMonthValue();
        Assertions.assertFalse(ReceivedDay==expectedDay);
        Assertions.assertFalse(expectedMonth==receivedMonth);

    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        LocalDate today = LocalDate.now();
        Person person = new Person();
        int differentDay = today.getDayOfMonth()+1;
        LocalDate dateExpec = LocalDate.of(today.getYear(), today.getMonth(), differentDay);
        person.setBirthday(dateExpec);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new Person();
        LocalDate birthday =  LocalDate.now();
        person.setBirthday(birthday);
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person person = new Person();
        Person newMember = new Person();
        person.addToFamily(newMember);
        boolean family = person.isFamily(newMember);
        Assertions.assertTrue(family);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person = new Person();
        Person familyUpdate = new Person();
        person.addToFamily(familyUpdate);
        familyUpdate.addToFamily(person);
        boolean isFamily = person.isFamily(familyUpdate)&&familyUpdate.isFamily(person);
        Assertions.assertTrue(isFamily);

    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person person = new Person();
        boolean isFamily = person.isFamily(person);
        Assertions.assertFalse(isFamily);
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person person = new Person();
        person.addToFamily(person);
        boolean isFamily = person.isFamily(person);
        Assertions.assertTrue(isFamily);
    }
}
