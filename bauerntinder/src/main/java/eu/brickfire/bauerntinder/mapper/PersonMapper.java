package eu.brickfire.bauerntinder.mapper;

import eu.brickfire.bauerntinder.type.Farmer;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Person;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {

    Person selectPersonById(@Param("id") String id);
    Person selectPersonByEmail(@Param("email") String email);
    void insertPerson(Person person);
    boolean selectEmailInPerson(@Param("email") String email);
    Helper selectHelperById(@Param("id") String id);
    Farmer selectFarmerById(@Param("id") String id);
    void updatePersonById(Person person);
}
