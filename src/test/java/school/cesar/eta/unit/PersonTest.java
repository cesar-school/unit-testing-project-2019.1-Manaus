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

        Assertions.assertNotNull(person.getName());
        Assertions.assertEquals("Jon", person.getFirstName());
        Assertions.assertEquals("Snow", person.getLastName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");

        Assertions.assertEquals("Jon", person.getName());
        Assertions.assertNull(person.getLastName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");

        Assertions.assertNull(person.getFirstName());
        Assertions.assertEquals("Snow", person.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        String noNameFilledMsg = "Name must be filled";

        //OPTION 1
        try {
            person.getName();
        } catch (Throwable exception) {
            Assertions.assertEquals(noNameFilledMsg, exception.getMessage());
        }

        //OPTION 2
        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> person.getName());
        Assertions.assertEquals(noNameFilledMsg, exception.getMessage());
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate today = LocalDate.now();
        int differentMonth = today.minusMonths(1).getMonth().getValue();
        int differentDay = today.minusDays(1).getDayOfMonth();
        LocalDate dateUT = LocalDate.of(today.getYear(), differentMonth, differentDay);

        Person person = new Person();
        person.setBirthday(dateUT);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        LocalDate today = LocalDate.now();
        int differentDay = today.minusDays(1).getDayOfMonth();
        LocalDate dateUT = LocalDate.of(today.getYear(), today.getMonth(), differentDay);

        Person person = new Person();
        person.setBirthday(dateUT);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate today = LocalDate.now();

        Person person = new Person();
        person.setBirthday(today);
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person personUT = new Person();
        personUT.setName("Some");
        personUT.setLastName("Person");

        Person family = new Person();
        Assertions.assertFalse(family.isFamily(personUT));
        family.addToFamily(personUT);
        Assertions.assertTrue(family.isFamily(personUT));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person familyStark = new Person();
        Person sansa = new Person();
        sansa.setName("Sansa");
        sansa.setLastName("Stark");

        sansa.addToFamily(familyStark);
        familyStark.addToFamily(sansa);

        Assertions.assertTrue(familyStark.isFamily(sansa));
        Assertions.assertTrue(sansa.isFamily(familyStark));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person family = new Person();
        Person personUT = new Person();
        personUT.setName("Some");
        personUT.setLastName("Person");
        family.addToFamily(personUT);

        Person noRelativePerson = new Person();
        noRelativePerson.setName("No");
        noRelativePerson.setLastName("Relative");

        Assertions.assertFalse(family.isFamily(noRelativePerson));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person family = new Person();
        Person personUT = new Person();
        personUT.setName("Some");
        personUT.setLastName("Person");

        family.addToFamily(personUT);

        Assertions.assertTrue(family.isFamily(personUT));
    }
}
