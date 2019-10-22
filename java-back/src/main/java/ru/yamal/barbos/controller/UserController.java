package ru.yamal.barbos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.yamal.barbos.domain.model.Role;
import ru.yamal.barbos.dto.LoginDto;
import ru.yamal.barbos.dto.UserDto;
import ru.yamal.barbos.dto.UserRegistrationDto;
import ru.yamal.barbos.exception.CustomException;
import ru.yamal.barbos.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public String login(@ApiParam("Login dto") @RequestBody LoginDto loginDto) {
        return userService.signin(loginDto.getPhoneNumber(), loginDto.getPassword());
    }

    @PostMapping("/signup")
    public String signup(@ApiParam("Signup User") @RequestBody UserRegistrationDto user, Principal principal) {
        if ((!user.getRoles().contains(Role.ROLE_ADMIN) && !user.getRoles().contains(Role.ROLE_HELPER))
                || ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getAuthorities().contains(Role.ROLE_ADMIN)) {
            return userService.signup(user);
        }
        throw new CustomException("Not allowed", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> search(@ApiParam("Username") @PathVariable String username) {
        return ResponseEntity.ok(userService.search(username));
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<UserDto> whoami(HttpServletRequest req) {
        return ResponseEntity.ok(userService.whoami(req));
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(userService.findAll());
    }
}
