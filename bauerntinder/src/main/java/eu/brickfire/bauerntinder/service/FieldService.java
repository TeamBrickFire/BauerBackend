package eu.brickfire.bauerntinder.service;

import eu.brickfire.bauerntinder.type.Field;
import eu.brickfire.bauerntinder.type.Square;

import java.util.List;

public interface FieldService {
        Field getFieldById(String id);
        List<Square> getAllSquaresByFieldId(String id);
}
