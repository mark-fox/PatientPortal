create sequence hibernate_sequence start with 1 increment by 1;
create table doctors (doc_id bigint not null, firstname varchar(255) not null, lastname varchar(255) not null, primary key (doc_id)) engine=InnoDB;
create table patients (id bigint not null, dob date not null, email varchar(255), firstname varchar(255) not null, gender varchar(255), lastname varchar(255) not null, lastvisit date, phone varchar(255), ethnicity varchar(255), doc_id bigint, primary key (id)) engine=InnoDB;
create table users (id bigint not null, active bit not null, email varchar(255) not null, password varchar(255) not null, roles varchar(255), primary key (id)) engine=InnoDB;
create table visitnotes (visit_id bigint not null, description longtext, visitdate date not null, reason varchar(255) not null, patient_id bigint not null, primary key (visit_id)) engine=InnoDB;
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table patients add constraint FK63yr24bnfvdxkm982yo14svi7 foreign key (doc_id) references doctors (doc_id);
alter table visitnotes add constraint FK5pfwj1kxhi616dnal01qh7j08 foreign key (patient_id) references patients (id);
