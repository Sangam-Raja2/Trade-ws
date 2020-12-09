CREATE USER 'sangam'  PASSWORD 'sangam';
CREATE DATABASE tradeDB with owner = sangam ENCODING = 'UTF8' TABLESPACE = pg_default;

GRANT ALL PRIVILEGES ON DATABASE tradeDB TO sangam;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO sangam;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO sangam;

CREATE TABLE public.user
(
	user_id  bigint NOT NULL,
    user_name character varying(50)  NOT NULL,
    email character varying(120) NULL,
    address character varying(120) ,
    PRIMARY KEY (user_id)
)

CREATE TABLE public.trade
(
    trade_id  bigint NOT NULL,
    user_id  bigint NOT NULL,
    type character varying(120) NOT NULL,
    symbol character varying(120),
    shares character varying(120) ,
    price float ,
    timestamp date NULL,
    PRIMARY KEY (trade_id),
  CONSTRAINT Usr_FK FOREIGN KEY (user_id) REFERENCES user(user_id)
)

GRANT ALL PRIVILEGES ON TABLE trade TO sangam ;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO sangam; 