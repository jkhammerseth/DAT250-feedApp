# DAT250-feedApp

## Database setup:
1. Installer postgreSQL (postgres app for mac)
2. Port 5434
     Username:
     Password:
3. I postgres shell commandoer:
  - psql (postgres versjon)
  - \l (liste opp alle databaser)
  - CREATE DATABASE feedappdb; (lager database som heter feedappdb)
  - \du (lister opp roller, her skal du finne **din bruker**)
  - GRANT ALL PRIVILEGES ON DATABASE "feedappdb" TO **din bruker**;
  - GRANT ALL PRIVILEGES ON DATABASE "feedappdb" TO postgres;
  - \l (no skal feedappdb dukke opp i liste over databaser)
  - \c feedappdb (koble til databasen)
  - \d (liste opp relasjoner og tables)



# Postman:
Import this [Postman JSON](https://www.getpostman.com/collections/51079e8f1eabbe1f4071) into your postman applicaton for API tests 


# New branches:
git pull
git checkout -b feature/b-1-looking-up-iot
*YOUR BRANCH NAME WITH TRELLO-CODE (B-1/F-1)*
git status
git add . 
git commit -m "B-1: implementation of iot"
git push --set-upstream origin feature/b-1-looking-up-iot