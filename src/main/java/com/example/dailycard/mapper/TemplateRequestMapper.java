package com.example.dailycard.mapper;

import com.example.dailycard.dto.TemplateRequest;
import com.example.dailycard.dto.TemplateView;
import com.example.dailycard.model.Choice;
import com.example.dailycard.model.Template;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Slf4j
public abstract class TemplateRequestMapper {

    @Autowired private ChoiceRequestMapper choiceRequestMapper;

    @BeanMapping(qualifiedByName = "forCreate")
    public abstract Template create(TemplateRequest templateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(TemplateRequest templateRequest, @MappingTarget Template template);

    @Named("forCreate")
    @AfterMapping
    void afterCreate(TemplateRequest templateRequest, @MappingTarget Template template) {

        if (templateRequest.getChoices() != null) {
            template.setChoices(
                    templateRequest.getChoices().stream()
                            .map(
                                    choiceRequest -> {
                                        Choice choice = choiceRequestMapper.create(choiceRequest);
                                        choice.setTemplate(template);
                                        choice.getBack().setTemplate(template);
                                        return choice;
                                    })
                            .collect(Collectors.toList()));
        }
    }
}
