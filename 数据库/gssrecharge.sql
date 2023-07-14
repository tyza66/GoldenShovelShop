/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : gssrecharge

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 14/07/2023 15:21:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `traceno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES (124, 'admin', '55555', 100, 1);
INSERT INTO `recharge` VALUES (126, 'tyza66', '55555', 100, 0);
INSERT INTO `recharge` VALUES (127, 'tyza66', '1689229755000', 100, 1);
INSERT INTO `recharge` VALUES (128, 'tyza66充值100.0金币', '1689229947000', 100, 1);
INSERT INTO `recharge` VALUES (129, 'tyza66充值100.0金币', '1689229948000', 100, 1);
INSERT INTO `recharge` VALUES (130, 'tyza66充值100.0金币', '1689230307000', 100, 1);
INSERT INTO `recharge` VALUES (131, 'tyza66充值100.0金币', '1689230321000', 100, 1);
INSERT INTO `recharge` VALUES (132, 'tyza66充值100.0金币', '1689230623000', 100, 1);
INSERT INTO `recharge` VALUES (133, 'tyza66充值100.0金币', '1689230862000', 100, 1);
INSERT INTO `recharge` VALUES (134, 'tyza66充值200.0金币', '1689230868000', 200, 1);
INSERT INTO `recharge` VALUES (135, 'tyza66充值10000.0金币', '1689231036000', 10000, 1);
INSERT INTO `recharge` VALUES (136, 'tyza66充值0.0金币', '1689232852000', 0, 1);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
