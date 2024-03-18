package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.UserAdminRepository;
import com.loopcreative.web.admin.validate.UserAdminServiceVali;
import com.loopcreative.web.dto.UserDto;
import com.loopcreative.web.entity.User;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.form.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAdminService {

    private final UserAdminRepository userAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAdminServiceVali userAdminServiceVali;

    public UserDto Login(UserForm userForm){
        Optional<User> findUser= userAdminRepository.findByUserId(userForm.getUserId());

        return findUser.orElseThrow(() -> new RestApiException(UserErrorCode.FAIL_USER)).chageDto();
    }

    @Transactional
    public String join(UserForm userForm) {
        //valid
        userAdminServiceVali.userValid(userForm);

        String userPw = userForm.getUserPw();
        String encodeUserPw = passwordEncoder.encode(userPw);
        userForm.setUserPw(encodeUserPw);
        User user = userForm.toEntity();

        User saveUser = userAdminRepository.save(user);

        return saveUser.getUserId();
    }
}
