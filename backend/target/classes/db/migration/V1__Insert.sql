INSERT INTO users (user_id, created_by, created_date, modified_date, modified_by, user_name, email, password, full_name, user_role, phone_number)
VALUES
(UUID_TO_BIN(UUID()), 'system', NOW(), NOW(), 'system', 'SONTung-MTP', 'mtp@example.com', 'password123', 'Nguyen Thanh Tung', 'USER', '0123456789'),
(UUID_TO_BIN(UUID()), 'system', NOW(), NOW(), 'system', 'jane_smith', 'jane.smith@example.com', 'password456', 'Jane Smith', 'ADMIN', '0987654321');

INSERT INTO categories (category_id,category_name)
VALUES
(1,'Monotone'),
(2,'Multitone');

