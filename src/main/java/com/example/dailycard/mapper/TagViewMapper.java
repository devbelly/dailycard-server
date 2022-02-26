package com.example.dailycard.mapper;

import com.example.dailycard.dto.TagView;
import com.example.dailycard.model.Tag;
import org.hibernate.annotations.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TagViewMapper {

    public abstract List<TagView> toTagView(List<Tag> tags);

    //    @Mapping(source = "createdBy", target = "name")
    public abstract TagView toTagView(Tag tag);
}
