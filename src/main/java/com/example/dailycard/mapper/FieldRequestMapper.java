package com.example.dailycard.mapper;

import com.example.dailycard.dto.FieldRequest;
import com.example.dailycard.dto.TemplateRequest;
import com.example.dailycard.model.Field;
import com.example.dailycard.model.Template;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {StringTagMapper.class})
public abstract class FieldRequestMapper {

    @Mapping(target = "tag", ignore = true)
    public abstract Field create(FieldRequest fieldRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(FieldRequest fieldRequest, @MappingTarget Field field);
}
// @Mapping(target = "tag", ignore = true)
