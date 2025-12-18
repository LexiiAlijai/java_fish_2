CREATE TABLE IF NOT EXISTS fisher
(
    id       SERIAL PRIMARY KEY,
    login    TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    salt     TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS catch
(
    id         SERIAL PRIMARY KEY,
    fish_name TEXT NOT NULL,
    weight    REAL,
    length    REAL,
    fisher_id INTEGER NOT NULL,
    CONSTRAINT fk_catch_fisher
        FOREIGN KEY (fisher_id) REFERENCES fisher (id)
);
