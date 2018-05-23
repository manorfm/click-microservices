CREATE TABLE "CLICK"
(
   CLK_ID bigint PRIMARY KEY NOT NULL,
   AUD_CREATE timestamp,
   AUD_UPDATE timestamp,
   CLK_DH_TIMER timestamp,
   USR_ID bigint
)
;
CREATE TABLE "USER"
(
   USR_ID bigint PRIMARY KEY NOT NULL,
   AUD_CREATE timestamp,
   AUD_UPDATE timestamp,
   USR_NM varchar(100),
   USR_PW varchar(1000)
)

CREATE INDEX SYS_IDX_FK1REV8ERGBWNQRX62RFST6C56L_10163 ON "CLICK"(USR_ID)
;
CREATE UNIQUE INDEX SYS_IDX_SYS_PK_10156_10157 ON "CLICK"(CLK_ID)
;
CREATE UNIQUE INDEX SYS_IDX_SYS_PK_10159_10160 ON "USER"(USR_ID)
;
