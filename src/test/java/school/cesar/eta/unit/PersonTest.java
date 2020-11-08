package school.cesar.eta.unit;

import org.easymock.IMockBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sun.jvm.hotspot.utilities.Assert;

import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonTest {
    @InjectMocks
    Person mockperson;
    @Spy
    List<Person> family;

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        String name = person.getName();
        assertEquals("JonSnow",name);
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        String name = person.getName();
        assertEquals("Jon",name);
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");
        String name = person.getName();
        assertEquals("Snow",name);
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        Exception thrown = assertThrows(Exception.class, () -> person.getName(),
        "Name must be filled");
        assertTrue(thrown.getMessage().contains("Name must be filled"));
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person person = new Person(){
            @Override
            public LocalDate getNow() {
              return LocalDate.of(1989,03,23);
            }
        };

        person.setBirthday(LocalDate.of(1989,04,22));

        assertFalse(person.isBirthdayToday());

    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person person = new Person(){
            @Override
            public LocalDate getNow() {
                return LocalDate.of(1989,03,23);
            }
        };

        person.setBirthday(LocalDate.of(1989,03,22));

        assertFalse(person.isBirthdayToday());

    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new Person(){
            @Override
            public LocalDate getNow() {
                return LocalDate.of(1989,03,23);
            }
        };

        person.setBirthday(LocalDate.of(1989,03,23));

        assertTrue(person.isBirthdayToday());


    }


    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person person = new Person();
        mockperson.addToFamily(person);
        verify(family, times(1)).add(person);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person = new Person();
        person.addToFamily(mockperson);
        verify(family, times(1)).add(person);
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person person = new Person();
        Person person2 = new Person();
        assertFalse(person.isFamily(person2));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person relative = new Person();
        when(family.contains(relative)).thenReturn(true);
        assertTrue(mockperson.isFamily(relative));
    }


}
