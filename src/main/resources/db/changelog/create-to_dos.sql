create table to_dos (
                        id bigint not null auto_increment,
                        description varchar(255),
                        deadline date,
                        importance varchar(255),
                        status varchar(255),
                        user_id bigint,
                        primary key (id)
);
alter table to_dos
    add constraint FK_users
      foreign key (user_id)
      references users (id);