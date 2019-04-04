CREATE TABLE USERS (
  USERNAME VARCHAR(50) NOT NULL,
  VERSION NUMERIC(8) NOT NULL DEFAULT 1,
  CONSTRAINT PK_USERS PRIMARY KEY (USERNAME)
);

CREATE TABLE AUTHORITIES (
  USERNAME VARCHAR(64) NOT NULL,
  AUTHORITY VARCHAR(64) NOT NULL,
  CONSTRAINT PK_AUTHORITIES PRIMARY KEY (USERNAME, AUTHORITY)
);

