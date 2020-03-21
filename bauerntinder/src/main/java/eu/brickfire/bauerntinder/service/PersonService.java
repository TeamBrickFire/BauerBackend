package eu.brickfire.bauerntinder.service;

import eu.brickfire.bauerntinder.type.Farmer;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Person;

public interface PersonService {

    Person getPersonById(String id);

    Person getPersonByEmail(String email);

    Person savePerson(Person person);

    boolean isEmailFree(String email);

    Helper getHelperById(String id);

    Farmer getFarmerById(String id);

    String createToken(Person person);

    boolean checkToken(String id, String token);
}
