# AWS Egress Filter
Web Application that delivers all current AWS egress IP addresses, filtered by region

## Prerequisites
* JDK >= 17

## Usage
### Run locally
After starting the application locally with
```shell
./gradlew bootRun
```
it will run on port 8080, listening to connections (Make sure the port is not in use!)
Egress IP addresses of AWS can be fetched by calling
```shell
curl "http://localhost:8080?region=ALL"
```
Valid regions are EU, US, AP, CN, SA, AF, CA


### Run tests
```shell
./gradlew test
```


## Task definition
• A Spring Boot Application is created

• The application has a REST controller and lists the expected data

• The datasource is public and readable from here: https://ip-ranges.amazonaws.com/ip-ranges.json

• The REST controller takes URL query parameter like "?region=EU" or "?region=US" or "?region=ALL" to filter the data (valid regions are EU, US, AP, CN, SA, AF, CA)

• The data is presented as MIME type text/plain

• Each value is shown as single line in the output

• The data (from AWS ip-ranges.json) is not stored

• The code should be stored in github


