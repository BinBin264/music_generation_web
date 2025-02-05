package com.pbl6.music.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class MusicRequestDTO {

    Long musicId;

    String title;

    UUID composer_id;

    String fullUrl;

    BigDecimal price;

    boolean purchased;

    Long category_id;
}
