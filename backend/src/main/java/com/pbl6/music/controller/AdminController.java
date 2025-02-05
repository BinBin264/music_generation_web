package com.pbl6.music.controller;

import com.pbl6.music.dto.request.UserRequestDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.service.IMusicService;
import com.pbl6.music.service.IUserService;
import com.pbl6.music.exception.ErrorCode;
import com.pbl6.music.util.ResponseData;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AdminController {

    IUserService userService;
    @GetMapping("/users")
    public ResponseData<?> getAllUser(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(userService.getAllUser(page, pageSize))
                .build();
    }

    @GetMapping("/users/{id}")
    public ResponseData<?> getUserById(@PathVariable UUID id) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(userService.getUserById(id))
                .build();
    }

    @GetMapping("/users/search")
    public ResponseData<?> searchUserByName(@RequestParam String userName) {
        List<UserResponseDTO> users = userService.getListUserByUsername(userName); // Giả sử bạn đã có phương thức trong userService
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(users)
                .build();
    }


    @PostMapping("/users/create-user")
    public ResponseEntity<ResponseData<?>> addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        // Gọi service để thêm user mới
        UserResponseDTO newUser = userService.addUser(userRequestDTO);

        return  ResponseEntity.ok(ResponseData.builder()
                .code(ErrorCode.ADD_SUCCESSFUL.getCode())
                .message(ErrorCode.ADD_SUCCESSFUL.getMessage())
                .data(newUser)
                .build());
    }


    @PutMapping("/users/{id}")
    public ResponseData<?> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO request) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.UPDATE_SUCCESSFUL.getCode())
                .message(ErrorCode.UPDATE_SUCCESSFUL.getMessage())
                .data(userService.updateUser( id, request))
                .build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseData<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.DELETE_SUCCESSFUL.getCode())
                .message(ErrorCode.DELETE_SUCCESSFUL.getMessage())
                .message("Delete user success")
                .build();
    }



    IMusicService musicService;
    @GetMapping("/musics")
    public ResponseData<?> getAllMusic(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(musicService.getAll(page, pageSize))
                .build();
    }
    @GetMapping("/musics/{id}")
    public ResponseData<?> getById(@PathVariable Long id) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(musicService.getById(id))
                .build();
    }
}
