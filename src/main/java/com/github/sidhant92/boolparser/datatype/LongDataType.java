package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 24/01/2021
 */
public class LongDataType extends AbstractDataType<Long> {
    private ObjectMapper objectMapper;

    public LongDataType(final ObjectMapper objectMapper) {
        super(Long.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.LONG;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public Optional<Long> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}
