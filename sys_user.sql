/*
 Navicat Premium Data Transfer

 Source Server         : Mysql2Linux
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : 192.168.47.66:3306
 Source Schema         : mms

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 14/03/2020 11:57:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名称',
  `telephone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `mall` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '用户的部门 id',
  `status` int(1) DEFAULT NULL COMMENT '用户的状态，1 ：正常，0：冻结，2删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作的时间',
  `operator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者的 ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '小法', '11111111111', '111@163.com', '1', 1, 1, '1', '火男', '2020-03-20 10:03:43', 'admin/user');

SET FOREIGN_KEY_CHECKS = 1;
