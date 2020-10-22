CREATE TABLE campaign (
  id SERIAL PRIMARY KEY NOT NULL,
  creation_date timestamp NOT NULL,
  end_date timestamp,
  name varchar(100) NOT NULL,
  description text NOT NULL,
  logo text,
  goal_points integer NOT NULL,
  points_received int NOT NULL,
  profile_id bigint NOT NULL
);

ALTER TABLE campaign ADD CONSTRAINT fk_campaign_profile FOREIGN KEY (profile_id) REFERENCES profile(id);