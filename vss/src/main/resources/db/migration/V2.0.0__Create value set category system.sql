create table value_set_category_system (id bigint not null auto_increment, system varchar(255), primary key (id)) ENGINE=InnoDB;
create table value_set_category_system_aud (id bigint not null, rev integer not null, revtype tinyint, system varchar(255), primary key (id, rev)) ENGINE=InnoDB;
alter table value_set_category_system add constraint UKb3e9q77v81onsgnhq09g8g6h2 unique (system);
alter table value_set_category add value_set_category_system_id bigint not null;
alter table value_set_category_aud add value_set_category_system_id bigint;
alter table value_set_category add constraint FK86aj26rwxaba94f2lvwxep8t3 foreign key (value_set_category_system_id) references value_set_category_system (id);
alter table value_set_category_system_aud add constraint FKtms34e25c129q9mxcqjv5chqa foreign key (rev) references revinfo (id);