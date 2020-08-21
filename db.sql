# 캐릭터SET 설정
SET NAMES utf8mb4;

# DB 생성
DROP DATABASE IF EXISTS BD;
CREATE DATABASE BD;
USE BD;

# 카테고리 테이블 생성
DROP TABLE IF EXISTS cateItem;
CREATE TABLE cateItem (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE
);

# 카테고리 추가
INSERT INTO cateItem SET regDate = NOW(), `name` = 'IT/일반';
INSERT INTO cateItem SET regDate = NOW(), `name` = 'IT/알고리즘';
INSERT INTO cateItem SET regDate = NOW(), `name` = 'IT/프론트엔드';
INSERT INTO cateItem SET regDate = NOW(), `name` = 'IT/백엔드';
INSERT INTO cateItem SET regDate = NOW(), `name` = '디자인/피그마';
INSERT INTO cateItem SET regDate = NOW(), `name` = '일상/일반';

# 게시물 테이블 생성
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` TEXT NOT NULL
);

# 1번글 생성
INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 6,
displayStatus = 1,
title = '블로그를 시작합니다.',
`body` = ''

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
title = CONCAT('제목_', RAND()),
`body` = CONCAT('내용_', RAND());

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 2,
displayStatus = 1,
title = CONCAT('제목_', RAND()),
`body` = CONCAT('내용_', RAND());

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 3,
displayStatus = 1,
title = CONCAT('제목_', RAND()),
`body` = CONCAT('내용_', RAND());

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 4,
displayStatus = 1,
title = CONCAT('제목_', RAND()),
`body` = CONCAT('내용_', RAND());

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 5,
displayStatus = 1,
title = CONCAT('제목_', RAND()),
`body` = CONCAT('내용_', RAND());

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 6,
displayStatus = 1,
title = CONCAT('제목_', RAND()),
`body` = CONCAT('내용_', RAND());

# 조회수 칼럼 추가
ALTER TABLE article ADD COLUMN hit INT(10) UNSIGNED NOT NULL AFTER `body`;

SELECT *
FROM article
WHERE id = 91;

# 회원 테이블 생성
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `loginId` CHAR(100) NOT NULL UNIQUE,
    `loginPw` CHAR(100) NOT NULL,
    `name` CHAR(100) NOT NULL, 
    `nickname` CHAR(100) NOT NULL UNIQUE,
    `email` CHAR(100) NOT NULL,
    `level` INT(1) UNSIGNED DEFAULT 0 NOT NULL
);

# 마스터 회원 생성
INSERT INTO `member` SET
regDate = NOW(),
updateDate = NOW(),
`loginId` = 'admin',
`loginPw` = 'admin',
`name` = 'admin',
`nickname` = 'admin',
`email` = 'admin@admin.com',
`level` = 10; 