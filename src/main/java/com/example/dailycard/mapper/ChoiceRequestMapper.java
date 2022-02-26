package com.example.dailycard.mapper;

import com.example.dailycard.dto.ChoiceRequest;
import com.example.dailycard.dto.TemplateRequest;
import com.example.dailycard.model.Choice;
import com.example.dailycard.model.Template;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChoiceRequestMapper {

    public abstract Choice create(ChoiceRequest choiceRequest);

    @AfterMapping
    protected void afterCreate(
            ChoiceRequest choiceRequest, @MappingTarget Choice.ChoiceBuilder choice) {
        if (choiceRequest.getChoiceRequest() != null) {
            ChoiceRequest backRequest = choiceRequest.getChoiceRequest();
            Choice backChoice = create(backRequest);
            choice.back(backChoice);
        }
    }

    @Mapping(target = "back", source = "choiceRequest")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(ChoiceRequest choiceRequest, @MappingTarget Choice choice);
}
