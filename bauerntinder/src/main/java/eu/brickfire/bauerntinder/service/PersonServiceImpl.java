package eu.brickfire.bauerntinder.service;

import com.google.inject.Inject;
import eu.brickfire.bauerntinder.mapper.PersonMapper;
import eu.brickfire.bauerntinder.type.Farmer;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Person;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class PersonServiceImpl implements PersonService {

    @Inject
    private PersonMapper personMapper;

    @Override
    public Person getPersonById(String id) {
        return personMapper.selectPersonById(id);
    }

    @Override
    public Person getPersonByEmail(String email) {
        return personMapper.selectPersonByEmail(email);
    }

    @Override
    public Person savePerson(Person person) {
        person.setId(UUID.randomUUID().toString());
        personMapper.insertPerson(person);
        return getPersonById(person.getId());
    }

    @Override
    public boolean isEmailFree(String email) {
        return !personMapper.selectEmailInPerson(email);
    }

    @Override
    public Helper getHelperById(String id) {
        return personMapper.selectHelperById(id);
    }

    @Override
    public Farmer getFarmerById(String id) {
        return personMapper.selectFarmerById(id);
    }

    @Override
    public String createToken(Person person) {
        byte[] array = new byte[32];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        person.setToken(generatedString);
        personMapper.updatePerson(person);
        return generatedString;
    }

    @Override
    public boolean checkToken(String email, String token){
        return personMapper.selectPersonByEmail(email).getToken().equals(token);
    }


}
