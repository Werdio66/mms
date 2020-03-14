/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : mms

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 13/03/2020 19:45:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '权限模块 id',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限码',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '请求的 url',
  `type` int(1) NOT NULL DEFAULT 3 COMMENT '类型，1：菜单，2：按钮，3：其他',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结',
  `seq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限在当前模块的顺序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作的时间',
  `operator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者 ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL COMMENT '父级模块权限 id',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限模块层级',
  `seq` int(11) DEFAULT NULL COMMENT '权限模块在当前层级的顺序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作的时间',
  `opetator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者的 ip',
  `status` int(1) DEFAULT NULL COMMENT '状态，0：冻结，1：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) NOT NULL COMMENT '父级部门 id',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门层级',
  `seq` int(11) DEFAULT NULL COMMENT '部门在当前层级的顺序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作的时间',
  `opetator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者的 ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `type` int(11) NOT NULL COMMENT '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` bigint(20) DEFAULT NULL COMMENT '基于 type 的指定对象的 id，比如用户，部门，权限等的 id',
  `oldvalue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '旧的值',
  `newvalue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '新的值',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者 ip',
  `status` int(11) DEFAULT NULL COMMENT '当前是否恢复过，0：没有，1：恢复过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `type` int(1) NOT NULL DEFAULT 3 COMMENT '角色类型，1：管理员，2：其他',
  `status` int(1) DEFAULT NULL COMMENT '状态，1：正常，0：冻结',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作的时间',
  `operator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者 ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色 id',
  `acl_id` bigint(20) DEFAULT NULL COMMENT '权限 id',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者 ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色 id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户 id',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作者 ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
