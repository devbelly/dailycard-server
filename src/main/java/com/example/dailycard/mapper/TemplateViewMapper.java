package com.example.dailycard.mapper;

import com.example.dailycard.dto.TemplateView;
import com.example.dailycard.model.Template;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TemplateViewMapper {
    public abstract TemplateView toTemplateView(Template template);

    public abstract List<TemplateView> toTemplateView(List<Template> templates);
}
