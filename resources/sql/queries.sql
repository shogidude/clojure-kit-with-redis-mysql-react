-- place your sql queries here
-- see https://www.hugsql.org/ for documentation



-- Checks the uptime of the database,
-- but mostly just checking to see if the DB is alive
-- :name show-status :? :*
-- :doc Returns the Variable_name column and the Value column
SHOW STATUS LIKE 'Uptime';