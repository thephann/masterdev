//1
SELECT AVG(r.score) as score_avg FROM `Result` r   

//2
SELECT (
(SELECT COUNT(*) 
FROM `Result` r  WHERE r.score < 4) / (SELECT COUNT(*) 
FROM `Result` r)) AS weak_score

SELECT (
(SELECT COUNT(*) FROM `Result` r 
WHERE r.score >= 4 AND r.score < 6) / (SELECT COUNT(*) FROM `Result` r)) AS pass_score

SELECT (
(SELECT COUNT(*) FROM `Result` r 
WHERE r.score >= 6 AND r.score < 8) / (SELECT COUNT(*) FROM `Result` r)) AS credit_score

SELECT (
(SELECT COUNT(*) FROM `Result` r 
WHERE r.score >= 8 AND r.score < 9) / (SELECT COUNT(*) FROM `Result` r)) AS good_score

SELECT (
(SELECT COUNT(*) FROM `Result` r 
WHERE r.score >= 9) / (SELECT COUNT(*) FROM `Result` r)) AS excellent_score

//3
SELECT c.course_name, AVG(r.score) AS avg_score
FROM Course c , `Result` r , Class c2  
WHERE c.id = c2.course_id AND c2.id = r.class_id 
GROUP BY c2.id  
ORDER BY avg_score DESC
LIMIT 1

//4
SELECT r.class_id,c.`year`  ,AVG(r.score) AS avg_score
FROM `Result` r , Class c 
WHERE r.class_id = c.id 
GROUP BY r.class_id 
ORDER BY avg_score DESC
LIMIT 1

//5
SELECT s.name ,AVG(r.score) 
FROM `Result` r , Student s  
WHERE r.student_id = s.id 
GROUP BY r.student_id 
ORDER BY AVG(r.score) DESC 
LIMIT 1

//6
SELECT c2.id , c2.course_name , COUNT(s.id) as stu_fail
FROM `Result` r ,Class c ,Course c2 ,Student s 
WHERE s.id = r.student_id AND r.class_id = c.id AND c.course_id = c2.id AND r.score < 4.0
GROUP BY c2.course_name 
ORDER BY stu_fail DESC 

//7
SELECT DISTINCT s.id ,s.name ,r.score 
FROM Student s , `Result` r 
WHERE s.id = r.student_id AND r.score  >= 4.0

//8 
SELECT c2.id , c2.course_name , COUNT(s.id) as stu_fail
FROM `Result` r ,Class c ,Course c2 ,Student s 
WHERE s.id = r.student_id AND r.class_id = c.id AND c.course_id = c2.id AND r.score < 4.0
GROUP BY c2.course_name 
ORDER BY stu_fail DESC  
LIMIT 10





