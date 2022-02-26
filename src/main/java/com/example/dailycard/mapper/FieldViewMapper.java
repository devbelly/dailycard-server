package com.example.dailycard.mapper;

import com.example.dailycard.dto.FieldView;
import com.example.dailycard.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FieldViewMapper {

    public abstract FieldView toFieldView(Field field);

    public abstract List<FieldView> toFieldView(List<Field> fields);
}
