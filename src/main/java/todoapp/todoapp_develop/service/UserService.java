package todoapp.todoapp_develop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todoapp.todoapp_develop.domain.User;
import todoapp.todoapp_develop.dto.UserRequestDto;
import todoapp.todoapp_develop.dto.UserResponseDto;
import todoapp.todoapp_develop.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    // 속성
    private final UserRepository userRepository;

    // 회원가입
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 유저명입니다.");
        }
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        User savedUser = userRepository.save(user);
        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    // 유저 생성
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 유저명입니다.");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());

        User savedUser = userRepository.save(user);
        return User.userResponseDto(savedUser);
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
        user.setEmail(requestDto.getEmail());

        User updatedUser = userRepository.save(user);
        return User.userResponseDto(updatedUser);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        }
        userRepository.deleteById(id);
    }

}
