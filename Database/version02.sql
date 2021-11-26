USE QLC19
GO

-- 1.2.1 Hiển thị danh sách người liên quan Covid-19
SELECT *
FROM dbo.PROFILE
WHERE GENDER <> N'N'

-- 1.2.1 Xem chi tiết thông tin của người liên quan bao gồm danh sách người liên đới.
SELECT p1.*, p2.FULLNAME 'Danh sách người liên đới'
FROM dbo.PROFILE p1 JOIN dbo.RELATE r ON p1.ID = r.USERNAME1 JOIN dbo.PROFILE p2 ON r.USERNAME2 = p2.ID
WHERE p1.GENDER <> N'N'

UNION

SELECT p1.*, p2.FULLNAME 'Danh sách người liên đới'
FROM dbo.PROFILE p1 JOIN dbo.RELATE r ON p1.ID = r.USERNAME2 JOIN dbo.PROFILE p2 ON r.USERNAME1 = p2.ID
WHERE p1.GENDER <> N'N'

-- 1.2.1 Sắp xếp theo tên
SELECT *
FROM dbo.PROFILE
WHERE GENDER <> N'N'
ORDER BY FULLNAME

-- 1.2.1 Sắp xếp theo năm sinh
SELECT *
FROM dbo.PROFILE
WHERE GENDER <> N'N'
ORDER BY DOB

-- 1.2.1 Sắp xếp theo trạng thái hiện tại
SELECT *
FROM dbo.PROFILE p JOIN F_HISTORY f ON p.ID = f.USER_ID
WHERE GENDER <> N'N' AND f.F_DATE >= ALL (
										SELECT f1.F_DATE
										FROM F_HISTORY f1
										WHERE p.ID = f1.USER_ID
										)

-- 1.2.1 Sắp xếp theo nơi cách ly
SELECT *
FROM dbo.PROFILE
WHERE GENDER <> N'N'
ORDER BY ID_QUARATINE

-- 1.2.4/1.4.2 Hiển thị danh sách gói nhu yếu phẩm
SELECT *
FROM dbo.NECESSITIES

-- 1.2.5 Thống kê số lượng người ở từng trạng thái theo thời gian
SELECT F_DATE 'Ngày', F_KIND 'Trạng thái', COUNT(*) 'Số lượng người'
FROM dbo.F_HISTORY
GROUP BY F_DATE, F_KIND
ORDER BY F_DATE

-- 1.2.5 Thống kê tiêu thụ các gói nhu yếu phẩm
SELECT n.NAME 'Tên gói', SUM(c.QUANTITY) 'Số lượng tiêu thụ'
FROM dbo.NECESSITIES n LEFT JOIN dbo.CONSUME c ON n.ID_NECESSITIES = c.ID_NECESSITIES
GROUP BY n.NAME

-- 1.3.3 Địa điểm điều trị / cách ly chỉ cần thông tin Tên, Sức chứa và Số lượng tiếp nhận hiện tại
SELECT q.NAME 'Tên', q.CAPABLE 'Sức chứa', COUNT(p.ID) 'Số lượng tiếp nhận hiện tại'
FROM dbo.QUARATINE q LEFT JOIN dbo.PROFILE p ON q.ID_QUARATINE = p.ID_QUARATINE
GROUP BY q.NAME, q.CAPABLE

-- 1.4.1 Các thông tin cá nhân cơ bản
SELECT *
FROM dbo.PROFILE
WHERE ID = '272665958'

-- 1.4.1 Lịch sử được quản lý
SELECT *
FROM dbo.F_HISTORY
WHERE USER_ID = '272665958'
ORDER BY F_DATE ASC

-- 1.4.1 Lịch sử tiêu thụ gói Nhu yếu phẩm
SELECT b.DATE, n.NAME, c.QUANTITY
FROM dbo.BILL b JOIN dbo.CONSUME c ON b.ID_BILL = c.ID_BILL JOIN NECESSITIES n ON c.ID_NECESSITIES = n.ID_NECESSITIES
WHERE b.ID_USER = '189393343'

-- 1.4.1 Xem dư nợ
SELECT *
FROM dbo.DEBT
WHERE USER_ID = '189393343'

-- 1.4.1 Xem lịch sử thanh toán
SELECT *
FROM dbo.PAYMENT_HISTORY
WHERE USER_ID = '189393343'