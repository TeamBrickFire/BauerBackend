package eu.brickfire.bauerntinder.service;

import com.google.inject.Inject;
import eu.brickfire.bauerntinder.mapper.FieldMapper;
import eu.brickfire.bauerntinder.type.Field;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Square;

import java.util.List;
import java.util.UUID;

public class FieldServiceImpl implements FieldService {
    @Inject
    private FieldMapper fieldMapper;

    @Override
    public Field getFieldById(String id) {
        return fieldMapper.selectFieldById(id);
    }

    @Override
    public Square getSquareById(String id) { return fieldMapper.selectSquareById(id); }

    @Override
    public List<Square> getAllSquaresByFieldId(String id) {
        return fieldMapper.selectAllSquaresByFieldId(id);
    }

    @Override
    public List<Helper> getAllHelperByFieldId(String id) { return fieldMapper.selectAllHelperByFieldId(id); }

    @Override
    public int getHelperCountByFieldId(String id) { return fieldMapper.selectHelperCountByFieldId(id); }

    @Override
    public Field createField(Field field) {
        field.setId(UUID.randomUUID().toString());
        fieldMapper.insertField(field);
        return getFieldById(field.getId());
    }

    @Override
    public Square insertSquare(Square square) {
        square.setId(UUID.randomUUID().toString());
        fieldMapper.insertSquare(square);
        return getSquareById(square.getId());
    }

    @Override
    public void setSquare(Square square) {
        if(square.getId() == null || square.getId().equals("") || getSquareById(square.getId()) == null){
            System.out.println("New Square:" + square.getJSON());
            insertSquare(square);
        } else {
            fieldMapper.updateSquare(square);
        }
    }

    @Override
    public List<Field> getFieldsByFarmerId(String id) {
        return fieldMapper.selectFieldsByFarmerId(id);
    }
}
