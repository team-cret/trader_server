package traderV0.trader.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import traderV0.trader.constant.UserState;
import traderV0.trader.dto.ErrorDto;
import traderV0.trader.dto.LoginDto;
import traderV0.trader.dto.ResponseDto;
import traderV0.trader.dto.SuccessDto;
import traderV0.trader.entity.UserInfo;
import traderV0.trader.function.Security;
import traderV0.trader.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;
    private final Security security;
    
    public ResponseDto signUp(LoginForm form){
        if (userRepository.findById(form.getId()).isEmpty()){
            userRepository.save(new UserInfo(form.getId(), security.loginHashing(form.getPassword()), form.getName(), 0, UserState.OFFLINE));
            log.info("Insert New User Id : {}", form.getId());
            return new SuccessDto(201, HttpStatus.CREATED, "Id Saved");
        }else{
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Id Duplicate Error");
        }
    }

    @Transactional
    public ResponseDto login(LoginForm form) {
        Optional<UserInfo> findUserOption = userRepository.findById(form.getId());
        if (findUserOption.isEmpty()){
            return new ErrorDto(401,HttpStatus.UNAUTHORIZED, "User Id No Exist");
        }else{
            UserInfo findUser = findUserOption.get();
            if (!findUser.getPassword().equals(security.loginHashing(form.getPassword()))){
                return new ErrorDto(401,HttpStatus.UNAUTHORIZED, "Password Error");
            }else{
                String uuid = UUID.randomUUID().toString();
                findUser.setHttpSession(uuid);
                findUser.updateSessionDate();
                log.info("User Login id : {}", form.getId());
                return new LoginDto(202, HttpStatus.ACCEPTED, uuid);
            }
        }
    }

}
