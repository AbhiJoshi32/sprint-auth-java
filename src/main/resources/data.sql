INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('sprintClientIdPassword', 'secret', 'trust,read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);

INSERT INTO users(
	user_id, name, username, password, address, email, is_verified, phone, is_active)
VALUES (1, 'abhi', 'john123', 123, 'India', 'aa@aa.aa', TRUE , 7777777777, 1);
INSERT INTO role(
	role_id, role_name)
VALUES (1, 'ADMIN');

INSERT INTO role(
	role_id, role_name)
VALUES (2, 'USER');

INSERT INTO role(
	role_id, role_name)
VALUES (3, 'SHOP');

INSERT INTO user_role(user_id,role_id)
VALUES (1,2);