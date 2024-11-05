// 테이블 생성 쿼리
CREATE TABLE IF NOT EXISTS schedules{
    id INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) NOT NULL.
    todo varchar(255) NOT NULL,
    password varchar(100) NOT NULL,
    createddate DATE NOT NULL DEFAULT NOW(),
    modidate DATE NOT NULL DEFAULT NOW()
    }

// 일정 생성 쿼리
INSERT INTO schedules (todo, username, password,createddate, modidtae)
VALUES (?, ?, ?, NOW(), NOW());

//선택 일정 조회 쿼리
SELECT * FROM schedules WHERE id = ?;

//전체 일정 조회 쿼리
SELECT * FROM schedules
WHERE (modidate = ? OR ? IS NULL)
AND (username = ? OR ? IS NULL);

//일정 수정 쿼리
UPDATE schedules
SET todo = CASE WHEN ? IS NOT NULL THEN ? ELSE todo END,
    username = CASE WHEN ? IS NOT NULL THEN ? ELSE username END,
    modidate = NOW()
WHERE id = ? AND password = ?;

//일정 삭제 쿼리
DELETE FROM schedules
WHERE id = ? AND password = ?;