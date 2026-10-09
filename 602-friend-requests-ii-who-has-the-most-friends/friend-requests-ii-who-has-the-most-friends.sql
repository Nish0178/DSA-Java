# Write your MySQL query statement below

WITH Friends AS (
    SELECT requester_id AS id FROM RequestAccepted
    UNION ALL
    SELECT accepter_id AS id FROM RequestAccepted
),
FriendCounts AS (
    SELECT id, COUNT(*) AS num
    FROM Friends
    GROUP BY id
)
SELECT id, num
FROM FriendCounts
WHERE num = (SELECT MAX(num) FROM FriendCounts);
