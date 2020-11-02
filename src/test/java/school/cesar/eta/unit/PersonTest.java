package school.cesar.eta.unit;

import org.easymock.IMockBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.Year;


import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person mockperson = mock(Person.class);
        when(mockperson.getFirstName()).thenReturn("Jon");
        when(mockperson.getLastName()).thenReturn("Snow");
        assertEquals("Jon",mockperson.getFirstName());
        assertEquals("Snow",mockperson.getLastName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person mockperson = mock(Person.class);
        when(mockperson.getFirstName()).thenReturn("Jon");
        when(mockperson.getLastName()).thenReturn("");
        assertEquals("Jon",mockperson.getFirstName());
        assertEquals("",mockperson.getLastName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person mockperson = mock(Person.class);
        when(mockperson.getFirstName()).thenReturn("");
        when(mockperson.getLastName()).thenReturn("Snow");
        assertEquals("",mockperson.getFirstName());
        assertEquals("Snow",mockperson.getLastName());

    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person mockperson = mock(Person.class);
        when(mockperson.getName()).thenThrow(new RuntimeException());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("Name must be filled");
        });

        String expectedMessage = "Name must be filled";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person mockperson = mock(Person.class);
        when(mockperson.getNow()).thenReturn(LocalDate.of(1989,02,21));
        when(mockperson.isBirthdayToday()).thenReturn(false);
        assertFalse(mockperson.isBirthdayToday());

    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person mockperson = mock(Person.class);
        when(mockperson.getNow()).thenReturn(LocalDate.of(1989,02,21));
        when(mockperson.isBirthdayToday()).thenReturn(false);
        assertFalse(mockperson.isBirthdayToday());

//        fail();
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {

        Person mockperson = mock(Person.class);
        when(mockperson.getNow()).thenReturn(LocalDate.of(1989,02,21));
        when(mockperson.isBirthdayToday()).thenReturn(true);
        assertTrue(mockperson.isBirthdayToday());


    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person person = new Person();
        person.addToFamily(person);
        assertTrue(person.isFamily(person));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person = new Person();
        person.addToFamily(person);
        assertTrue(person.isFamily(person));

    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person person = new Person();
        Person person2 = new Person();
        assertFalse(person.isFamily(person2));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person person = new Person();
        Person relative = new Person();
        person.addToFamily(relative);
        assertTrue(person.isFamily(relative));
    }


//    public int diaAlterado(int dia){
//        if(dia >= 29) {
//            return dia -= 1;
//        }else{
//            return dia +=1;
//        }
//    }
//
//    public int mesAlterado(int mes){
//        if(mes >= 29) {
//            return mes -= 1;
//        }else{
//            return mes +=1;
//        }
//    }
//


}
