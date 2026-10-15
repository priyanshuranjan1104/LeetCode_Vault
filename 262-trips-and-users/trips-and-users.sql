# Write your MySQL query statement below
WITH not_banned as (
    SELECT users_id FROM users
    WHERE banned = 'No'
) 
SELECT
    request_at as Day,
    ROUND( SUM( CASE WHEN status LIKE 'cancelled%'
                     THEN 1.00
                     ELSE 0 END) / COUNT(*), 2)
    AS "Cancellation Rate"
FROM Trips
WHERE
    client_id IN (SELECT users_id FROM not_banned)
    AND driver_id IN (SELECT users_id FROM not_banned)
    AND request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY request_at