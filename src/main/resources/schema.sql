drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication BYTEA
);

create table if not exists oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

CREATE TABLE if not exists users
(
  user_id integer NOT NULL,
  name character varying(255) NOT NULL,
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  address character varying(255),
  email character varying(255),
  is_verified boolean,
  phone character varying(12) NOT NULL,
  is_active integer,
  CONSTRAINT user_pkey PRIMARY KEY (user_id)
);

CREATE TABLE if NOT EXISTS user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT uk_it77eq964jhfqtu54081ebtio UNIQUE (role_id),
  CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id)
  REFERENCES role (role_id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id)
  REFERENCES users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
  REFERENCES role (role_id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
  REFERENCES users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS role
(
  role_id integer NOT NULL,
  role_name character varying(50) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_id)
);