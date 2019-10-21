package ru.yamal.barbos.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.yamal.barbos.configuration.security.JwtTokenProvider;
import ru.yamal.barbos.domain.model.UserEntity;
import ru.yamal.barbos.domain.repository.UserRepository;
import ru.yamal.barbos.dto.UserDto;
import ru.yamal.barbos.dto.UserRegistrationDto;
import ru.yamal.barbos.exception.CustomException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public String signin(String phoneNumber, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, password));

            Optional<UserEntity> byPhone = userRepository.findByPhoneNumber(phoneNumber);

            UserEntity userEntity;
            if (byPhone.isPresent()) {
                userEntity = byPhone.get();
            } else {
                throw new CustomException("Not found", HttpStatus.NOT_FOUND);
            }

            return jwtTokenProvider.createToken(userEntity.getPhoneNumber(), userEntity.getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(UserRegistrationDto userRegistrationDto) {
        UserEntity userEntity = modelMapper.map(userRegistrationDto, UserEntity.class);
        if (!userRepository.existsByPhoneNumber(userEntity.getPhoneNumber())) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRoles(userRegistrationDto.getRoles());
            userRepository.save(userEntity);
            return jwtTokenProvider.createToken(userEntity.getPhoneNumber(), userEntity.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String phone) {
        userRepository.deleteByPhoneNumber(phone);
    }

    public UserDto search(String phone) {
        return modelMapper.map(userRepository.findByPhoneNumber(phone).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND)), UserDto.class);
    }

    public UserDto whoami(HttpServletRequest req) {
        return modelMapper.map(userRepository.findByPhoneNumber(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)))
                .orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND)), UserDto.class);
    }

    public String refresh(String phone) {
        return jwtTokenProvider.createToken(phone, userRepository.findByPhoneNumber(phone).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND)).getRoles());
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity, UserDto.class)).collect(Collectors.toList());
    }

}
