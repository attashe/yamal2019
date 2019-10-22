package ru.yamal.barbos.configuration.security.ftl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.yamal.barbos.configuration.security.JwtTokenProvider;
import ru.yamal.barbos.exception.CustomException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class FtlAuthProvider {

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    private final JwtTokenProvider jwtTokenProvider;

    public User getUser(HttpServletRequest request) {
        String token = CookieUtil.getValue(request, jwtTokenCookieName);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        if (authentication != null && authentication.getPrincipal() != null) {
            return (User) jwtTokenProvider.getAuthentication(token).getPrincipal();
        } else {
            throw new CustomException("Not Authorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
