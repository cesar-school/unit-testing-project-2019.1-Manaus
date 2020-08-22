package school.cesar.eta.unit;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person jonSnow = new Person();
        jonSnow.setName("jon");
        jonSnow.setLastName("Snow");
        String firstName = jonSnow.getFirstName();
        String lastName = jonSnow.getLastName();
        //assertEquals("jon", firstName);
        //assertEquals("Snow", lastName);
        assertEquals(jonSnow.getName(),"jonSnow");
        //fail();
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person jonSnow = new Person();
        jonSnow.setName("jon");
        jonSnow.setLastName(null);
        String firstName = jonSnow.getFirstName();
        String lastName = jonSnow.getLastName();
        //assertEquals("jon", firstName);
        //assertEquals(null, lastName);
        assertEquals(jonSnow.getName(),"jon");
        //fail();
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person jonSnow = new Person();
        jonSnow.setName(null);
        jonSnow.setLastName("snow");
        String firstName = jonSnow.getFirstName();
        String lastName = jonSnow.getLastName();
        //assertEquals("Snow", lastName);
        //assertEquals(null, firstName);
        assertEquals(jonSnow.getName(),"snow");
        //fail();
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person jonSnow = new Person();
        jonSnow.setName(null);
        jonSnow.setLastName(null);
        Exception exception = assertThrows(RuntimeException.class, () ->
                jonSnow.getName());
        assertEquals("Name must be filled", exception.getMessage());
        System.out.println("Mensagem:"+exception.getMessage());
        //fail();
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person jonSnow = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //mes diferente e dia diferente
        String str_bday = "1973-07-29";
        System.out.println(str_bday);
        LocalDate birthday = LocalDate.parse(str_bday, formatter);
        jonSnow.setBirthday(birthday);
        jonSnow.getBirthday();
        System.out.println(jonSnow.isBirthdayToday());
        assertFalse(jonSnow.isBirthdayToday());
        //fail();
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person jonSnow = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //mes igual e dia diferente
        String str_bday = "1979-08-01";
        System.out.println(str_bday);
        LocalDate birthday = LocalDate.parse(str_bday, formatter);
        jonSnow.setBirthday(birthday);
        jonSnow.getBirthday();
        System.out.println(jonSnow.isBirthdayToday()); //false
        assertFalse(jonSnow.isBirthdayToday());
        //fail();
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person jonSnow = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //mes e dia iguais
        String str_bday = "1989-08-21";
        //System.out.println(s_bday);
        LocalDate birthday = LocalDate.parse(str_bday, formatter);
        System.out.println("nascimento: "+birthday);

        jonSnow.setBirthday(birthday);
        jonSnow.getBirthday();

        System.out.println(jonSnow.isBirthdayToday());
        assertTrue(jonSnow.isBirthdayToday()); //true
        //fail();
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {

        Person markito = new Person();
        markito.setName("markito");
        markito.setLastName("rodela");
        Person faxinildo = new Person();
        faxinildo.setName("faxinildo");
        faxinildo.setLastName("faxinista");
        faxinildo.addToFamily(faxinildo);
        faxinildo.addToFamily(markito);
        //assertTrue(true);
       // System.out.println(peterParker.);
        //assertArrayEquals(peterParker.addToFamily());
        //fail();
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person somePerson = new Person();
        somePerson.setName("some");
        somePerson.setLastName("Person");
        //assertTrue(somePerson.addToFamily(somePerson));

        //fail();
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person fooBarX = new Person();
        Person charlie = new Person();
        charlie.addToFamily(charlie);
        fooBarX.addToFamily(fooBarX);
        //System.out.println(peterParker.isFamily(fooBar4));
        assertFalse(charlie.isFamily(fooBarX)); //false

        //fail();
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person fooBarY = new Person();
        Person peterParker = new Person();
        peterParker.addToFamily(peterParker);
        peterParker.addToFamily(fooBarY);
        //System.out.println(peterParker.isFamily(fooBar5));
        assertTrue(peterParker.isFamily(fooBarY));
        //fail();
    }
}




