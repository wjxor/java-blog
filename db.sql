DROP DATABASE IF EXISTS blog;
CREATE DATABASE blog;
USE blog;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(200),
    `body` LONGTEXT NOT NULL
);




# 작업 8 DB
# 캐릭터SET 설정
SET NAMES utf8mb4;

# DB 생성
DROP DATABASE IF EXISTS site41;
CREATE DATABASE site41;
USE site41;

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
INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
cateItemId = 6,
displayStatus = 1,
title = '블로그를 시작합니다.',
`body` = ''

# 체크
SELECT *
FROM article;



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
cateItemId =3,
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