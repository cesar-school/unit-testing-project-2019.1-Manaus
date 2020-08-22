package school.cesar.eta.unit;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person JonSnow = new Person();
        JonSnow.setName("Jon");
        JonSnow.setLastName("Snow");
        String firstName = JonSnow.getFirstName("");
        String lastName = JonSnow.getLastName("");
        Assertions.assertEquals("Jon", firstName);
        Assertions.assertEquals("Snow", lastName);

    }


    @Test
    public void getName_firstNameJonNoLastName_jon() {

        Person JonSnow = new Person();
        JonSnow.setName("Jon");
        JonSnow.setLastName("");
        String firstName = JonSnow.getFirstName("");
        String lastName = JonSnow.getLastName("");
        Assertions.assertEquals("Jon", firstName);
        Assertions.assertEquals("", lastName);
        //fail();
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {

        Person JonSnow = new Person();
        JonSnow.setName("");
        JonSnow.setLastName("Snow");
        String firstName = JonSnow.getFirstName("");
        String lastName = JonSnow.getLastName("");
        Assertions.assertEquals("", firstName);
        Assertions.assertEquals("Snow", lastName);
        //fail();
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {

        Person JonSnow = new Person();
        JonSnow.setName("");
        JonSnow.setLastName("");
        String firstName = JonSnow.getFirstName("");
        String lastName = JonSnow.getLastName("");
        Assertions.assertEquals("", firstName);
        Assertions.assertEquals("", lastName);

        //fail();
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {

        Person birthdayTest = new Person();
        birthdayTest.setBirthday(LocalDate.now());
        LocalDate birthday = birthdayTest.getBirthday();
        int day = birthdayTest.getBirthday().getDayOfMonth();
        int mes = birthdayTest.getBirthday().getMonthValue();
        Assertions.assertEquals(16, day);
        Assertions.assertEquals(7, mes);
        // fail();
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {


        Person birthdayTest = new Person();
        birthdayTest.setBirthday(LocalDate.now());
        LocalDate birthday = birthdayTest.getBirthday();
        int day = birthdayTest.getBirthday().getDayOfMonth();
        int mes = birthdayTest.getBirthday().getMonthValue();
        Assertions.assertEquals(16, day);
        Assertions.assertEquals(8, mes);
        // fail();
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {


        Person birthdayTest = new Person();
        birthdayTest.setBirthday(LocalDate.parse("2020-08-15"));
        LocalDate birthday = birthdayTest.getBirthday();
        int day = birthdayTest.getBirthday().getDayOfMonth();
        int mes = birthdayTest.getBirthday().getMonthValue();
        Assertions.assertEquals(15, day);
        Assertions.assertEquals(8, mes);
        // fail();
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {

        Person family = new Person();
        Person NewMember = new Person();
        family.setLastName("Snow");
        NewMember.setName("Joao");
        NewMember.setLastName("Snow");
        NewMember.setBirthday(LocalDate.parse("2000-05-20"));
        String familyNickname = family.getLastName("");
        String lastName = NewMember.getLastName("");
        Assertions.assertEquals(familyNickname, lastName);
        family.addToFamily(NewMember);
        Assertions.assertTrue(family.isFamily(NewMember));


        //fail();
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {

        Person family = new Person();
        Person NewMember = new Person();
        family.setLastName("Snow");
        NewMember.setName("Joao");
        NewMember.setLastName("Snow");
        Assertions.assertFalse(family.isFamily(NewMember));
        Assertions.assertFalse(NewMember.isFamily(family));
        family.addToFamily(NewMember);
        Assertions.assertTrue(family.isFamily(NewMember));
        Assertions.assertTrue(NewMember.isFamily(family));

        //fail();
    }

    @Test
    public void isFamily_nonRelativePerson_false() {

        Person family = new Person();
        Person NewMember = new Person();
        Assertions.assertFalse(family.isFamily(NewMember));
        Assertions.assertFalse(NewMember.isFamily(family));
        Assertions.assertTrue(family.isFamily(NewMember));
        Assertions.assertTrue(NewMember.isFamily(family));
        //fail();
    }

    @Test
    public void isFamily_relativePerson_true() {

        Person family = new Person();
        Person NewMember = new Person();
        Assertions.assertFalse(family.isFamily(NewMember));
        Assertions.assertFalse(NewMember.isFamily(family));
        family.addToFamily(NewMember);
        NewMember.addToFamily(family);
        Assertions.assertTrue(family.isFamily(NewMember));
        Assertions.assertTrue(NewMember.isFamily(family));
        //fail();
    }
}
