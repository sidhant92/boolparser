package com.github.sidhant92.boolparser.constant;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 27/07/2020
 */
@Getter
@AllArgsConstructor
public enum ContainerDataType {
    primitive(null) {
        @Override
        public <T> Optional<T> getValue(final DataType dataType, final Object value) {
            return DataTypeFactory.getDataType(dataType).getValue(value);
        }

        @Override
        public boolean isValid(final DataType dataType, final Object value) {
            return DataTypeFactory.getDataType(dataType).isValid(value);
        }
    },
    set(Set.class) {
        @Override
        public Optional<Set<?>> getValue(final DataType dataType, final Object value) {
            final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(this.getClassType(),
                    DataTypeFactory.getDataType(dataType).getClassType());
            Set<?> valueSet = objectMapper.convertValue(value, collectionType);
            return Optional.ofNullable(valueSet);
        }

        @Override
        public boolean isValid(final DataType dataType, final Object value) {
            return isValidCollectionType(dataType, value);
        }
    },
    list(List.class) {
        @Override
        public Optional<List<?>> getValue(final DataType dataType, final Object value) {
            final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(this.getClassType(),
                    DataTypeFactory.getDataType(dataType).getClassType());
            List<?> valueSet = objectMapper.convertValue(value, collectionType);
            return Optional.ofNullable(valueSet);
        }

        @Override
        public boolean isValid(final DataType dataType, final Object value) {
            return isValidCollectionType(dataType, value);
        }
    };

    private static ObjectMapper objectMapper;

    private Class<? extends Collection> classType;

    public static void init(final ObjectMapper objectMapper) {
        ContainerDataType.objectMapper = objectMapper;
    }

    protected boolean isValidCollectionType(final DataType dataType, final Object value) {
        try {
            final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(getClassType(),
                    DataTypeFactory.getDataType(dataType).getClassType());
            Collection<?> collection = objectMapper.convertValue(value, collectionType);
            return collection != null && collection.stream().allMatch(DataTypeFactory.getDataType(dataType)::isValid);

        } catch (final Exception ex) {
            return false;
        }
    }

    public abstract <T> Optional<T> getValue(final DataType dataType, final Object value);

    public abstract boolean isValid(final DataType dataType, final Object value);
}
