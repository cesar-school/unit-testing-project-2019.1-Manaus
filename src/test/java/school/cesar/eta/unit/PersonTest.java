package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    Person p = new Person();

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
        Assertions.assertThrows(RuntimeException.class, () -> {
            p.getName();
        });
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate tdy = LocalDate.now();
        int tmwr_m = differentMonth(tdy.getMonthValue());
        int tmwr_d = differentDay(tdy.getDayOfMonth());
        LocalDate bday = LocalDate.of(1985, tmwr_m, tmwr_d);
        p.setBirthday(bday);
        Assertions.assertFalse(p.isBirthdayToday());

    }


    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        LocalDate tdy = LocalDate.now();
        int tmwr_m = tdy.getMonthValue();
        int tmwr_d = differentDay(tdy.getDayOfMonth());
        LocalDate bday = LocalDate.of(1985, tmwr_m, tmwr_d);
        p.setBirthday(bday);
        Assertions.assertFalse(p.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate bday = LocalDate.now();
        p.setBirthday(bday);
        Assertions.assertTrue(p.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person rltve = new Person();
        Assertions.assertFalse(p.isFamily(rltve));
        p.addToFamily(rltve);
        Assertions.assertTrue(p.isFamily(rltve));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person rltve = new Person();
        Assertions.assertFalse(rltve.isFamily(p));
        rltve.addToFamily(p);
        Assertions.assertTrue(rltve.isFamily(p));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person nop = new Person();
        Assertions.assertFalse(p.isFamily(nop));
    }

    @Test
    public void isFamily_relativePerson_true() {
      Person prlt = new Person();
      p.addToFamily(prlt);
      Assertions.assertTrue(p.isFamily(prlt));

    }

    private int differentDay(int day){
        if (day >=28){
            day = day - 1;
        }else{
            day = day + 1;
        }
        return day;
    }

    private int differentMonth(int month){
        if (month == 12){
            month = month - 1;
        }else{
            month = month + 1;
        }
        return month;
    }
}
