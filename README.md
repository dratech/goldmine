# Building the application:
With the command **mvn clean install**

---

# Running the application
* With the **mvn spring-boot:run** command 
* Running the jar file in the /target folder with the command **java -jar energy-0.0.1-SNAPSHOT.jar**

---

# Endpoints

## Postman collection with usage examples
	https://www.getpostman.com/collections/660482ae9d98bbb8e290

---

## Customer endpoints:

### GET /customers/

Returns a list of the customers and their address

---

### GET /customers/{id}
	Long id - id of the customer

	Returns a customer with the given is

---

### PUT /customers/add
	Requires a json object with given properties
	name : String 
	address: 
			country : String
			zipcode : Integer
			city : String
			street : String
			house : String

	Registers a customer with the given properties and adds a unused meter to them

	Example:
		"name":"Peter Parker",
		"address":{
			  "country":"USA",
			  "zipcode":3214,
			  "city":"New York",
			  "street":"Avangers Street",
			  "house":"19/B"
		}

---

## Consumptions endpoints

GET /consumptions/customers/{customerId}/year/{year}
	Long customerId - id of the customer
	Integer year - year which the readings belongs to

	Returns a list of the consumptions breaked down by month for that year

---

GET /consumptions/customers/{customerId}/year/{year}/summary
	Long customerId - id of the customer
	Integer year - year which the readings belongs to

	Returns the yearly consumption aggregated

---

GET /consumptions/customers/{id}/year/{year}/month/{month}
	Long customerId - id of the customer
	Integer year - year which the readings belongs to
	Integer month - month which the readings belongs to

	Returns the consumption for that month

---

PUT /consumptions/customers/{customerId}/add
	Requires a json object with given properties
	year : Integer
	month : Integer
	consumption : Long 

	Registers a consumption for the given user
	
	Example: 
		"year":2017,
		"month":5,
		"consumption":12


## Meter endpoints
	
GET /meters/

	Returns a list of the meters

---

GET /meters/{meterId}
	Long meterId - id of the meter
	
	Retruns the meter of the given id
	
PUT /meters/add
	Requires a json object with given properties
	type              : String
	version 		  : String
	yearOfManufacture : Integer

	Example:
		"type" : "DFC",
		"version" : "54C",
		"yearOfManufacture" : 1999 
