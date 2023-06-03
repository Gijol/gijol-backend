package com.gist.graduation.utils.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component
@RequiredArgsConstructor
public class AesConverter implements AttributeConverter<String, String> {

    private final Aes256Component aes256Component;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (ObjectUtils.isEmpty(attribute)) {
            return attribute;
        }
        try {
            return aes256Component.encrypt(attribute);
        } catch (Exception e) {
            throw new RuntimeException("failed to encrypt data", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return aes256Component.decrypt(dbData);
        } catch (Exception e) {
            return dbData;
        }
    }

}
