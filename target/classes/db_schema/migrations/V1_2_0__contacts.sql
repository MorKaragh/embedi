CREATE TABLE IF NOT EXISTS contacts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    quote_id UUID, 
    phone INTEGER,
    email VARCHAR(64)
);



