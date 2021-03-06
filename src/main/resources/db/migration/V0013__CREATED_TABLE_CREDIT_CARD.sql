CREATE TABLE credit_card (
  id SERIAL PRIMARY KEY NOT NULL,
  profile_id bigint NOT NULL,
  card_number varchar(50) NOT NULL,
  printed_name varchar(150) NOT NULL,
  expirtation_date varchar(10) NOT NULL,
  brand varchar(50),
  card_token varchar(255),
  digit varchar(150),
  status varchar(15) NOT NULL
);

ALTER TABLE credit_card ADD CONSTRAINT fk_creditCard_profile FOREIGN KEY (profile_id) REFERENCES profile(id);
ALTER TABLE recharge ADD COLUMN credit_card_id bigint;
ALTER TABLE recharge ADD CONSTRAINT fk_recharge_creditCard FOREIGN KEY (credit_card_id) REFERENCES credit_card(id);