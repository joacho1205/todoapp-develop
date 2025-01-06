package todoapp.todoapp_develop.user.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todoapp.todoapp_develop.global.config.PasswordEncoder;
import todoapp.todoapp_develop.global.exception.*;
import todoapp.todoapp_develop.user.domain.User;
import todoapp.todoapp_develop.auth.dto.request.LoginRequestDto;
import todoapp.todoapp_develop.user.dto.request.UserRequestDto;
import todoapp.todoapp_develop.user.dto.response.UserResponseDto;
import todoapp.todoapp_develop.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto signup(UserRequestDto requestDto) {
        validateSignup(requestDto);
        User user = requestDto.toEntity(passwordEncoder);
        return new UserResponseDto(userRepository.save(user));
    }

    public User login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new EmailNotFoundException(ErrorCode.EMAIL_NOT_FOUND));
        user.validatePassword(requestDto.getPassword(), passwordEncoder);
        return user;
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistingException(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto, Long loginUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistingException(ErrorCode.USER_NOT_FOUND));
        user.validateUpdate(loginUserId);
        user.update(requestDto.getUsername());
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id, Long loginUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistingException(ErrorCode.USER_NOT_FOUND));
        user.validateDelete(loginUserId);
        userRepository.delete(user);
    }

    private void validateSignup(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new ExistingEmailException(ErrorCode.EXISTING_EMAIL);
        }
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new ExistingUserNameException(ErrorCode.EXISTING_USERNAME);
        }
    }

}