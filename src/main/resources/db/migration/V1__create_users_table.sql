CREATE TABLE tb_users (
  id UUID PRIMARY KEY,
  full_name VARCHAR(150) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(50),
  document VARCHAR(255),
  account_number VARCHAR(40),
  agency VARCHAR(20),
  bank VARCHAR(6),
  password VARCHAR(255) NOT NULL,
  birth_date DATE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
  deleted_at TIMESTAMP
);

COMMENT ON COLUMN tb_users.id IS 'Unique identifier of the user (UUID)';
COMMENT ON COLUMN tb_users.full_name IS 'User full name';
COMMENT ON COLUMN tb_users.email IS 'User email';
COMMENT ON COLUMN tb_users.password IS 'User password (hash)';
COMMENT ON COLUMN tb_users.phone IS 'User contact information';
COMMENT ON COLUMN tb_users.account_number IS 'User account number';
COMMENT ON COLUMN tb_users.bank IS 'User bank reference';
COMMENT ON COLUMN tb_users.agency IS 'User agency information';
COMMENT ON COLUMN tb_users.created_at IS 'Date and time of user creation';
COMMENT ON COLUMN tb_users.updated_at IS 'Date and time of user update';
COMMENT ON COLUMN tb_users.birth_date IS 'User birth date';
COMMENT ON COLUMN tb_users.deleted_at IS 'Date and time of user deletion';