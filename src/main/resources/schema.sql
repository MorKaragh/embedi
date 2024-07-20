CREATE TABLE IF NOT EXISTS public.agents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.persons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    birth_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS public.addresses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_address_string VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tick_quotes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    premium DECIMAL,
    insurer UUID REFERENCES public.persons(id),
    date_start TIMESTAMP WITH TIME ZONE NOT NULL,
    date_end TIMESTAMP WITH TIME ZONE NOT NULL,
    address UUID REFERENCES public.addresses(id)
);

CREATE TABLE IF NOT EXISTS public.tick_insurers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    quote_id UUID REFERENCES public.tick_quotes(id),
    insurer_id UUID REFERENCES public.persons(id)
);


