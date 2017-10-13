
//初始化区域
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('1', '安康市', '0');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('10', '汉阴县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('11', '旬阳县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('12', '平利县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('13', '紫阳县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('14', '白河县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('15', '镇坪县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('16', '岚皋县', '1');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('17', '宁陕县', '1');

INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('100', '城关镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('101', '蒿坪镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('102', '蒲溪镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('103', '平梁镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('104', '龙垭镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('105', '铁佛寺镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('106', '双河口镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('107', '上七镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('108', '酒店镇', '10');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('109', '汉阳镇', '10');


INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('1000', '新桃村', '100');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('1001', '新田村', '100');


INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('1010', '天紫村', '101');
INSERT INTO kechuang_map.area (id, NAME, pid) VALUES('1011', '东关村', '101');


--添加数据字典

--性别 类型 1
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('10', '男', '0', '1');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('11', '女', '1', '1');

--与户主关系 类型 2
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('20', '户主', '0', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('21', '丈夫', '1', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('22', '妻子', '2', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('23', '丈夫', '3', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('24', '儿子', '4', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('25', '女儿', '5', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('26', '孙子', '6', '2');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('27', '孙女', '7', '2');


--政治面貌  类型3
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('30', '党员', '0', '3');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('31', '预备党员', '1', '3');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('32', '群众', '2', '3');

--文化程度  类型4
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('40', '博士', '0', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('41', '硕士', '1', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('42', '大学', '2', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('43', '大专', '3', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('44', '中专', '4', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('45', '高中', '5', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('46', '初中', '6', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('47', '小学', '7', '4');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('48', '文盲', '8', '4');

--健康状况 类型5
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('50', '健康', '0', '5');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('51', '疾病', '1', '5');

--务工状况 类型6
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('60', '县内务工', '0', '6');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('61', '县外省内务工', '1', '6');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('62', '省外务工', '2', '6');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('63', '其他', '3', '6');

--贫困户类别 类型7
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('70', '一般', '0', '7');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('71', '低保', '1', '7');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('70', '五保', '2', '7');

--主要致贫原因 类型8

INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('80', '因病返贫', '0', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('81', '因残致贫', '1', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('82', '子女上学', '2', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('83', '自然灾害', '3', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('84', '文化低下', '4', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('85', '资源匮乏', '5', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('86', '缺少技术', '6', '8');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('87', '缺少资金', '7', '8');

--家庭生产经营状况  类型9
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('90', '种植业', '0', '9');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('91', '加工业', '1', '9');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('92', '服务业', '2', '9');
INSERT INTO kechuang_map.data_dictionary (id, NAME, VALUE, data_type) VALUES ('93', '其他', '3', '9');