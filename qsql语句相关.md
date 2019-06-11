## qsql语句相关

```
java连接
https://www.yiibai.com/postgresql/postgresql_java.html 
CREATE TABLE public.student2
(
  id integer NOT NULL,
  name character(100),
  subjects character(1),
  CONSTRAINT student2_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.student2
  OWNER TO postgres;
COMMENT ON TABLE public.student2
  IS '这是一个学生信息表2';
#建立表
CREATE TABLE public.employees
(
  id integer NOT NULL,
  name text NOT NULL,
	age INTEGER NOT NULL,
	address character(50),
	salary real,
  CONSTRAINT employees_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.employees
  OWNER TO postgres;

#插入数据 单条
INSERT INTO "public".employees(id,name,age,address,salary) VALUES (7, 'Maxsu', 26, '海口市人民大道1268号', 990);
#插入数据 多条
INSERT INTO "public".employees(ID, NAME, AGE, ADDRESS, SALARY)  
VALUES
 (1, 'Maxsu', 25, '海口市人民大道2880号', 109990.00 ), 
(2, 'minsu', 25, '广州中山大道 ', 125000.00 ), 
(3, '李洋', 21, '北京市朝阳区', 185000.00),   
(4, 'Manisha', 24, 'Mumbai', 65000.00), 
(5, 'Larry', 21, 'Paris', 85000.00);

INSERT INTO EMPLOYEES VALUES (6, '李洋', 24, '深圳市福田区中山路', 135000);  
INSERT INTO EMPLOYEES VALUES (7, 'Manisha', 19, 'Noida', 125000);  
INSERT INTO EMPLOYEES VALUES (8, 'Larry', 45, 'Texas', 165000);

-- Table: public.department

-- DROP TABLE public.department;

CREATE TABLE public.department
(
  id integer,
  dept text,
  fac_id integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.department
  OWNER TO postgres;
-- 插入数据
INSERT INTO department VALUES(1,'IT', 1);
INSERT INTO department VALUES(2,'Engineering', 2);
INSERT INTO department VALUES(3,'HR', 7);
INSERT INTO department VALUES(10,'Market', 10);


#查询表数据
SELECT * FROM "public".employees;
SELECT id, name FROM "public".employees;

#更新比较数据
UPDATE "public".employees SET age=29, salary=9800 WHERE id=1;

#删除表数据
DELETE FROM "public".employees WHERE id=1;
DELETE FROM "public".employees;

#order by 排序
SELECT * FROM "public".employees ORDER BY age asc;
SELECT * FROM "public".employees ORDER BY name desc;
SELECT * FROM "public".employees ORDER BY name, address ASC;

#分组 GROUP BY
SELECT name, SUM(salary) FROM "public".employees GROUP BY name;

#having 与 GROUP BY 组合使用
SELECT NAME, COUNT(NAME) FROM "public".employees GROUP BY NAME HAVING COUNT (NAME) > 1;

#条件查询
SELECT * FROM employees WHERE salary > 120000 AND id <= 4;
SELECT * FROM employees WHERE NAME = 'minsu' OR address = 'Noida';
SELECT * FROM employees WHERE (NAME = 'Manisha' AND address = 'Noida') OR (id >= 8);
#NOT条件与WHERE子句一起使用以否定查询中的条件
SELECT * FROM employees WHERE address IS NOT NULL;


#LIKE条件
SELECT * FROM employees WHERE NAME LIKE 'Ma%';
SELECT * FROM employees WHERE NAME LIKE '%su';
SELECT * FROM employees WHERE address LIKE '%大道%';

#IN条件
SELECT * FROM employees WHERE age IN(19,21);

#NOT IN
SELECT * FROM employees WHERE age NOT IN(21,24);#查询那些年龄不是21和24的所有记录
SELECT * FROM employees WHERE NAME NOT IN('Maxsu', 'minsu');

#BETWEEN条件与WHERE子句一起使用
SELECT * FROM employees WHERE age BETWEEN 24 AND 27;

#内连接(INNER JOIN)
SELECT e.id, e.name, d.dept FROM employees e INNER JOIN department d ON e.id = d.id;
#左外连接
SELECT e.id, e.name, d.dept FROM employees e LEFT JOIN department d ON e.id = d.id;
#右外连接
SELECT e.id, e.name, d.dept from employees e RIGHT JOIN department d on e.id = d.id;
#全外连接 全外连接从左表和左表中返回所有行
SELECT e.id, e.name, d.dept FROM employees e FULL OUTER JOIN department d ON e.id = d.id;
#交叉连接
SELECT NAME, DEPT FROM EMPLOYEES  CROSS JOIN DEPARTMENT;
```

