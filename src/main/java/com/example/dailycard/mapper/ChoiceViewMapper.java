package com.example.dailycard.mapper;

import com.example.dailycard.dto.ChoiceView;
import com.example.dailycard.model.Choice;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChoiceViewMapper {

    public abstract ChoiceView toChoiceView(Choice choice);

    public abstract List<ChoiceView> toChoiceView(List<Choice> choices);

    @AfterMapping
    void created(Choice choice, @MappingTarget ChoiceView choiceView) {
        if (choice.getBack() != null) {
            ChoiceView backView = toChoiceView(choice.getBack());
            choiceView.setBack(backView);
        }
    }
}
