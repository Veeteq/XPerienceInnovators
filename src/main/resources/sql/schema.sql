create table accounts (
    id                  bigint not null,
    holder_name         varchar(100),
    holder_address      varchar(100),
    holder_phone_number varchar(100),
    holder_email        varchar(100)
);
create sequence accounts_seq start with 1 increment by 1 nocache;

create table policies (
    id                  bigint not null,
    account_id          bigint not null,
    policy_type         varchar(100),
    policy_number       varchar(100),
    colour              varchar(100),
    engine_size         varchar(100),
    fuel_type           varchar(100),
    make                varchar(100),
    model               varchar(100),
    motor_type          varchar(100),
    registration_number varchar(100),
    year_of_manufacture integer,
    pet_name            varchar(100),
    pet_type            varchar(100),
    gender              varchar(100),
    breed               varchar(100),
    pet_age_years       integer,
    pet_age_months      integer,
    pedigree            varchar(3),
    level_of_cover      varchar(100),
    medical_conditions  varchar(3)
);
create sequence policies_seq start with 1 increment by 1 nocache;