package com.example.dailycard.mapper;

import com.example.dailycard.dto.TagRequest;
import com.example.dailycard.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TagRequestMapper {

    public abstract Tag toTag(TagRequest tagRequest);
}
