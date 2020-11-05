ALTER TABLE payment ADD COLUMN return_code_aux VARCHAR(10);
UPDATE payment SET return_code_aux = return_code;
ALTER TABLE payment DROP COLUMN return_code;
ALTER TABLE payment ADD COLUMN return_code VARCHAR(60);
UPDATE payment SET return_code = return_code_aux;
ALTER TABLE payment DROP COLUMN return_code_aux;