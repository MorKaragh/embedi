CREATE TABLE IF NOT EXISTS agents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    status INT NOT NULL
);

CREATE TABLE IF NOT EXISTS persons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    birth_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS addresses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_address_string VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tick_quotes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    agent_id UUID REFERENCES agents(id) NOT NULL,
    premium DECIMAL,
    stream_quote_id VARCHAR(50),
    insurer_id UUID REFERENCES persons(id),
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    address_id UUID REFERENCES addresses(id)
);

CREATE TABLE IF NOT EXISTS tick_insured_persons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    quote_id UUID REFERENCES tick_quotes(id),
    person_id UUID REFERENCES persons(id)
);



