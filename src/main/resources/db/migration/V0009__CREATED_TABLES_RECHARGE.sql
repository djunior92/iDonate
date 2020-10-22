CREATE TABLE recharge (
  id SERIAL PRIMARY KEY NOT NULL,
  date_recharge timestamp NOT NULL,
  profile_id bigint NOT NULL,
  value_recharged decimal(12,2) NOT NULL,
  value_rate decimal(12,2),
  points_recharged int NOT NULL,
  quotation_id bigint NOT NULL
);

ALTER TABLE recharge ADD CONSTRAINT fk_recharge_profile FOREIGN KEY (profile_id) REFERENCES profile(id);
ALTER TABLE recharge ADD CONSTRAINT fk_recharge_quotation FOREIGN KEY (quotation_id) REFERENCES quotation(id);


