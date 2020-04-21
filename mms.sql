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

 Date: 21/04/2020 14:12:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(11) NOT NULL DEFAULT 0 COMMENT '权限所在的权限模块id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `type` int(11) NOT NULL DEFAULT 3 COMMENT '类型，1：菜单，2：按钮，3：其他',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一个更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES (1, '20171015095130_26', '进入产品管理界面', 1, '/sys/product/product.page', 1, 1, 1, '', 'Admin', '2017-10-15 09:51:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (2, '20171015095322_14', '查询产品列表', 1, '/sys/product/page.json', 2, 1, 2, '', 'Admin', '2017-10-15 09:53:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (3, '20171015095350_69', '产品上架', 1, '/sys/product/online.json', 2, 1, 3, '', 'Admin', '2017-10-15 09:53:51', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (4, '20171015095420_7', '产品下架', 1, '/sys/product/offline.json', 2, 1, 4, '', 'Admin', '2017-10-15 10:11:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (5, '20171015212626_63', '进入订单页', 2, '/sys/order/order.page', 1, 1, 1, '', 'Admin', '2017-10-15 21:26:27', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (6, '20171015212657_12', '查询订单列表', 2, '/sys/order/list.json', 2, 1, 2, '', 'Admin', '2017-10-15 21:26:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (7, '20171015212907_36', '进入权限管理页', 7, '/sys/aclModule/index.page', 1, 1, 1, '', 'zhangsan', '2020-03-31 10:22:49', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (8, '20171015212938_27', '进入角色管理页', 8, '/sys/role/index.page', 1, 1, 1, '', 'zhangsan', '2020-03-31 10:23:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (9, '20171015213009_0', '进入用户管理页', 9, '/sys/dept/index.page', 1, 1, 1, '', 'zhangsan', '2020-03-31 10:23:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (10, '20171016230429_8', '进入权限更新记录页面', 11, '/sys/log/index.page', 1, 1, 1, '', 'zhangsan', '2020-03-31 10:23:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (16, '', '新增权限模块', 7, '/sys/aclModule/save.json', 2, 1, 1, '', 'superadmin', '2020-03-31 11:48:35', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (17, '', '修改权限模块', 7, '/sys/aclModule/update.json', 2, 1, 1, '', 'superadmin', '2020-03-31 11:19:29', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (18, '', '删除权限模块', 7, '/sys/aclModule/delete.json', 2, 1, 1, '', 'superadmin', '2020-03-31 11:19:23', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (19, '', '查看权限点信息', 7, '/sys/acl/queryByAclModuleId.json', 1, 1, 2, '', 'superadmin', '2020-03-31 11:19:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (20, '', '新增权限点信息', 7, '/sys/acl/save.json', 2, 1, 2, '', 'superadmin', '2020-03-31 11:12:44', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (21, '', '修改权限点信息', 7, '/sys/acl/update.json', 2, 1, 2, '', 'superadmin', '2020-03-31 11:19:52', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (22, '', '删除权限点信息', 7, '/sys/acl/delete.json', 2, 1, 2, '', 'superadmin', '2020-03-31 11:20:00', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (23, '', '新增角色', 8, '/sys/role/save.json', 2, 1, 1, '', 'superadmin', '2020-03-31 11:24:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (24, '', '修改角色', 8, '/sys/role/update.json', 2, 1, 1, '', 'superadmin', '2020-03-31 11:24:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (25, '', '删除角色', 8, '/sys/role/delete.json', 2, 1, 1, '', 'superadmin', '2020-03-31 11:25:21', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (26, '', 'shuai', 7, 'ffffffffffffffff', 1, 1, 3, '', 'superadmin', '2020-03-31 12:14:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (27, '', '算法', 7, 'gggggggggggggggggg', 1, 1, 3, '', 'superadmin', '2020-03-31 12:14:49', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES (28, '', 'shu', 7, 'fffffffffffffffffffffffffffff', 1, 1, 3, '', 'superadmin', '2020-03-31 12:15:02', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上级权限模块id',
  `level` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '权限模块在当前层级下的顺序，由小到大',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES (1, '产品管理', 0, '0', 3, 1, 'product', 'zhangsan', '2020-03-28 08:36:54', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (2, '订单管理', 0, '0', 3, 1, '', 'zhangsan', '2020-03-28 06:23:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (3, '公告管理', 0, '0', 1, 1, '', 'zhangsan', '2020-03-28 08:46:03', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (4, '出售中产品管理', 1, '0.1', 1, 1, '', 'Admin', '2017-10-14 21:13:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (5, '下架产品管理', 1, '0.1', 2, 1, '', 'Admin', '2017-10-14 20:18:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (6, '权限管理', 0, '0', 4, 1, '', 'Admin', '2017-10-15 21:27:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (7, '权限管理', 6, '0.6', 1, 1, '', 'Admin', '2017-10-15 21:27:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (8, '角色管理', 6, '0.6', 2, 1, '', 'Admin', '2017-10-15 21:28:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (9, '用户管理', 6, '0.6', 2, 1, '', 'Admin', '2017-10-15 21:28:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (10, '运维管理', 0, '0', 6, 1, '', 'Admin', '2017-10-16 23:03:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES (11, '权限更新记录管理', 6, '0.6', 4, 1, '', 'Admin', '2017-10-16 23:04:07', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上级部门id',
  `level` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '技术部', 0, '0', 1, '技术部', 'sys', '2020-03-26 08:01:13', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (2, '后端开发', 1, '0.1', 1, '后端', 'system-update', '2017-10-12 07:56:16', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (3, '前端开发', 1, '0.1', 2, '', 'system-update', '2017-10-14 11:29:45', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (4, 'UI设计', 1, '0.1', 3, '', 'system', '2017-10-12 07:55:43', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (11, '产品部', 0, '0', 2, '', 'Admin', '2017-10-16 22:52:29', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES (12, '客服部', 0, '0', 4, '', 'Admin', '2017-10-17 00:22:55', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES (16, 'java', 2, '0.1.2', 3, '', 'zhangsan', '2020-03-31 10:13:55', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES (18, 'c++', 2, '0.1.2', 2, '', 'zhangsan', '2020-03-27 05:34:14', '127.0.0.1');
INSERT INTO `sys_dept` VALUES (19, '华南部', 0, '0', 1, '', 'zhangsan', '2020-03-28 06:29:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES (24, 'python', 2, '0.1.2', 3, '', 'superadmin', '2020-04-05 01:31:26', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` int(11) NOT NULL COMMENT '基于type后指定的对象id，比如用户、权限、角色等表的主键',
  `oldvalue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '旧值',
  `newvalue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '新值',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '当前是否复原过，0：没有，1：复原过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (19, 6, 1, '[1,2,3,4,5,6]', '[1,2,3,4]', 'superadmin', '2020-04-06 10:54:57', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (20, 7, 1, '[2,6]', '[2,6,10]', 'superadmin', '2020-04-06 10:55:06', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (21, 7, 1, '[2,6,10]', '[2,6]', 'superadmin', '2020-04-06 10:55:58', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (22, 6, 1, '[1,2,3,4]', '[1,2,3,4,5,6]', 'superadmin', '2020-04-06 11:04:27', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (23, 6, 1, '[1,2,3,4,5,6]', '[1,2,3,4]', 'superadmin', '2020-04-06 11:08:35', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (24, 6, 1, '[1,2,3,4]', '[1,2,3,4,5,6]', 'superadmin', '2020-04-06 11:10:09', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (25, 6, 1, '[1,2,3,4,5,6]', '[1,2,3,4]', 'superadmin', '2020-04-06 11:12:04', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (26, 6, 1, '[1,2,3,4]', '[1,2,3,4,5,6]', 'superadmin', '2020-04-06 11:20:54', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (27, 6, 1, '[1,2,3,4,5,6]', '[1,2,3,4]', 'superadmin', '2020-04-06 11:22:38', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (28, 6, 1, '[1,2,3,4]', '[1,2,3,4,5,6]', 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (29, 1, 26, '', '{\"id\":26,\"name\":\"shuai\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:23:52\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:23:52', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (30, 1, 26, '{\"id\":26,\"name\":\"shuai\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:23:52\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":26,\"name\":\"fffff\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:24:02\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:24:03', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (31, 1, 26, '{\"id\":26,\"name\":\"fffff\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:24:03\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":26,\"name\":\"shuai\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:24:15\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:24:16', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (32, 2, 14, '', '{\"id\":14,\"username\":\"root\",\"telephone\":\"13772474731\",\"mall\":\"1023@163.com\",\"password\":\"E9B0C9300415F9A9CC39F8D934D46820\",\"deptId\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:25:44\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:25:45', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (33, 2, 14, '{\"id\":14,\"username\":\"root\",\"telephone\":\"13772474731\",\"mall\":\"1023@163.com\",\"password\":\"E9B0C9300415F9A9CC39F8D934D46820\",\"deptId\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:25:44\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":14,\"username\":\"root\",\"telephone\":\"13772474731\",\"mall\":\"1023@163.com\",\"password\":null,\"deptId\":1,\"status\":0,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:25:55\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:25:56', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (34, 2, 14, '{\"id\":14,\"username\":\"root\",\"telephone\":\"13772474731\",\"mall\":\"1023@163.com\",\"password\":\"E9B0C9300415F9A9CC39F8D934D46820\",\"deptId\":1,\"status\":0,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:25:56\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":14,\"username\":\"root\",\"telephone\":\"13772474731\",\"mall\":\"1023@163.com\",\"password\":\"E9B0C9300415F9A9CC39F8D934D46820\",\"deptId\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:11\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:26:11', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (35, 5, 8, '', '{\"id\":8,\"name\":\"算法工程师\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:36\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:26:36', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (36, 5, 8, '{\"id\":8,\"name\":\"算法工程师\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:36\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":8,\"name\":\"算法工程师1\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:41\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:26:41', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (37, 5, 8, '{\"id\":8,\"name\":\"算法工程师1\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:41\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":8,\"name\":\"算法工程师\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:50\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', 'superadmin', '2020-04-06 11:26:50', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (38, 3, 17, '{\"id\":17,\"name\":\"算法\",\"parentId\":0,\"level\":\"0\",\"seq\":3,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-05 02:33:53\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\",\"status\":1}', '{\"id\":17,\"name\":\"算法\",\"parentId\":0,\"level\":\"0\",\"seq\":6,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:27:05\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\",\"status\":1}', 'superadmin', '2020-04-06 11:27:06', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (39, 3, 17, '{\"id\":17,\"name\":\"算法\",\"parentId\":0,\"level\":\"0\",\"seq\":6,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:27:06\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\",\"status\":1}', '{\"id\":17,\"name\":\"算法\",\"parentId\":0,\"level\":\"0\",\"seq\":3,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:27:11\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\",\"status\":1}', 'superadmin', '2020-04-06 11:27:12', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (40, 3, 17, '{\"id\":17,\"name\":\"算法\",\"parentId\":0,\"level\":\"0\",\"seq\":3,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:27:12\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\",\"status\":1}', '', 'superadmin', '2020-04-06 11:27:16', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (41, 5, 6, '{\"id\":6,\"name\":\"java工程师\",\"type\":1,\"status\":1,\"remark\":\"java\",\"operator\":\"zhangsan\",\"operatorTime\":\"2020-03-29 07:52:49\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', '', 'superadmin', '2020-04-06 11:27:23', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (42, 5, 8, '{\"id\":8,\"name\":\"算法工程师\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:26:50\",\"operatorIp\":\"0:0:0:0:0:0:0:1\"}', '', 'superadmin', '2020-04-06 11:27:26', '0:0:0:0:0:0:0:1', 1);
INSERT INTO `sys_log` VALUES (43, 1, 26, '{\"id\":26,\"name\":\"shuai\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"superadmin\",\"operatorTime\":\"2020-04-06 11:24:16\",\"opetatorIp\":\"0:0:0:0:0:0:0:1\"}', '', 'superadmin', '2020-04-06 11:27:32', '0:0:0:0:0:0:0:1', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int(11) NOT NULL DEFAULT 1 COMMENT '角色的类型，1：管理员角色，2：其他',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：可用，0：冻结',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '产品管理员', 1, 1, '', 'Admin', '2017-10-15 12:42:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (2, '订单管理员', 1, 1, '', 'Admin', '2017-10-15 12:18:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (3, '公告管理员', 1, 1, '', 'Admin', '2017-10-15 12:19:10', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (4, '权限管理员', 1, 1, '', 'Admin', '2017-10-15 21:30:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES (5, '运维管理员', 1, 1, '运维', 'Admin', '2017-10-17 00:23:28', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES (24, 2, 5, 'zhangsan', '2020-03-31 03:08:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (25, 4, 7, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (26, 4, 16, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (27, 4, 17, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (28, 4, 18, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (29, 4, 19, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (30, 4, 20, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (31, 4, 21, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (32, 4, 22, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (33, 4, 8, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (34, 4, 23, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (35, 4, 24, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (36, 4, 25, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (37, 4, 9, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (38, 4, 10, 'superadmin', '2020-03-31 11:49:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (79, 1, 1, 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (80, 1, 2, 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (81, 1, 3, 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (82, 1, 4, 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (83, 1, 5, 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES (84, 1, 6, 'superadmin', '2020-04-06 11:23:38', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (30, 4, 6, 'superadmin', '2020-03-31 11:52:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (36, 1, 2, 'superadmin', '2020-04-06 10:55:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES (37, 1, 6, 'superadmin', '2020-04-06 10:55:58', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `telephone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户所在部门的id',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结状态，2：删除',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'superadmin', '17319870746', 'admin@qq.com', '1111', 1, 1, 'admin', 'system', '2017-10-13 08:46:16', '127.0.0.1');
INSERT INTO `sys_user` VALUES (2, 'Jimin', '13188889999', 'jimin@qq.com', '25D55AD283AA400AF464C76D713C07AD', 1, 1, 'jimin.zheng', 'Admin', '2017-10-14 14:45:19', '127.0.0.1');
INSERT INTO `sys_user` VALUES (3, 'Jimmy', '13812344311', 'jimmy@qq.com', '25D55AD283AA400AF464C76D713C07AD', 2, 1, '', 'Admin', '2017-10-16 12:57:35', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES (4, 'Kate', '13144445555', 'kate@qq.com', '25D55AD283AA400AF464C76D713C07AD', 1, 0, 'sss', 'Admin', '2017-10-16 23:02:51', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES (5, '服务员A', '18677778888', 'service@qq.com', '25D55AD283AA400AF464C76D713C07AD', 12, 1, '', 'Admin', '2017-10-17 00:22:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES (6, 'zhangsan', '13772474738', '1111', '1111', 2, 1, 'hhh', 'sys', '2020-03-26 08:49:07', '');
INSERT INTO `sys_user` VALUES (10, 'lisi', '13745474738', '111111', '86daa68', 1, 1, 'gg', 'zhangsan', '2020-03-31 10:14:33', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES (11, 'superadmin', '13772474733', '66666', 'c6799e1', 1, 1, '', 'sys', '2020-03-27 04:10:06', '');
INSERT INTO `sys_user` VALUES (12, 'lisi', '11112222334', '123@qq.com', '41bb87e', 2, 2, '', 'zhangsan', '2020-03-27 05:35:42', '127.0.0.1');
INSERT INTO `sys_user` VALUES (14, 'root', '13772474731', '1023@163.com', 'E9B0C9300415F9A9CC39F8D934D46820', 1, 1, '', 'superadmin', '2020-04-06 11:26:11', '0:0:0:0:0:0:0:1');

SET FOREIGN_KEY_CHECKS = 1;
