CREATE TABLE address (
  id SERIAL PRIMARY KEY NOT NULL,
  profile_id bigint NOT NULL,
  street_address varchar(100) NOT NULL,
  number_address varchar(6) NOT NULL,
  complement_address varchar(50),
  cep varchar(10) NOT NULL,
  neighborhood varchar(50) NOT NULL,
  city varchar(100) NOT NULL,
  uf char(2) NOT NULL
);

ALTER TABLE address ADD CONSTRAINT fk_address_profile FOREIGN KEY (profile_id) REFERENCES profile(id);