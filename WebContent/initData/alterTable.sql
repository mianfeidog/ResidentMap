--任务表 增加 联系人电话字段
ALTER TABLE task ADD contact_phone VARCHAR(25);
--更改任务表  字段为TEXT类型
ALTER TABLE task MODIFY area_edge_point TEXT;
--任务表增加字段预设面积
ALTER TABLE task ADD preselect_area FLOAT DEFAULT 0;

--删除任务id字段
ALTER TABLE task_vehicle_gps DROP COLUMN task_id;
--删除 飞行器id字段
ALTER TABLE task_vehicle_gps DROP COLUMN air_vehicle_id;
--添加飞行器绑定额GPSid字段
ALTER TABLE task_vehicle_gps ADD binded_gps_id VARCHAR(255);
--任务表ownerID  去掉数据库非空约束，  因为 发布任务时，并不知道 执行团队
task_excutor_id
--飞行器表加字段is_removed  表示是否删除
ALTER TABLE air_vehicle ADD is_removed BOOLEAN DEFAULT FALSE;
--去掉飞行器ownerid  不能为空的数据库约束
ALTER TABLE air_vehicle MODIFY owner_id BIGINT(20);

