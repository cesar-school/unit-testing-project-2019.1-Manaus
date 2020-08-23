package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import sun.jvm.hotspot.utilities.Assert;
//import sun.jvm.hotspot.utilities.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    /*OK TERMINADA*/
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {

        Person JonSnow = new Person();
        JonSnow.setName("Jon");
        JonSnow.setLastName("Snow");
        String firstName = JonSnow.getFirstName();
        String lastName = JonSnow.getLastName();
        assertEquals(JonSnow.getName(),"JonSnow");

    }

    /*OK TERMINADA*/
    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person JonSnow = new Person();
        JonSnow.setName("Jon");
        JonSnow.setLastName(null);
        String firstName = JonSnow.getFirstName();
        String lastName = JonSnow.getLastName();
        assertEquals(JonSnow.getName(),"Jon");

    }

    /*OK TERMINADA*/
    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person JonSnow = new Person();
        JonSnow.setName(null);
        JonSnow.setLastName("Snow");
        String firstName = JonSnow.getFirstName();
        String lastName = JonSnow.getLastName();
        assertEquals(JonSnow.getName(),"Snow");

    }

    /*OK TERMINADA*/
    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person JonSnow = new Person();
        JonSnow.setName(null);
        JonSnow.setLastName(null);
        Exception exception = assertThrows(RuntimeException.class, () ->
                JonSnow.getName());
        assertEquals("Name must be filled", exception.getMessage());

    }

    /*OK TERMINADA*/
    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person JonSnow = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String myday = "1889-01-21";
        LocalDate birthday = LocalDate.parse(myday, formatter);
        JonSnow.setBirthday(birthday);
        JonSnow.getBirthday();
        assertFalse(JonSnow.isBirthdayToday());

    }

    /*OK TERMINADA*/
    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person JonSnow = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String myday = "1889-01-21";
        LocalDate birthday = LocalDate.parse(myday, formatter);
        JonSnow.setBirthday(birthday);
        JonSnow.getBirthday();
        assertFalse(JonSnow.isBirthdayToday());

    }

    /*OK TERMINADA*/
    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person jonSnow = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String myday = "1889-01-21";
        LocalDate birthday = LocalDate.parse(myday, formatter);
        jonSnow.setBirthday(birthday);
        jonSnow.getBirthday();
        assertTrue(jonSnow.isBirthdayToday());

    }


    /*OK TERMINADA*/
    @Test
    public void addToFamily_somePerson_familyHasNewMember() {

        Person Jon = new Person();
        Person Arya = new Person();

        Jon.addToFamily(Arya);

        Assertions.assertTrue(Jon.isFamily(Arya));


/*codigos para o terminal
* git add .
* git commit -m "Atividade de testes unitarios"
* git pull
*
* */





    }

    /*OK TERMINADA*/
    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person Jon = new Person();
        Person Arya = new Person();
        Jon.addToFamily(Arya);


        Assertions.assertTrue(Jon.isFamily(Arya));
        Assertions.assertTrue(Arya.isFamily(Jon));
    }


/*ACHO QUE ESTA TERMINADO*/
    @Test
    public void isFamily_nonRelativePerson_false() {
        Person Jon = new Person();
        Person Tyrion = new Person();

        assertFalse(Jon.isFamily(Tyrion));


    }

    /*NÃO TERMINADO*/
    @Test
    public void isFamily_relativePerson_true() {
        Person Jon = new Person();
        Person Arya = new Person();

    }
}
