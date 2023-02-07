SELECT * FROM test;
+--+----+
|id|name|
+--+----+
| 5|Alex|
| 8|Lucy|
| 2|Mary|
| 4|Fred|
| 1|Lisa|
| 9|Eric|
|10|Adam|
| 6|Mark|
| 7|Lily|
| 3|Evan|
+--+----+

-- 百分比：
SELECT * FROM test TABLESAMPLE (50 PERCENT);
+--+----+
|id|name|
+--+----+
| 5|Alex|
| 2|Mary|
| 4|Fred|
| 9|Eric|
|10|Adam|
| 3|Evan|
+--+----+

SELECT * FROM test TABLESAMPLE (5 ROWS);
+--+----+
|id|name|
+--+----+
| 5|Alex|
| 8|Lucy|
| 2|Mary|
| 4|Fred|
| 1|Lisa|
+--+----+

-- 分桶：10个中取4个
SELECT * FROM test TABLESAMPLE (BUCKET 4 OUT OF 10);
+--+----+
|id|name|
+--+----+
| 8|Lucy|
| 2|Mary|
| 9|Eric|
| 6|Mark|
+--+----+
