INSERT INTO `privilege`
VALUES 
(1,'systemManage','ϵͳ����',NULL),
(2,'userQuery','�û�����',1),
(3,'roleQuery','��ɫ����',1),
(4,'privilegeQuery','Ȩ�޹���',1),
(5,'userEdit','�༭�û�',2),
(6,'userAdd','����û�',2),
(7,'userDelete','ɾ���û�',2),
(8,'grantRole','�����ɫ',2),
(9,'privilegeAdd','���Ȩ��',4),
(10,'privilegeEdit','�༭Ȩ��',4),
(11,'privilegeDelete','ɾ��Ȩ��',4),
(12,'roleAdd','��ӽ�ɫ',3),
(13,'roleEdit','�༭��ɫ',3),
(14,'roleDelete','ɾ����ɫ',3),
(15,'grantPriv','����Ȩ��',3);

INSERT INTO `role` VALUES (1,0,'ӵ������Ȩ��','��������Ա');

INSERT INTO `role_privilege` VALUES 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15);

INSERT INTO `user` VALUES (1,'2010-04-09 22:08:00',0,'2010-04-09 21:01:00',9,'admin','��������Ա',0,'admin');

INSERT INTO `user_role` VALUES (1,1);