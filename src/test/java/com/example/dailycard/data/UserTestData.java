// package com.example.dailycard.data;
//
// import com.example.dailycard.dto.CreateUserRequest;
// import com.example.dailycard.dto.UserView;
// import com.example.dailycard.service.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import javax.xml.bind.ValidationException;
//
// @Service
// @RequiredArgsConstructor
// public class UserTestData {
//
//    @Autowired private UserService userService;
//
//    public UserView createUser(String username, String password) throws Exception {
//        CreateUserRequest request = new CreateUserRequest();
//        request.setUsername(username);
//        request.setPassword(password);
//        request.setRePassword(password);
//
//        UserView userView = userService.create(request);
//        return userView;
//    }
// }
