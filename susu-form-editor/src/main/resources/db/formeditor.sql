DROP DATABASE IF EXISTS `formeditor`;

CREATE DATABASE  `formeditor` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

USE `formeditor`;

DROP TABLE IF EXISTS `form`;
CREATE TABLE `form` (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(64) NOT NULL COMMENT '名称',
    `content` varchar(2048) NULL DEFAULT NULL COMMENT '内容',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) NULL DEFAULT '' COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(64) NULL DEFAULT '' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT = '';

DROP TABLE IF EXISTS `form_submit`;
CREATE TABLE `form_submit` (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `form_id` bigint(0) NOT NULL COMMENT '',
    `content` varchar(2048) NULL DEFAULT NULL COMMENT '内容',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) NULL DEFAULT '' COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(64) NULL DEFAULT '' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT = '';

DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `form_id` bigint(0) NOT NULL COMMENT '',
    `content` varchar(2048) NULL DEFAULT NULL COMMENT '内容',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) NULL DEFAULT '' COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(64) NULL DEFAULT '' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT = '';