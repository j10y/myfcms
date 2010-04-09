INSERT INTO `privilege`
VALUES 
(1,'systemManage','系统管理',NULL),
(2,'userQuery','用户管理',1),
(3,'roleQuery','角色管理',1),
(4,'privilegeQuery','权限管理',1),
(5,'userEdit','编辑用户',2),
(6,'userAdd','添加用户',2),
(7,'userDelete','删除用户',2),
(8,'grantRole','授予角色',2),
(9,'privilegeAdd','添加权限',4),
(10,'privilegeEdit','编辑权限',4),
(11,'privilegeDelete','删除权限',4),
(12,'roleAdd','添加角色',3),
(13,'roleEdit','编辑角色',3),
(14,'roleDelete','删除角色',3),
(15,'grantPriv','授予权限',3);

INSERT INTO `role` VALUES (1,0,'拥有所有权限','超级管理员');

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

INSERT INTO `user` VALUES (1,'2010-04-09 22:08:00',0,'2010-04-09 21:01:00',9,'admin','超级管理员',0,'admin');

INSERT INTO `user_role` VALUES (1,1);