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

## Testing

### Integration Testing

#### Controller

Used Mockito to mock out the Service layer and called Controller methods

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

#### Integration Tests

```
@RunWith(SpringRunner.class) = ???
@SpringBootTest = ???
@MockBean = ???
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

## Docker

### Dockerfile

This repo contains a Dockerfile example - a Dockerfile is simple a way to run our API inside a container

#### Instructions
```
# Ensure you are in a directory that contains the Dockerfile
ls -l

# We first need to build our image locally on our machine, we will tag the image also
docker build . -t spring-example

# Let's confirm that the image has been built
docker ps -a

# Now let's run the image as a container and expose what port we want to run it on
docker run -p 8080:8080 spring-example
```

### Docker Compose

While a docker file is used to build a container, a docker-compose file provides more flexibility around containers.

Using a docker-compose file, you can scale container, run and stop multiple containers, provide port information etc.

#### Instructions
```
# Ensure you are in a directory that contains both a Dockerfile and docker-compose.yml file
ls -l

# Use docker compose to run all services inside your docker compose file
docker-compose up -d

# Check that your docker containers are running
docker logs --follow <container-id>

# Once done, use docker compose down to remove all running containers
docker-compose down
```

## Mocking

### Mockito

The test in test/java/.../ are using a mocked bean allowing us to mockout the response.

### Wire Mock

We are also using wiremock inside the docker container to help mock out an external api. 
Note that we need to do wireing up inside the container and in the url of the external api endpoint for the wiremock to work.
For Wire Mock to work, I had to add 'networks, links and environment' in the docker-compose file.

#### Instructions
```
# Build the api first
./gradlew clean build

# Use docker compose to run all services inside your docker compose file
docker-compose up -d

# Perform the following curl - this will call the /ext mapping endpoint in the person controller - which in turn will call the mocked endpoint
curl http://localhost:1443/api/v1/person/ext
```