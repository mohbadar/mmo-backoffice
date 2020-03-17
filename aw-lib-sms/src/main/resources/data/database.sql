-- Schema: sms

-- DROP SCHEMA sms;

CREATE SCHEMA sms
  AUTHORIZATION asims_user;


-- Table: sms.sms_log_tbl

-- DROP TABLE sms.sms_log_tbl;

CREATE TABLE sms.sms_log_tbl
(
  id bigint NOT NULL,
  delivery_date timestamp without time zone,
  delivery_status_message character varying(255),
  delivery_status_number integer NOT NULL,
  message character varying(255),
  receiver_msisdn character varying(255),
  send_date timestamp without time zone,
  sender_msisdn character varying(255),
  CONSTRAINT sms_log_tbl_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sms.sms_log_tbl
  OWNER TO asims_user;
