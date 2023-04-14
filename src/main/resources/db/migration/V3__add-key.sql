ALTER TABLE address ADD CONSTRAINT fk_address_user FOREIGN KEY (id_user) REFERENCES users(id);
