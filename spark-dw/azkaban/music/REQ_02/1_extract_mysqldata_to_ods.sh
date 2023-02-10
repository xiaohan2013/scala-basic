#!/bin/bash
ssh hadoop@node03 > /tmp/logs/music_project/machine-info.log 2>&1 <<aabbcc
hostname
source /etc/profile

# ycak
## machine_baseinfo 	==>> 	TO_YCAK_MAC_D
sqoop import --connect jdbc:mysql://node01:3306/ycak?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table machine_baseinfo --target-dir /user/hive/warehouse/music.db/TO_YCAK_MAC_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'
## machine_local_info 	==>> 	TO_YCAK_MAC_LOC_D
sqoop import --connect jdbc:mysql://node01:3306/ycak?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table machine_local_info --target-dir /user/hive/warehouse/music.db/TO_YCAK_MAC_LOC_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'

# ycbk
## machine_admin_map	==>> 	TO_YCBK_MAC_ADMIN_MAP_D
sqoop import --connect jdbc:mysql://node01:3306/ycbk?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table machine_admin_map --target-dir /user/hive/warehouse/music.db/TO_YCBK_MAC_ADMIN_MAP_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'
## machine_store_map	==>> 	TO_YCBK_MAC_STORE_MAP_D
sqoop import --connect jdbc:mysql://node01:3306/ycbk?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table machine_store_map --target-dir /user/hive/warehouse/music.db/TO_YCBK_MAC_STORE_MAP_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'
## machine_store_info	==>> 	TO_YCBK_STORE_D
sqoop import --connect jdbc:mysql://node01:3306/ycbk?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table machine_store_info --target-dir /user/hive/warehouse/music.db/TO_YCBK_STORE_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'
## province_info		==>> 	TO_YCBK_PRVC_D
sqoop import --connect jdbc:mysql://node01:3306/ycbk?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table province_info --target-dir /user/hive/warehouse/music.db/TO_YCBK_PRVC_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'
## city_info	==>> 	TO_YCBK_CITY_D
sqoop import --connect jdbc:mysql://node01:3306/ycbk?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table city_info --target-dir /user/hive/warehouse/music.db/TO_YCBK_CITY_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'
## area_info	==>> 	TO_YCBK_AREA_D
sqoop import --connect jdbc:mysql://node01:3306/ycbk?dontTrackOpenResources=true\&defaultFetchSize=10000\&useCursorFetch=true\&useUnicode=yes\&characterEncoding=utf8 --username root --password 123456 --table area_info --target-dir /user/hive/warehouse/music.db/TO_YCBK_AREA_D/ --delete-target-dir --num-mappers 1 --fields-terminated-by '\t'

aabbcc

echo "all done!"
