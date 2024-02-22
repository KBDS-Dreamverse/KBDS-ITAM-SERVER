package com.kbds.itamserveradmin.domain.assetRequest.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter
public class LicneseCategoryConverter implements AttributeConverter<LicenseCategory,Integer> {

    @Override
    public Integer convertToDatabaseColumn(LicenseCategory attribute) {
        if(attribute ==null) return null;
        return attribute.getCode();
    }

    @Override
    public LicenseCategory convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for(LicenseCategory category : LicenseCategory.values()){
            if(category.getCode() ==dbData) return category;
        }
        throw new IllegalArgumentException("Unknown ApprovalStatus code: " + dbData);
    }
}

