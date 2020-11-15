# Spring Boot Framework

## Purpose

The purpose of this project is to provide a quick re-cap on Spring Boot and how it can be used to build Java API's

## Endpoints

```
GET localhost:8080/api/v1/person - returns all persons
POST localhost:8080/api/v1/person - create a new person
PUT localhost:8080/api/v1/person/{id} - update an existing person, must provide a UUID of an existing person
DELETE localhost:8080/api/v1/person/{id} - delete an existing person, must provide a UUID of an existing person
```

Example JSON Request:
```
{
    "name": "Batman"
}
```

## Layers in an API

API / Controller Layer -> Service Layer -> Data Access Layer

The API layer talks to the Service Layer. The Service Layer talks to the Data Access (DAO) Layer
Each layer is responsible for something different and services a different purpose

### API / Controller Layer

This layer contains all the endpoints and is responsible for calling the service layer methods

### Service Layer

This layer provides all the logic for the end points
It also calls the DAO layer for any data that is needed

### DAO Layer

Calls any Repositories (DB's) to perform CRUD operations

## Tags

Here is a list of some useful tags at a high level
When using the following class level tags, an instance of that class is created by Spring
These instances are called Beans and used when trying to Autowire them

### Class Level

#### API / Controller Layer

```
@RestController = tag allows us to use methods in this class exposed as REST endpoints
@RequestMapping("path') = allows us to tell Spring what endpoint this rest controller is listening to
```

#### Service Layer

```
@Service = tag to tell Spring that this is a service
```

#### DAO Layer

```
@Repository = tag to tell Spring that this class needs to be instantiated as a bean for other classes
@Repository("name) = can optionally have names if multiple instances exist
```

#### Other

```
@Component = Can use this tag instead of @RestController, @RequestMapping, @Repository
```

### Method Level

```
@PostMapping = used to annotate a method as a post endpoint
@GetMapping  = used to annotate a method as a get endpoint
@GetMapping(path = "{id}") = can optionally pass URL path parameter to hit subdomain of an endpoint
@Autowired = tag that looks for a bean and if it exists then injects it
```

### Method Signature level

```
@RequestBody = parameter tag that tells Spring to convert request body into Java object
@PathVariable("id") = parameter tag that tells spring to pull in a url parameter that is passed in an endpoint and convert to Java object
@JsonProperty  = parameter tag that is used to identify JSON property and maps to java method paramter
@Qualifier = if you autowire an interface, then you must provide some identifier
```

## Responsibility

### Controller

A controller class should only contain all the endpoints needed

### Service

A service class should contain all the logic needed to perform (business rules)

### DAO

A DAO class is responsible to perform CRUD operations on data
