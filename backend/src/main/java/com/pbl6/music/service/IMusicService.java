package com.pbl6.music.service;

import com.pbl6.music.dto.request.MusicRequestDTO;
import com.pbl6.music.dto.request.UserRequestDTO;
import com.pbl6.music.dto.response.MusicResponseDTO;
import com.pbl6.music.dto.response.UserResponseDTO;
import com.pbl6.music.util.PageResponse;
import org.springframework.stereotype.Service;


public interface IMusicService {

    PageResponse<?> getAll(int page, int pageSize);
    MusicResponseDTO getById(Long id);

    PageResponse<?> getMusicByComposer(String composerName,int page, int pageSize);
    MusicResponseDTO createMusic(String composerUserName, Long categoryId);
}
