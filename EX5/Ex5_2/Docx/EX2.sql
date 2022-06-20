SELECT *
FROM ghtk_db.users u 
WHERE u.user_name = "ghtk";

SELECT u.full_name ,u.age, u.province  
FROM ghtk_db.users u 
WHERE u.province LIKE '%Hồ Chí Minh%'
ORDER BY u.age DESC 
LIMIT 10;