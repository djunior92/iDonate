CREATE TABLE comment (
  id SERIAL PRIMARY KEY NOT NULL,
  date_comment timestamp NOT NULL,
  author_id bigint NOT NULL,
  profile_id bigint,
  campaign_id bigint,
  title varchar(256),
  description varchar(512)
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_profile_author FOREIGN KEY (author_id) REFERENCES profile(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_profile FOREIGN KEY (profile_id) REFERENCES profile(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_campaign FOREIGN KEY (campaign_id) REFERENCES campaign(id);



