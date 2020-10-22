CREATE TABLE USER_SYSTEM(
  id SERIAL PRIMARY KEY NOT NULL,
  profile_id bigint,
  email varchar(70) NOT NULL UNIQUE,
  login varchar(20) NOT NULL UNIQUE,
  passw varchar(255) NOT NULL,
  status varchar(255) NOT NULL,
  registration_date timestamp NOT NULL,
  validation_date timestamp
);


CREATE TABLE role (
  id SERIAL PRIMARY KEY NOT NULL,
  name_role varchar(50) NOT NULL
);

CREATE TABLE users_role (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  UNIQUE (role_id),
  UNIQUE (user_id,role_id)
);