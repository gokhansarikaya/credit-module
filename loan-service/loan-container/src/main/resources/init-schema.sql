
drop table if exists "public".loan cascade;
drop table if exists "public".loan_installment cascade;
drop table if exists "public".customer cascade;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "public".customer (
                                 id uuid NOT NULL,
                                 credit_limit numeric(38, 2) NULL,
                                 name varchar(255) NULL,
                                 surname varchar(255) NULL,
                                 used_credit_limit numeric(38, 2) NULL,
                                 CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE "public".loan (
                             id uuid NOT NULL,
                             create_date timestamptz(6) NULL,
                             is_paid bool NULL,
                             loan_amount numeric(38, 2) NULL,
                             number_of_installment int4 NULL,
                             customer_id uuid NULL,
                             CONSTRAINT loan_pkey PRIMARY KEY (id),
                             CONSTRAINT fkcwv05agfqwg5ndy6adbefsx7d FOREIGN KEY (customer_id) REFERENCES public.customer(id)
);

CREATE TABLE "public".loan_installment (
                                         id int8 NOT NULL,
                                         amount numeric(38, 2) NULL,
                                         due_date timestamptz(6) NULL,
                                         is_paid bool NULL,
                                         paid_amount numeric(38, 2) NULL,
                                         payment_date timestamptz(6) NULL,
                                         loan_id uuid NOT NULL,
                                         CONSTRAINT loan_installment_pkey PRIMARY KEY (id, loan_id),
                                         CONSTRAINT fkpii2hd44b4ih32usrb3lyck2p FOREIGN KEY (loan_id) REFERENCES public.loan(id)
);

INSERT INTO public.customer(id,credit_limit, "name", surname, used_credit_limit) VALUES
    ('123e4567-e89b-42d3-a456-556642440000'::uuid, 5000.00, 'testuser', 'testusersurname', 0.00);

INSERT INTO public.customer(id,credit_limit, "name", surname, used_credit_limit) VALUES
    ('6d868c6f-f48e-40dd-baa7-87f7a9663214'::uuid, 1000.00, 'testuser2', 'testusersurname2', 0.00);
