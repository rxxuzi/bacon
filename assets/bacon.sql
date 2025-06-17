-- ===========================
-- bacon SNS Database Schema
-- ===========================

-- データベース作成
CREATE DATABASE IF NOT EXISTS bacon_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE bacon_db;

-- ===========================
-- Users Table
-- ===========================
CREATE TABLE users (
                       id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       user_id VARCHAR(50) NOT NULL UNIQUE,
                       username VARCHAR(100) NOT NULL,
                       bio TEXT,
                       color CHAR(7) DEFAULT '#6B73FF',
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       PRIMARY KEY (id),
                       INDEX idx_user_id (user_id),
                       INDEX idx_email (email),
                       INDEX idx_created_at (created_at)
) ENGINE=InnoDB;

-- ===========================
-- Posts Table
-- ===========================
CREATE TABLE posts (
                       id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       user_id BIGINT UNSIGNED NOT NULL,
                       content TEXT NOT NULL,
                       visibility ENUM('public','private') DEFAULT 'public',
                       reply_count INT UNSIGNED DEFAULT 0,
                       is_deleted BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id),
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       INDEX idx_user_id (user_id),
                       INDEX idx_visibility (visibility),
                       INDEX idx_created_at (created_at)
) ENGINE=InnoDB;

-- ===========================
-- Messages Table
-- ===========================
CREATE TABLE messages (
                          id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          sender_id BIGINT UNSIGNED NOT NULL,
                          receiver_id BIGINT UNSIGNED NOT NULL,
                          content TEXT NOT NULL,
                          is_read BOOLEAN DEFAULT FALSE,
                          visibility ENUM('normal', 'hidden') DEFAULT 'normal',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (id),
                          FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE,
                          INDEX idx_sender_id (sender_id),
                          INDEX idx_receiver_id (receiver_id),
                          INDEX idx_conversation (sender_id, receiver_id),
                          INDEX idx_created_at (created_at),
                          INDEX idx_is_read (is_read)
) ENGINE=InnoDB;

-- ===========================
-- Bookmarks Table
-- ===========================
CREATE TABLE bookmarks (
                           id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                           user_id BIGINT UNSIGNED NOT NULL,
                           post_id BIGINT UNSIGNED NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (id),
                           UNIQUE KEY unique_bookmark (user_id, post_id),
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
                           INDEX idx_user_id (user_id),
                           INDEX idx_post_id (post_id),
                           INDEX idx_created_at (created_at)
) ENGINE=InnoDB;


