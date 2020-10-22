CREATE TABLE bank_account (
  id SERIAL PRIMARY KEY NOT NULL,
  profile_id bigint NOT NULL,
  account_type char(1) NOT NULL,
  bank varchar(100) NOT NULL,
  agency varchar(6) NOT NULL,
  dg_agency varchar(2),
  number_account varchar(10) NOT NULL,
  dg_account varchar(2)
);

ALTER TABLE bank_account ADD CONSTRAINT fk_bankAccount_profile FOREIGN KEY (profile_id) REFERENCES profile(id);