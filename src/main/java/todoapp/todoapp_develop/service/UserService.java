package todoapp.todoapp_develop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todoapp.todoapp_develop.config.PasswordEncoder;
import todoapp.todoapp_develop.domain.User;
import todoapp.todoapp_develop.dto.requestdto.LoginRequestDto;
import todoapp.todoapp_develop.dto.requestdto.UserRequestDto;
import todoapp.todoapp_develop.dto.responsedto.LoginResponseDto;
import todoapp.todoapp_develop.dto.responsedto.UserResponseDto;
import todoapp.todoapp_develop.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    // 속성
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<User> loginUser = userRepository.findByEmail(loginRequestDto.getEmail());
        User user = loginUser.orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다."));
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
        }

        return LoginResponseDto.builder()
                .message("로그인 성공")
                .username(user.getUsername())
                .build();
    }

    // 유저 생성
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 유저명입니다.");
        }
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());

        User savedUser = userRepository.save(user);
        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    // 전체 유저 조회
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(User::userResponseDto)
                .collect(Collectors.toList());
    }

    // 선택 유저 조회
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return User.userResponseDto(user);
    }

    // 유저 수정
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());

        User updatedUser = userRepository.save(user);
        return User.userResponseDto(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        }
        userRepository.deleteById(id);
    }

}
