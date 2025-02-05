package com.pbl6.music.controller;

import com.pbl6.music.dto.response.MusicResponseDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.service.IMusicService;
import com.pbl6.music.service.IUserService;
import com.pbl6.music.exception.ErrorCode;
import com.pbl6.music.util.ResponseData;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UserController {

    IUserService userService;

    IMusicService musicService;
    @GetMapping("/musics/my-musics")
    public ResponseData<?> getMusicByComposer(@RequestParam String composerUserName,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(musicService.getMusicByComposer(composerUserName, page, pageSize))
                .build();
    }

    @PostMapping("/musics/create-music")
    public ResponseEntity<ResponseData<?>> createMusic(@RequestBody Map<String, String> body) {
        // Gọi service để thêm user mới
        String composerUserName = body.get("composerUserName");
        Long categoryId = Long.parseLong(body.get("categoryId"));
        MusicResponseDTO newMusic = musicService.createMusic(composerUserName, categoryId);

        return  ResponseEntity.ok(ResponseData.builder()
                .code(ErrorCode.ADD_SUCCESSFUL.getCode())
                .message(ErrorCode.ADD_SUCCESSFUL.getMessage())
                .data(newMusic)
                .build());
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

    @GetMapping("/{email}")
    public ResponseEntity<ResponseData<?>> getUserByEmail(@PathVariable("email") String email) {
        String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
        Optional<UserResponseDTO> userOptional = userService.getUserByEmail(decodedEmail);
            // Trả về dữ liệu user
            return ResponseEntity.ok(
                ResponseData.builder()
                    .status(HttpStatus.OK.value())
                    .code(ErrorCode.GET_SUCCESSFUL.getCode())
                    .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                    .data(userOptional.get()) // Lấy giá trị User từ Optional
                    .build()
            );
    }

}
