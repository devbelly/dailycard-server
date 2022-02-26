package com.example.dailycard.service;

import com.example.dailycard.dto.CreateUserRequest;
import com.example.dailycard.dto.UserView;
import com.example.dailycard.mapper.UserEditMapper;
import com.example.dailycard.mapper.UserViewMapper;
import com.example.dailycard.model.Role;
import com.example.dailycard.model.RoleName;
import com.example.dailycard.model.User;
import com.example.dailycard.repository.RoleRepository;
import com.example.dailycard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEditMapper userEditMapper;
    private final RoleRepository roleRepository;
    private final UserViewMapper userViewMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserView create(CreateUserRequest request) throws Exception {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username already exist");
        }
        if (!request.checkPasswordValid()) {
            throw new ValidationException("Password don't match", "400");
        }

        User user = userEditMapper.create(request);
        Set<Role> roles = new HashSet<>();

        if (user.getRoles() == null) {
            Role userRole =
                    roleRepository
                            .findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Role is not Found"));
            roles.add(userRole);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User result = userRepository.save(user);
        return userViewMapper.toUserView(user);
    }
}
