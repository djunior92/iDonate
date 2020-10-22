CREATE TABLE quotation (
  id SERIAL PRIMARY KEY NOT NULL,
  date_start timestamp NOT NULL,
  date_end timestamp,
  price_point decimal(12,2) NOT NULL
);

