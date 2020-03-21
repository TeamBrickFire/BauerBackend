package eu.brickfire.bauerntinder.service;

import com.google.inject.Inject;
import eu.brickfire.bauerntinder.mapper.FieldMapper;
import eu.brickfire.bauerntinder.type.Field;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Square;

import java.util.List;

public class FieldServiceImpl implements FieldService {
    @Inject
    private FieldMapper fieldMapper;

    @Override
    public Field getFieldById(String id) {
        return fieldMapper.selectFieldById(id);
    }

    @Override
    public List<Square> getAllSquaresByFieldId(String id) {
        return fieldMapper.selectAllSquaresByFieldId(id);
    }

    @Override
    public List<Helper> getAllHelperByFieldId(String id) { return fieldMapper.selectAllHelperByFieldId(id); }

    @Override
    public int getHelperCountByFieldId(String id) { return fieldMapper.selectHelperCountByFieldId(id); }
}
