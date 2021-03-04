CREATE TABLE IF NOT EXISTS players
(
	id varchar(36) not null,
	player_id varchar(36) not null,
	items longtext not null,
	created_at long not null,
	updated_at long not null
);

create unique index players_id_uindex
	on players (id);

alter table players
	add constraint players_pk
		primary key (id);