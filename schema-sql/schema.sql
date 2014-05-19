-- Table: turlcheckedresults

-- DROP TABLE turlcheckedresults;

CREATE TABLE turlcheckedresults
(
  destination_url character varying(500),
  responsecode character varying(500),
  checkdate date NOT NULL DEFAULT ('now'::text)::date,
  uid serial NOT NULL,
  url_uid integer,
  CONSTRAINT turlcheckedresults_pk PRIMARY KEY (uid),
  CONSTRAINT fk_url_uid_tucr FOREIGN KEY (url_uid)
      REFERENCES turltocheck (uid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE turlcheckedresults
  OWNER TO "pgmaster";
  
  
  -- Table: turltocheck

-- DROP TABLE turltocheck;

CREATE TABLE turltocheck
(
  uid serial NOT NULL,
  url character varying(500),
  adddate date NOT NULL DEFAULT ('now'::text)::date,
  CONSTRAINT turltocheck_pkey PRIMARY KEY (uid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE turltocheck
  OWNER TO "pgmaster";

