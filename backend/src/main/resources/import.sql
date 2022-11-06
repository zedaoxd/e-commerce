INSERT INTO tb_brand(name, create_Date, update_Date) VALUES ('Pichau', NOW(), NOW());
INSERT INTO tb_brand(name, create_Date, update_Date) VALUES ('Cooler Master', NOW(), NOW());
INSERT INTO tb_brand(name, create_Date, update_Date) VALUES ('AMD', NOW(), NOW());

INSERT INTO tb_category(name, create_Date, update_Date) VALUES ('Eletronicos', NOW(), NOW());
INSERT INTO tb_category(name, create_Date, update_Date) VALUES ('Computadores', NOW(), NOW());
INSERT INTO tb_category(name, create_Date, update_Date) VALUES ('Perifericos', NOW(), NOW());

INSERT INTO tb_state(name, initials, create_Date, update_Date) VALUES ('Rio de Janeiro', 'RJ', NOW(), NOW());
INSERT INTO tb_state(name, initials, create_Date, update_Date) VALUES ('São Paulo', 'SP', NOW(), NOW());
INSERT INTO tb_state(name, initials, create_Date, update_Date) VALUES ('Minas Gerais', 'MG', NOW(), NOW());

INSERT INTO tb_city(name, create_Date, update_Date, state_id) VALUES ('Aperibé', NOW(), NOW(), 1);
INSERT INTO tb_city(name, create_Date, update_Date, state_id) VALUES ('Itaperuna', NOW(), NOW(), 1);
INSERT INTO tb_city(name, create_Date, update_Date, state_id) VALUES ('Guarapari', NOW(), NOW(), 3);
INSERT INTO tb_city(name, create_Date, update_Date, state_id) VALUES ('Guarulhos', NOW(), NOW(), 2);

INSERT INTO tb_person(name, cpf, email, password, street, cep, create_Date, update_Date, city_id) VALUES ('maria', '42409041027', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'Rua Fagundes', '39402784', NOW(), NOW(), 1);
INSERT INTO tb_person(name, cpf, email, password, street, cep, create_Date, update_Date, city_id) VALUES ('bob', '38296260026', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'Rua Das Garças', '46283746', NOW(), NOW(), 2);
INSERT INTO tb_person(name, cpf, email, password, street, cep, create_Date, update_Date, city_id) VALUES ('ana', '49811279012', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'Rua 24 de Março', '57392059', NOW(), NOW(), 2);