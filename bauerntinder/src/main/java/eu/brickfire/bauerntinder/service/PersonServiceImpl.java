package eu.brickfire.bauerntinder.service;

import com.google.inject.Inject;
import eu.brickfire.bauerntinder.mapper.PersonMapper;
import eu.brickfire.bauerntinder.type.Farmer;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Person;

import java.security.SecureRandom;
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
    public boolean isHelper(String id) {
        return personMapper.selectIsHelper(id);
    }

    @Override
    public boolean isFarmer(String id) {
        return personMapper.selectIsFarmer(id);
    }

    @Override
    public String createToken(Person person) {
        person.setToken(generateRandomString(32));
        personMapper.updatePersonById(person);
        return person.getToken();
    }

    @Override
    public boolean checkToken(String id, String token) {
        return personMapper.selectPersonById(id).getToken().equals(token);
    }

    @Override
    public Helper saveHelper(Helper helper) {
        personMapper.insertHelper(helper);
        return getHelperById(helper.getId());
    }

    @Override
    public Farmer saveFarmer(Farmer farmer) {
        personMapper.insertFarmer(farmer);
        return getFarmerById(farmer.getId());
    }


    private final String Letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private SecureRandom random = new SecureRandom();

    private String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(Letters.length());
            char rndChar = Letters.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }


}
