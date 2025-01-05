package todoapp.todoapp_develop.user.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todoapp.todoapp_develop.global.config.PasswordEncoder;
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
        User user = requestDto.toEntity(passwordEncoder);  // 암호화된 비밀번호로 저장
        return new UserResponseDto(userRepository.save(user));
    }

    public User login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto, Long loginUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!user.getId().equals(loginUserId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        user.update(requestDto.getUsername());
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id, Long loginUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!user.getId().equals(loginUserId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        userRepository.delete(user);
    }

    private void validateSignup(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용중인 사용자명입니다.");
        }
    }

}