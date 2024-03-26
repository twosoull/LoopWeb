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

    /**
     * 1. userId로 User를 찾는다.
     * 2. passwordEncoder로 비밀번호 매치
     * 3. 매치가 되지 않을 시 에러코드 반환 (FAIL_USER)
     * 4. 비밀번호는 set으로 빈값 처리하여 반환 (노출 x)
     * @param userForm
     * @return
     */
    public UserDto Login(UserForm userForm){
        UserDto findUser = userAdminRepository.findByUserId(userForm.getUserId())
                .orElseThrow(() -> new RestApiException(UserErrorCode.FAIL_USER)).chageDto();

        boolean result = passwordEncoder.matches(userForm.getUserPw(),findUser.getUserPw());
        if(!result){
            throw new RestApiException(UserErrorCode.FAIL_USER);
        }

        findUser.setUserPw(""); //비밀번호는 비운다.
        return findUser;
    }

    /**
     * 1. userForm 값 vali 처리
     * 2. passwordEncoder 사용하여 비밀번호 암호화 후 저장
     * 3. 저장 UserId(키값x) 반환
     * @param userForm
     * @return
     */
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
