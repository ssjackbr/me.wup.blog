INSERT INTO tb_user (first_name, last_name, nick_name, email, password, user_type, user_status, date_registered) VALUES ('Jackson J', 'Rodrigues','Jack','jackson@gmail.com','123456', 1, 'ACTIVE',NOW());
INSERT INTO tb_user (first_name, last_name, nick_name, email, password, user_type, user_status, date_registered) VALUES ('Alex', 'Brown', 'Alex', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 1, 'ACTIVE',NOW());
INSERT INTO tb_user (first_name, last_name, nick_name, email, password, user_type, user_status, date_registered) VALUES ('Maria', 'Green', 'Maria', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 1, 'ACTIVE',NOW());

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_CREATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_READER');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);

INSERT INTO tb_post (author, title, content, status, created_at, update_at, user_id) VALUES ('Jackson J Rodrigues','Utopias de um louco Volume 1', 'Este material foi preparado pelo Banco Inter S.A. e destina-se à informação de investidores, não constituindo oferta de compra ou venda de títulos ou valores mobiliários. Os ativos discutidos neste relatório podem não ser adequados para todos os investidores. Este material não leva em consideração os objetivos de investimento, a situação financeiras e as necessidades específicas de qualquer investidor em particular. Aqueles que desejem adquirir ou negociar os ativos objeto de análise neste material devem obter as informações pertinentes para formarem sua própria convicção sobre o investimento. As decisões de investimento devem ser realizadas pelo próprio leitor. O investimento em títulos e valores mobiliários apresenta riscos para os investidores, podendo resultar em significativas perdas patrimoniais. A rentabilidade obtida no passado não representa garantia de resultados futuros.','true', NOW(),NOW(),1);
INSERT INTO tb_post (author, title, content, status, created_at, update_at, user_id) VALUES ('Jackson J Rodrigues','Utopias de um louco Volume 2', 'Este material foi preparado pelo Banco Inter S.A. e destina-se à informação de investidores, não constituindo oferta de compra ou venda de títulos ou valores mobiliários. Os ativos discutidos neste relatório podem não ser adequados para todos os investidores. Este material não leva em consideração os objetivos de investimento, a situação financeiras e as necessidades específicas de qualquer investidor em particular. Aqueles que desejem adquirir ou negociar os ativos objeto de análise neste material devem obter as informações pertinentes para formarem sua própria convicção sobre o investimento. As decisões de investimento devem ser realizadas pelo próprio leitor. O investimento em títulos e valores mobiliários apresenta riscos para os investidores, podendo resultar em significativas perdas patrimoniais. A rentabilidade obtida no passado não representa garantia de resultados futuros.','true', NOW(),NOW(),1);
--INSERT INTO tb_user_post (post_id, user_id) VALUES (1,1);
