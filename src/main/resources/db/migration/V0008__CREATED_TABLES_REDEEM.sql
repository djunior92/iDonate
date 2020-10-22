CREATE TABLE redeem (
  id SERIAL PRIMARY KEY NOT NULL,
  date_redeem timestamp NOT NULL,
  profile_id bigint NOT NULL,
  value_redeemed decimal(12,2) NOT NULL,
  value_rate decimal(12,2),
  points_redeemed int NOT NULL,
  quotation_id bigint NOT NULL,
  bank_account_id bigint NOT NULL
);

ALTER TABLE redeem ADD CONSTRAINT fk_redeem_profile FOREIGN KEY (profile_id) REFERENCES profile(id);
ALTER TABLE redeem ADD CONSTRAINT fk_redeem_quotation FOREIGN KEY (quotation_id) REFERENCES quotation(id);
ALTER TABLE redeem ADD CONSTRAINT fk_redeem_bank_account FOREIGN KEY (bank_account_id) REFERENCES bank_account(id);


