# AWS Egress Filter
Web Application that delivers all current AWS egress IP addresses, filtered by region

## Task definition
• A Spring Boot Application is created

• The application has a REST controller and lists the expected data

• The datasource is public and readable from here: https://ip-ranges.amazonaws.com/ip-ranges.json

• The REST controller takes URL query parameter like "?region=EU" or "?region=US" or "?region=ALL" to filter the data (valid regions are EU, US, AP, CN, SA, AF, CA)

• The data is presented as MIME type text/plain

• Each value is shown as single line in the output

• The data (from AWS ip-ranges.json) is not stored

• The code should be stored in github


