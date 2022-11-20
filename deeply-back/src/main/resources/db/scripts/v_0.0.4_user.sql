# liquibase formatted sql
# changeset minisha:v1

ALTER TABLE user ADD COLUMN provider_id int(11) DEFAULT NULL AFTER provider;

