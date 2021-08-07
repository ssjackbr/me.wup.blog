INSERT INTO tb_user (first_name, last_name, login, email, password, user_type) VALUES ('Jackson','R','ssjackbr','jackson@gmail.com','123456',1);

INSERT INTO tb_post (title,body_text, author, created_At, update_At) VALUES ('Utopias de um louco Volume 1', 'Teste em construção','Jackson R','2021-08-07T01:50:07.12345Z','2021-08-07T02:50:07.12345Z');
INSERT INTO tb_post (title,body_text, author, created_At, update_At) VALUES ('Utopias de um louco Volume 2', 'Teste em construção','Jackson R','2021-08-07T02:50:07.12345Z','2021-08-07T02:50:07.12345Z');
INSERT INTO tb_post (title,body_text, author, created_At, update_At) VALUES ('Utopias de um louco Volume 3', 'Teste em construção','Jackson R','2021-08-07T03:50:07.12345Z','2021-08-07T02:50:07.12345Z');

INSERT INTO tb_user_post (user_id, post_id) VALUES (1,1)
INSERT INTO tb_user_post (user_id, post_id) VALUES (1,2)
INSERT INTO tb_user_post (user_id, post_id) VALUES (1,3)