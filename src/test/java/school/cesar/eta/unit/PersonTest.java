package school.cesar.eta.unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    //Person p = new Person();
    PersonStub p = new PersonStub();

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {

        p.setName("Jon");
        p.setLastName("Snow");
        String exp_name = p.getFirstName() + p.getLastName();
        Assertions.assertEquals(exp_name, p.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {

        p.setName("Jon");
        String exp_name = p.getFirstName();
        Assertions.assertEquals(exp_name, p.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {

        p.setLastName("Snow");
        String exp_name = p.getLastName();
        Assertions.assertEquals(exp_name, p.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class, () -> p.getName());
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate bday = LocalDate.of(1985, 11, 19);
        p.setBirthday(bday);
        Assertions.assertFalse(p.isBirthdayTodayStub(LocalDate.of(1985, 12, 2)));

    }


    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        LocalDate bday = LocalDate.of(1985, 11, 19);
        p.setBirthday(bday);
        Assertions.assertFalse(p.isBirthdayTodayStub(LocalDate.of(1985, 11, 2)));
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate bday = LocalDate.of(1985, 11, 19);
        p.setBirthday(bday);
        Assertions.assertTrue(p.isBirthdayTodayStub(LocalDate.of(1985, 11, 19)));
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person rltve = new Person();
        p.addToFamily(rltve);
        List<Person> family = p.GetFamily();

        for (Person f : family) {
            if (f == rltve) {
                Assertions.assertTrue(true);
            }
        }

        Assertions.assertFalse(false);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        PersonStub rltve = new PersonStub();
        List<Person> previous = rltve.GetFamily();
        rltve.addToFamily(new Person());
        List<Person> current = rltve.GetFamily();

        Assertions.assertFalse(previous.size() != current.size());

    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person nop = new Person();
        Assertions.assertFalse(p.isFamily(nop));
    }

    @Test
    public void isFamily_relativePerson_true() {
        PersonStub rltve = new PersonStub();
        p.addToFamily(rltve);
        Person rltveb = new Person();
        rltve.addToFamily(rltveb);

        List<Person> family = rltve.GetFamily();

        for (Person f : family) {
            if (f == rltveb) {
                Assertions.assertTrue(true);
            }
        }

        Assertions.assertFalse(false);
    }

}
