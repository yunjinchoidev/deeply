# liquibase formatted sql
# changeset minisha:v1

ALTER TABLE user ADD COLUMN fcm_token varchar(32) DEFAULT NULL AFTER password;

