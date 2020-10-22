CREATE TABLE profile (
  id SERIAL PRIMARY KEY NOT NULL,
  phone varchar(15) NOT NULL,
  name varchar(255) NOT NULL,
  image text,
  registration_date timestamp NOT NULL,
  facebook varchar(255),
  instagram varchar(255),
  youtube varchar(255),
  website varchar(255),
  my_points int NOT NULL,
  points_received int NOT NULL,
  people_type char(1) NOT NULL,
  document varchar(14) NOT NULL,
  date_birth timestamp NOT NULL,
  description varchar(255) NOT NULL
);

ALTER TABLE USER_SYSTEM ADD CONSTRAINT fk_user_profile FOREIGN KEY (profile_id) REFERENCES profile(id);