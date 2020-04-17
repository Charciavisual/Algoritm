-- 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회
-- 입양건이 없는 경우 0을 표시해야한다.
SELECT T1.hour as HOUR, ifnull(T2.count, 0) as COUNT
from (
    SELECT 0 as hour
    union select 1 union select 2 union select 3 union select 4 union select 5
    union select 6 union select 7 union select 8 union select 9 union select 10
    union select 11 union select 12 union select 13 union select 14 union select 15
    union select 15 union select 16 union select 17 union select 18 union select 19
    union select 20 union select 21 union select 22 union select 23) T1
left join (
    select hour(datetime) as hour, count(*) as count
    from animal_outs
    group by hour) as T2 on T2.hour = T1.hour