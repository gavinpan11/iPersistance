/*
 Navicat MariaDB Data Transfer

 Source Server         : 101
 Source Server Type    : MariaDB
 Source Server Version : 100416
 Source Host           : 192.168.0.101:3306
 Source Schema         : ipersistance

 Target Server Type    : MariaDB
 Target Server Version : 100416
 File Encoding         : 65001

 Date: 08/04/2021 21:57:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordertime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` double NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '2019-12-12', 3000, 1);
INSERT INTO `orders` VALUES (2, '2019-12-12', 4000, 1);
INSERT INTO `orders` VALUES (3, '2019-12-12', 5000, 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'CTO', 'CTO');
INSERT INTO `sys_role` VALUES (2, 'CEO', 'CEO');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`userid`, `roleid`) USING BTREE,
  INDEX `roleid`(`roleid`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'lucy', '123', '2019-12-12');
INSERT INTO `user` VALUES (2, 'tom', '123', '2019-12-12');
INSERT INTO `user` VALUES (3, 'gavin0101', '123123skdf', '1991-01-01');
INSERT INTO `user` VALUES (4, 'gavin', 'skdjflsikd', '2020-01-09');
INSERT INTO `user` VALUES (5, 'test-1', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (6, 'gavin0101', '123123skdf', '1991-01-01');
INSERT INTO `user` VALUES (7, 'test-3', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (8, 'test-4', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (9, 'test-5', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (10, 'test-6', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (11, 'test-7', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (12, 'test-8', 'sdfsdf', '1999-12-08');
INSERT INTO `user` VALUES (13, 'test-9', 'sdfsdf', '1999-12-08');

SET FOREIGN_KEY_CHECKS = 1;
