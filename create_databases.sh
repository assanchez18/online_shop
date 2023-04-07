#!/usr/bin/env bash
set -euxo pipefail

declare -a databases=(
    "users"
)

for service in "${databases[@]}"
do
  db=${service//-/_}
  user=$db
  flyway_user=${db}_flyway
  echo "Setting up DB - $db for user=$user and flyway_user=$flyway_user"
psql -v ON_ERROR_STOP=1 --username "postgres" <<-EOSQL
  create database $db;
  create user $user with encrypted password '$POSTGRES_PASSWORD';
  create user $flyway_user with encrypted password '$POSTGRES_PASSWORD';
  grant all privileges on database $db to $flyway_user with grant option;
EOSQL
psql -v ON_ERROR_STOP=1 --username "postgres" -d $db <<-EOSQL
  create extension "uuid-ossp";
  set role $flyway_user;
  alter default privileges in schema public grant all on tables to $user;
  alter default privileges in schema public grant all on sequences to $user;
  reset role;
EOSQL
done
echo "Created DBs."
