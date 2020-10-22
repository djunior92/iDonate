CREATE TABLE image (
  id SERIAL PRIMARY KEY NOT NULL,
  date_image timestamp NOT NULL,
  profile_id bigint,
  campaign_id bigint,
  name varchar(256),
  description varchar(512),
  image_file text
);

ALTER TABLE image ADD CONSTRAINT fk_image_profile FOREIGN KEY (profile_id) REFERENCES profile(id);
ALTER TABLE image ADD CONSTRAINT fk_image_campaign FOREIGN KEY (campaign_id) REFERENCES campaign(id);




