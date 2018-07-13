/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : coursechoose

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 13/07/2018 10:20:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_ccourse
-- ----------------------------
DROP TABLE IF EXISTS `c_ccourse`;
CREATE TABLE `c_ccourse`  (
  `stu_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `ccourse_mark` int(11) NULL DEFAULT 0,
  `ccourse_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`, `course_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `c_ccourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_ccourse_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `c_student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_ccourse
-- ----------------------------
INSERT INTO `c_ccourse` VALUES (1001, 1, 40, '2018-07-09 09:13:51');
INSERT INTO `c_ccourse` VALUES (1001, 2, 30, '2018-07-07 08:43:40');
INSERT INTO `c_ccourse` VALUES (1001, 3, 90, '2018-07-09 09:14:38');
INSERT INTO `c_ccourse` VALUES (1001, 5, 0, '2018-07-10 16:39:16');
INSERT INTO `c_ccourse` VALUES (1002, 1, 90, '2018-07-09 09:13:44');
INSERT INTO `c_ccourse` VALUES (1002, 3, 20, '2018-07-09 09:14:07');
INSERT INTO `c_ccourse` VALUES (1004, 1, 50, '2018-07-09 09:14:20');
INSERT INTO `c_ccourse` VALUES (1004, 3, 10, '2018-07-09 09:14:15');
INSERT INTO `c_ccourse` VALUES (1021, 1, 60, '2018-07-11 16:12:12');

-- ----------------------------
-- Table structure for c_class
-- ----------------------------
DROP TABLE IF EXISTS `c_class`;
CREATE TABLE `c_class`  (
  `class_id` int(2) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_maxnum` int(2) NOT NULL,
  `grade_id` int(2) NOT NULL,
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `grade_id`(`grade_id`) USING BTREE,
  CONSTRAINT `grade_id` FOREIGN KEY (`grade_id`) REFERENCES `c_grade` (`grade_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_class
-- ----------------------------
INSERT INTO `c_class` VALUES (1, '计科1705', 40, 1);
INSERT INTO `c_class` VALUES (2, '计科1706', 40, 1);
INSERT INTO `c_class` VALUES (3, '计科1605', 40, 2);
INSERT INTO `c_class` VALUES (4, '计科1606', 40, 2);
INSERT INTO `c_class` VALUES (5, '计科1505', 40, 3);
INSERT INTO `c_class` VALUES (6, '计科1506', 40, 3);
INSERT INTO `c_class` VALUES (7, '计科1405', 40, 4);
INSERT INTO `c_class` VALUES (8, '计科1406', 40, 4);

-- ----------------------------
-- Table structure for c_course
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course`  (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_credit` float NOT NULL,
  `course_info` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_begin_time` datetime(0) NOT NULL,
  `teach_id` int(11) NOT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `teach_id`(`teach_id`) USING BTREE,
  CONSTRAINT `c_course_ibfk_1` FOREIGN KEY (`teach_id`) REFERENCES `c_teacher` (`teach_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES (1, '操作系统', 4, '学习操作系统', '2018-07-10 12:38:58', 1);
INSERT INTO `c_course` VALUES (2, '脚本编程', 8, '学习linux', '2018-07-10 12:39:33', 1);
INSERT INTO `c_course` VALUES (3, 'java设计', 5, '学习java基础', '2018-07-10 12:40:09', 2);
INSERT INTO `c_course` VALUES (4, '高等数学', 4, '学习高等数学', '2018-07-10 12:40:30', 3);
INSERT INTO `c_course` VALUES (5, '数据结构与算法', 5, '很好的课程', '2018-07-10 15:20:00', 2);
INSERT INTO `c_course` VALUES (6, '组成原理', 5, '很有用的一门课程', '2018-07-10 09:15:33', 1);
INSERT INTO `c_course` VALUES (7, 'web开发', 6, '网页的设计', '2018-07-10 09:30:09', 3);
INSERT INTO `c_course` VALUES (8, 'C语言基础', 3, '很语言基础应用', '2018-07-10 10:02:12', 2);
INSERT INTO `c_course` VALUES (9, '计算机工程导论', 3, '计算机基础', '2018-07-10 11:05:45', 3);
INSERT INTO `c_course` VALUES (10, '人工智能', 4, '学习人工智能', '2018-03-07 09:55:44', 4);
INSERT INTO `c_course` VALUES (11, '计算机网络', 3, '学习计算机网络', '2018-07-10 15:48:21', 6);
INSERT INTO `c_course` VALUES (12, '离散数学', 4, '学习离散数学', '2018-03-07 16:21:48', 3);

-- ----------------------------
-- Table structure for c_grade
-- ----------------------------
DROP TABLE IF EXISTS `c_grade`;
CREATE TABLE `c_grade`  (
  `grade_id` int(2) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_grade
-- ----------------------------
INSERT INTO `c_grade` VALUES (1, '一年级');
INSERT INTO `c_grade` VALUES (2, '二年级');
INSERT INTO `c_grade` VALUES (3, '三年级');
INSERT INTO `c_grade` VALUES (4, '四年级');
INSERT INTO `c_grade` VALUES (5, '研一');
INSERT INTO `c_grade` VALUES (6, '研二');
INSERT INTO `c_grade` VALUES (7, '研三');
INSERT INTO `c_grade` VALUES (8, '大一');
INSERT INTO `c_grade` VALUES (9, '大二');
INSERT INTO `c_grade` VALUES (10, '大三');
INSERT INTO `c_grade` VALUES (11, '大四');

-- ----------------------------
-- Table structure for c_log
-- ----------------------------
DROP TABLE IF EXISTS `c_log`;
CREATE TABLE `c_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_time` datetime(0) NULL DEFAULT NULL,
  `login_user` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_log
-- ----------------------------
INSERT INTO `c_log` VALUES (1, '[admin]管理员登陆系统', '2018-07-10 11:19:17', 'admin');
INSERT INTO `c_log` VALUES (2, '[admin]管理员登陆系统', '2018-07-10 11:24:45', 'admin');
INSERT INTO `c_log` VALUES (3, '[admin]管理员登陆系统', '2018-07-10 15:38:56', 'admin');
INSERT INTO `c_log` VALUES (4, '[admin]管理员登陆系统', '2018-07-10 15:53:23', 'admin');
INSERT INTO `c_log` VALUES (5, '[admin]管理员登陆系统', '2018-07-10 16:38:56', 'admin');
INSERT INTO `c_log` VALUES (6, '[admin]管理员登陆系统', '2018-07-10 18:57:35', 'admin');
INSERT INTO `c_log` VALUES (7, '[1001]号学生登陆系统', '2018-07-10 19:00:26', '1001');
INSERT INTO `c_log` VALUES (8, '[admin]管理员登陆系统', '2018-07-10 19:01:53', 'admin');
INSERT INTO `c_log` VALUES (9, '[admin]管理员登陆系统', '2018-07-10 19:47:52', 'admin');
INSERT INTO `c_log` VALUES (10, '[1]号教师用户登陆系统', '2018-07-10 22:48:08', '1');
INSERT INTO `c_log` VALUES (11, '[admin]管理员登陆系统', '2018-07-10 22:53:34', 'admin');
INSERT INTO `c_log` VALUES (12, '[admin]管理员登陆系统', '2018-07-10 22:54:12', 'admin');
INSERT INTO `c_log` VALUES (13, '[admin]管理员登陆系统', '2018-07-10 22:56:54', 'admin');
INSERT INTO `c_log` VALUES (14, '[admin]管理员登陆系统', '2018-07-10 22:58:36', 'admin');
INSERT INTO `c_log` VALUES (15, '[admin]管理员登陆系统', '2018-07-10 23:00:10', 'admin');
INSERT INTO `c_log` VALUES (16, '[admin]管理员登陆系统', '2018-07-10 23:00:34', 'admin');
INSERT INTO `c_log` VALUES (17, '[admin]管理员登陆系统', '2018-07-10 23:02:29', 'admin');
INSERT INTO `c_log` VALUES (18, '[admin]管理员登陆系统', '2018-07-10 23:04:18', 'admin');
INSERT INTO `c_log` VALUES (19, '[admin]管理员登陆系统', '2018-07-10 23:14:18', 'admin');
INSERT INTO `c_log` VALUES (20, '[admin]管理员登陆系统', '2018-07-10 23:18:33', 'admin');
INSERT INTO `c_log` VALUES (21, '[admin]管理员登陆系统', '2018-07-10 23:20:00', 'admin');
INSERT INTO `c_log` VALUES (22, '[lw]管理员登陆系统', '2018-07-11 09:11:46', 'lw');
INSERT INTO `c_log` VALUES (23, '[admin]管理员登陆系统', '2018-07-11 09:15:58', 'admin');
INSERT INTO `c_log` VALUES (24, '[admin]管理员登陆系统', '2018-07-11 09:16:19', 'admin');
INSERT INTO `c_log` VALUES (25, '[1001]号学生登陆系统', '2018-07-11 09:17:20', '1001');
INSERT INTO `c_log` VALUES (26, '[admin]管理员登陆系统', '2018-07-11 09:19:43', 'admin');
INSERT INTO `c_log` VALUES (27, '[admin]管理员登陆系统', '2018-07-11 09:54:43', 'admin');
INSERT INTO `c_log` VALUES (28, '[1001]号学生登陆系统', '2018-07-11 09:56:27', '1001');
INSERT INTO `c_log` VALUES (29, '[admin]管理员登陆系统', '2018-07-11 10:50:09', 'admin');
INSERT INTO `c_log` VALUES (30, '[admin]管理员登陆系统', '2018-07-11 13:08:19', 'admin');
INSERT INTO `c_log` VALUES (31, '[admin]管理员登陆系统', '2018-07-11 16:03:13', 'admin');

-- ----------------------------
-- Table structure for c_student
-- ----------------------------
DROP TABLE IF EXISTS `c_student`;
CREATE TABLE `c_student`  (
  `stu_id` int(8) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `stu_sex` char(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `class_id` int(2) NOT NULL,
  `stu_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '1',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1023 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_student
-- ----------------------------
INSERT INTO `c_student` VALUES (1001, '徐红博', '男', 3, '1');
INSERT INTO `c_student` VALUES (1002, '李威', '男', 3, '1');
INSERT INTO `c_student` VALUES (1003, '张三', '女', 3, '1');
INSERT INTO `c_student` VALUES (1004, '李四', '女', 3, '1');
INSERT INTO `c_student` VALUES (1005, '昭君', '女', 3, '1');
INSERT INTO `c_student` VALUES (1006, '马云', '男', 3, '1');
INSERT INTO `c_student` VALUES (1007, '马化腾', '男', 1, '1');
INSERT INTO `c_student` VALUES (1008, '撒切尔', '女', 1, '1');
INSERT INTO `c_student` VALUES (1009, '雷军', '男', 2, '1');
INSERT INTO `c_student` VALUES (1010, '天天', '女', 2, '1');
INSERT INTO `c_student` VALUES (1011, '小龙', '男', 3, '1');
INSERT INTO `c_student` VALUES (1012, '小虎', '男', 3, '1');
INSERT INTO `c_student` VALUES (1013, '小兰', '女', 4, '1');
INSERT INTO `c_student` VALUES (1014, '小绿', '女', 4, '1');
INSERT INTO `c_student` VALUES (1015, 'uzi', '男', 5, '1');
INSERT INTO `c_student` VALUES (1016, 'iboy', '男', 5, '1');
INSERT INTO `c_student` VALUES (1017, '小宋', '女', 6, '1');
INSERT INTO `c_student` VALUES (1018, '大虎', '男', 6, '1');
INSERT INTO `c_student` VALUES (1019, '小树', '女', 7, '1');
INSERT INTO `c_student` VALUES (1020, '阿门', '男', 7, '1');
INSERT INTO `c_student` VALUES (1021, '小球', '女', 8, '1');
INSERT INTO `c_student` VALUES (1022, '妮妮', '女', 8, '1');

-- ----------------------------
-- Table structure for c_teacher
-- ----------------------------
DROP TABLE IF EXISTS `c_teacher`;
CREATE TABLE `c_teacher`  (
  `teach_id` int(11) NOT NULL AUTO_INCREMENT,
  `teach_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_education` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`teach_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_teacher
-- ----------------------------
INSERT INTO `c_teacher` VALUES (1, '李老师', '女', '博士', '1');
INSERT INTO `c_teacher` VALUES (2, '陈老师', '男', '博士', '1');
INSERT INTO `c_teacher` VALUES (3, '吴老师', '男', '硕士', '1');
INSERT INTO `c_teacher` VALUES (4, '陈老师', '男', '博士', '1');
INSERT INTO `c_teacher` VALUES (5, '钟老师', '男', '博士', '1');
INSERT INTO `c_teacher` VALUES (6, '薛老师', '女', '学士', '1');
INSERT INTO `c_teacher` VALUES (7, '黄教授', '男', '教授', '1');

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_login_time` datetime(0) NULL DEFAULT NULL,
  `user_privilege` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES (1, 'admin', '123', NULL, NULL, '管理员');
INSERT INTO `c_user` VALUES (2, 'xhb', '123', NULL, NULL, '管理员');
INSERT INTO `c_user` VALUES (4, 'lw', '123', NULL, NULL, '普通用户');
INSERT INTO `c_user` VALUES (8, 'vvvvvv', 'vvvv', NULL, NULL, '管理员');

SET FOREIGN_KEY_CHECKS = 1;
