CREATE TABLE donation (
  id SERIAL PRIMARY KEY NOT NULL,
  date_donation timestamp NOT NULL,
  donor_id bigint NOT NULL,
  benefited_id bigint,
  campaign_id bigint,
  donated_points int NOT NULL
);

ALTER TABLE donation ADD CONSTRAINT fk_donation_profile_donor FOREIGN KEY (donor_id) REFERENCES profile(id);
ALTER TABLE donation ADD CONSTRAINT fk_donation_profile_benefited FOREIGN KEY (benefited_id) REFERENCES profile(id);
ALTER TABLE donation ADD CONSTRAINT fk_donation_campaign FOREIGN KEY (campaign_id) REFERENCES campaign(id);

