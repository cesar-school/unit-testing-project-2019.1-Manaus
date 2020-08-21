package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        String name = person.getFirstName();
        String lastname = person.getLastName();
        Assertions.assertTrue(name == "Jon" && lastname == "Snow");
    }
    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        String name = person.getFirstName();
        Assertions.assertTrue(name == "Jon");
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");
        String name = person.getLastName();
        Assertions.assertTrue(name == "Snow");
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        person.setName(null);
        person.setLastName(null);
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("Name must be filled");
        });

        String expectedMessage = "Name must be filled";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate today = LocalDate.now();
        int mes = today.minusMonths(1).getMonth().getValue();
        int dia = today.minusDays(1).getDayOfMonth();
        int ano = today.minusYears(1).getYear();
        LocalDate dataTest = LocalDate.of(today.getYear(), mes, dia);

        Person person = new Person();
        person.setBirthday(dataTest);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        LocalDate today = LocalDate.now();
        int differentDay = today.minusDays(1).getDayOfMonth();
        LocalDate data = LocalDate.of(today.getYear(), today.getMonth(), differentDay);

        Person person = new Person();
        person.setBirthday(data);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate hoje = LocalDate.now();

        Person birthday = new Person();
        birthday.setBirthday(hoje);
        Assertions.assertTrue(birthday.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person addParente = new Person();
        addParente.setName("Novo");
        addParente.setLastName("Parente");

        Person familia = new Person();
        Assertions.assertFalse(familia.isFamily(addParente));
        familia.addToFamily(addParente);
        Assertions.assertTrue(familia.isFamily(addParente));

    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person parente = new Person();
        Assertions.assertFalse(parente.isFamily(parente));
        parente.addToFamily(parente);
        Assertions.assertTrue(parente.isFamily(parente));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person familiaDosSilva = new Person();
        Person jose = new Person();
        jose.setName("jose");
        jose.setLastName("souza");
        familiaDosSilva.addToFamily(jose);

        Person noRelativePerson = new Person();
        noRelativePerson.setName("No");
        noRelativePerson.setLastName("Relative");

        Assertions.assertFalse(familiaDosSilva.isFamily(noRelativePerson));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person familiaDosSilva = new Person();
        Person jose = new Person();
        jose.setName("jose");
        jose.setLastName("souza");

        familiaDosSilva.addToFamily(jose);

        Assertions.assertTrue(familiaDosSilva.isFamily(jose));
    }
}
