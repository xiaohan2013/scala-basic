-- Spark format
INSERT OVERWRITE DIRECTORY '/tmp/destination'
    USING parquet
    OPTIONS (col1 1, col2 2, col3 'test')
    SELECT * FROM test_table;

INSERT OVERWRITE DIRECTORY
    USING parquet
    OPTIONS ('path' '/tmp/destination', col1 1, col2 2, col3 'test')
    SELECT * FROM test_table;

-- Hive format
INSERT OVERWRITE LOCAL DIRECTORY '/tmp/destination'
    STORED AS orc
    SELECT * FROM test_table;

INSERT OVERWRITE LOCAL DIRECTORY '/tmp/destination'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
    SELECT * FROM test_table;



-- INSERT [ INTO | OVERWRITE ] [ TABLE ] table_identifier [ partition_spec ] [ ( column_list ) ]  { VALUES ( { value | NULL } [ , ... ] ) [ , ( ... ) ] | query }
-- Insert Into Single Row Insert Using a VALUES Clause
CREATE TABLE students (name VARCHAR(64), address VARCHAR(64))
    USING PARQUET PARTITIONED BY (student_id INT);
INSERT INTO students VALUES
    ('Amy Smith', '123 Park Ave, San Jose', 111111);

-- Multi-Row Insert Using a VALUES Clause

INSERT INTO students VALUES
    ('Bob Brown', '456 Taylor St, Cupertino', 222222),
    ('Cathy Johnson', '789 Race Ave, Palo Alto', 333333);
-- Insert Using a SELECT Statement
INSERT INTO students PARTITION (student_id = 444444)
    SELECT name, address FROM persons WHERE name = "Dora Williams";

-- Assuming the visiting_students table has already been created and populated.
SELECT * FROM visiting_students;
-- Insert Using a TABLE Statement
INSERT INTO students TABLE visiting_students;
-- Insert Using a FROM Statement
INSERT INTO students
     FROM applicants SELECT name, address, id applicants WHERE qualified = true;
-- Insert Using a Typed Date Literal for a Partition Column Value
CREATE TABLE students (name STRING, address  STRING) PARTITIONED BY (birthday DATE);
INSERT INTO students PARTITION (birthday = date'2019-01-02')
    VALUES ('Amy Smith', '123 Park Ave, San Jose');

-- Insert with a column list
INSERT INTO students (address, name, student_id) VALUES
    ('Hangzhou, China', 'Kent Yao', 11215016);

-- Insert with both a partition spec and a column list
INSERT INTO students PARTITION (student_id = 11215017) (address, name) VALUES
    ('Hangzhou, China', 'Kent Yao Jr.');

-------------------------------------------------------------
-- Insert Overwrite
-- Insert Using a VALUES Clause
SELECT * FROM students;
INSERT OVERWRITE students VALUES
    ('Ashua Hill', '456 Erica Ct, Cupertino', 111111),
    ('Brian Reed', '723 Kern Ave, Palo Alto', 222222);

-- Insert Using a SELECT Statement
INSERT OVERWRITE students PARTITION (student_id = 222222)
    SELECT name, address FROM persons WHERE name = "Dora Williams";

-- Insert Using a TABLE Statement
INSERT OVERWRITE students TABLE visiting_students;

-- Insert Using a FROM Statement
INSERT OVERWRITE students
    FROM applicants SELECT name, address, id applicants WHERE qualified = true;

-- Insert Using a Typed Date Literal for a Partition Column Value
CREATE TABLE students (name STRING, address  STRING) PARTITIONED BY (birthday DATE);

INSERT INTO students PARTITION (birthday = date'2019-01-02')
    VALUES ('Amy Smith', '123 Park Ave, San Jose');
INSERT INTO students PARTITION (birthday = date'2019-01-02')
    VALUES('Jason Wang', '908 Bird St, Saratoga');

-- Insert with a column list
INSERT OVERWRITE students (address, name, student_id) VALUES
    ('Hangzhou, China', 'Kent Yao', 11215016);

-- Insert with both a partition spec and a column list
INSERT OVERWRITE students PARTITION (student_id = 11215016) (address, name) VALUES
    ('Hangzhou, China', 'Kent Yao Jr.');

-- LOAD
-- LOAD DATA [ LOCAL ] INPATH path [ OVERWRITE ] INTO TABLE table_identifier [ partition_spec ]
-- Example without partition specification.
-- Assuming the students table has already been created and populated.
SELECT * FROM students;
+---------+----------------------+----------+
|     name|               address|student_id|
+---------+----------------------+----------+
|Amy Smith|123 Park Ave, San Jose|    111111|
+---------+----------------------+----------+

CREATE TABLE test_load (name VARCHAR(64), address VARCHAR(64), student_id INT) USING HIVE;

-- Assuming the students table is in '/user/hive/warehouse/'
LOAD DATA LOCAL INPATH '/user/hive/warehouse/students' OVERWRITE INTO TABLE test_load;

SELECT * FROM test_load;
+---------+----------------------+----------+
|     name|               address|student_id|
+---------+----------------------+----------+
|Amy Smith|123 Park Ave, San Jose|    111111|
+---------+----------------------+----------+

-- Example with partition specification.
CREATE TABLE test_partition (c1 INT, c2 INT, c3 INT) PARTITIONED BY (c2, c3);

INSERT INTO test_partition PARTITION (c2 = 2, c3 = 3) VALUES (1);

INSERT INTO test_partition PARTITION (c2 = 5, c3 = 6) VALUES (4);

INSERT INTO test_partition PARTITION (c2 = 8, c3 = 9) VALUES (7);

SELECT * FROM test_partition;
+---+---+---+
| c1| c2| c3|
+---+---+---+
|  1|  2|  3|
|  4|  5|  6|
|  7|  8|  9|
+---+---+---+

CREATE TABLE test_load_partition (c1 INT, c2 INT, c3 INT) USING HIVE PARTITIONED BY (c2, c3);

-- Assuming the test_partition table is in '/user/hive/warehouse/'
LOAD DATA LOCAL INPATH '/user/hive/warehouse/test_partition/c2=2/c3=3'
    OVERWRITE INTO TABLE test_load_partition PARTITION (c2=2, c3=3);

SELECT * FROM test_load_partition;
+---+---+---+
| c1| c2| c3|
+---+---+---+
|  1|  2|  3|
+---+---+---+






















