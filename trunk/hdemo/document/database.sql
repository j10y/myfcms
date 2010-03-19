
select * from privilege

insert into privilege (privCode,privName,parent_Id)
values ('system','系统管理',null)

insert into privilege (privCode,privName,parent_Id)
values ('role','角色管理','1')