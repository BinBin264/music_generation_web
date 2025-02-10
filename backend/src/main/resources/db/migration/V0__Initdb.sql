-- Tạo CSDL nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS PBL6;

-- Sử dụng CSDL
USE PBL6;

-- Bảng users không có khóa ngoại nên tạo trước
CREATE TABLE users
(
    user_id       BINARY(16)   NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime     NULL,
    modified_date datetime     NULL,
    modified_by   VARCHAR(255) NULL,
    user_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    full_name     VARCHAR(255) NOT NULL,
    user_role     VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT uc_users_email UNIQUE (email),
    CONSTRAINT uc_users_user_name UNIQUE (user_name)
);

-- Bảng categories không có khóa ngoại, tạo tiếp theo
CREATE TABLE categories
(
    category_id   BIGINT AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (category_id)
);

-- Bảng musics có khóa ngoại tới users và categories
CREATE TABLE musics
(
    music_id     BIGINT AUTO_INCREMENT NOT NULL,
    title        VARCHAR(255)          NOT NULL,
    composer_id  BINARY(16)            NULL,
    full_url     VARCHAR(255)          NOT NULL,
    price        DECIMAL(10, 2)        NOT NULL,
    is_purchased BIT(1)                NOT NULL,
    category_id  BIGINT                NULL,
    CONSTRAINT pk_musics PRIMARY KEY (music_id),
    CONSTRAINT FK_MUSICS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (category_id),
    CONSTRAINT FK_MUSICS_ON_COMPOSER FOREIGN KEY (composer_id) REFERENCES users (user_id)
);

