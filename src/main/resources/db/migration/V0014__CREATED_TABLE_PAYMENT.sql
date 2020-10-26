CREATE TABLE payment (
  id SERIAL PRIMARY KEY NOT NULL,
  proof_of_sale varchar(50),
  t_id varchar(50),
  authorization_code varchar(50),
  payment_id varchar(150),
  amount decimal(12,2),
  status integer,
  return_code varchar(10),
  return_message varchar(150),
  card_token varchar(150),
  recharge_id bigint
);

ALTER TABLE payment ADD CONSTRAINT fk_payment_recharge FOREIGN KEY (recharge_id) REFERENCES recharge(id);