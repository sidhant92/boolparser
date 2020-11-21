package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 23/07/2020
 */
public class IntegerDataType extends AbstractDataType<Integer> {
    private ObjectMapper objectMapper;

    public IntegerDataType(final ObjectMapper objectMapper) {
        super(Integer.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.STRING;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public Optional<Integer> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}
