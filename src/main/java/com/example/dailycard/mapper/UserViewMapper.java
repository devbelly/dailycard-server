package com.example.dailycard.mapper;

import com.example.dailycard.dto.UserView;
import com.example.dailycard.model.User;
import com.example.dailycard.security.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserViewMapper {
    public UserView toUserView(UserPrincipal userPrincipal);
    public UserView toUserView(User user);
}
