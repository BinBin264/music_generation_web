package com.pbl6.music.service.impl;

import com.pbl6.music.dto.response.MusicResponseDTO;
import com.pbl6.music.entity.CategoryEntity;
import com.pbl6.music.entity.MusicEntity;
import com.pbl6.music.entity.UserEntity;
import com.pbl6.music.exception.ApiException;
import com.pbl6.music.exception.ErrorCode;
import com.pbl6.music.mapper.MusicMapper;
import com.pbl6.music.repository.CategoryRepository;
import com.pbl6.music.repository.MusicRepository;
import com.pbl6.music.repository.UserRepository;
import com.pbl6.music.util.PageResponse;
import com.pbl6.music.service.IMusicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MusicServiceImpl implements IMusicService {

     UserRepository userRepository;

     MusicRepository musicRepository;

     MusicMapper musicMapper;
    private final CategoryRepository categoryRepository;

    public PageResponse<?> getAll(int page, int pageSize) {
         Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
         Page<MusicResponseDTO> musics = musicRepository.findAllAvailableMusic(pageable);

         return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(musics.getTotalPages())
                .items(musics.getContent())
                .build();
    }

    public MusicResponseDTO getById(Long id) {
        MusicEntity musicEntity = musicRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.MUSIC_NOT_FOUND));
       return musicMapper.toResponseDTO(musicEntity);
    }

    public PageResponse<?> getMusicByComposer(String composerName, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);

        // Gọi đến repository với composerName và pageable
        Page<MusicResponseDTO> musics = musicRepository.findMusicByComposer(composerName, pageable);

        // Trả về đối tượng PageResponse tương tự như trong hàm getAll
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(musics.getTotalPages())
                .items(musics.getContent())
                .build();
    }

        public MusicResponseDTO createMusic(String composerUserName, Long categoryId) {
            MusicEntity musicEntity = new MusicEntity();
            musicEntity.setTitle("Clone Title");
            System.out.println(composerUserName);
            UserEntity composer = userRepository.findUserByUsername(composerUserName).orElseThrow(() -> new RuntimeException("Composer not found"));
            musicEntity.setComposer(composer);
            musicEntity.setPrice(BigDecimal.valueOf(10000));  // Giá trị tạm thời
            musicEntity.setPurchased(false);  // Giá trị mặc định
            musicEntity.setFullUrl("http://example.com/xxx");
            CategoryEntity category = categoryRepository.findByCategoryId(categoryId);
            musicEntity.setCategory(category);
            musicEntity = musicRepository.save(musicEntity);
            return musicMapper.toResponseDTO(musicEntity);
    }


}
