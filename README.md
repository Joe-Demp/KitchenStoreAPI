# Kitchen Store API

## Project status 21/05/2020
I intend to restart work on this ASAP. I got sidetracked with tutorials on security and so on.  
The focus is to get a rudimentary project working first, and to worry about other concerns later, including:
an Angular Front-End Client, integration with a bar-code scanner for products, proper security mechanisms
(user accounts, privileges, Http sessions,...)

## Project Targets
1. Create an API for any user to do CRUD operations on products. Products should be members of logical houses 
(containers)

## Product Requirements
* Easy to change stuff - easy to add, delete, update (CRUD operations). Shouldn't be slower than doing it without the 
app
* Should have scope to scan barcodes and add products based on barcodes (client specification)
* Should track expiry dates and provide warnings
* Should divide products into logical segments
* Should generate shopping lists on demand

## Thanks to
https://www.baeldung.com/  
Jayson Taylor https://jasontaylor.dev/ for inspiration with respect to Clean Archictecture
 