package com.example.dailycard.mapper;

import com.example.dailycard.dto.CreateUserRequest;
import com.example.dailycard.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserEditMapper {


    public abstract User create(CreateUserRequest request);


}
