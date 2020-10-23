create table "userDetailsDTO"
(
	user_id varchar,
	user_namd varchar,
	locale varchar,
	email varchar,
	stated boolean,
	created_date timestamp,
	update_date timestamp
);

alter table "userDetailsDTO" owner to postgres;

