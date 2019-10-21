package ru.yamal.barbos.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yamal.barbos.domain.model.UserEntity;
import ru.yamal.barbos.domain.repository.UserRepository;
import ru.yamal.barbos.exception.CustomException;

@RequiredArgsConstructor
@Service
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByPhoneNumber(phone).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));

        return org.springframework.security.core.userdetails.User
                .withUsername(phone)
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
