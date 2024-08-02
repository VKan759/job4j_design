insert into roles(role) values('admin role');
insert into rules(rule) values('admin rule');
insert into role_rule (role, rule) values(1, 1);
insert into users(name, role) values('Vyacheslav', 1);
insert into states(state) values(true);
insert into categories(name) values('important');
insert into items(name, state, category, user_item) values ('first item', 1, 1, 1);
insert into comments(comment, item_comment) values('comment for SQL', 1);
insert into attachs(attachment, item_attachment) values ('Attachment', 1);