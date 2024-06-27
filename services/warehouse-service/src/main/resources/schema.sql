CREATE TABLE IF NOT EXISTS warehouse (
    id SERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS bike_part (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(255),
    warehouse_id INTEGER REFERENCES warehouse(id)
);