# liquibase formatted sql
# changeset minisha:v1

ALTER TABLE user ADD COLUMN provider varchar(32) DEFAULT NULL AFTER fcm_token;
