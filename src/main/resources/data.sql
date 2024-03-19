INSERT INTO CATEGORY VALUES(10, 'Bebidas');
INSERT INTO CATEGORY VALUES(20, 'Lanches');
INSERT INTO CATEGORY VALUES(30, 'Snaks');
INSERT INTO CATEGORY VALUES(40, 'Outros');
INSERT INTO CATEGORY VALUES(41, 'Drinks');

INSERT INTO PRODUCT VALUES (10, 'Lanche 2 Hamburgueres', 'X-Salada', 15.50, 20);
INSERT INTO PRODUCT VALUES (20, 'Refrigereante de Cola', 'Coca Cola', 5, 10);
INSERT INTO PRODUCT VALUES (30, 'Batata Frita', 'Batata Frita', 9, 30);
INSERT INTO PRODUCT VALUES (31, 'Chocolate', 'Chocolate', 5, 30);
INSERT INTO PRODUCT VALUES (40, 'Lanche de Frango', 'X-Chicken', 12, 30);
INSERT INTO PRODUCT VALUES (41, 'Lanche de Picanha', 'X-Picanha', 22, 30);

INSERT INTO CUSTOMER VALUES (10, 1,'34534567840', 'email@teste.com.br', 'Teste Customer');
INSERT INTO CUSTOMER VALUES (11, 0,'34534567842', 'email@teste.com.br', 'Teste Customer');
INSERT INTO CUSTOMER VALUES (12, 1,'34534567843', 'email@teste.com.br', 'Teste Customer');
INSERT INTO CUSTOMER VALUES (13, 1,'34534567899', 'email@teste.com.br', 'Teste Customer');

INSERT INTO "order" VALUES (10,CURRENT_TIMESTAMP(), 'Pedido Teste','any qrcode',CURRENT_TIMESTAMP(), 0, 34, 10);
INSERT INTO "order" VALUES (11,CURRENT_TIMESTAMP(), 'Pedido Teste 2', 'any qrcode', CURRENT_TIMESTAMP(), 0, 40, 10);

INSERT INTO ORDER_ITEM  VALUES (2, 10, 10, 20);
INSERT INTO ORDER_ITEM  VALUES (1, 20, 10, 10);
INSERT INTO ORDER_ITEM  VALUES (1, 6, 10, 30);