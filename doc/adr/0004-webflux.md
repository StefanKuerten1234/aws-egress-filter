# 4. webflux

Date: 2022-11-16

## Status

Accepted

## Context

We need to respond to HTTP requests from users and query a HTTP API.

## Decision

We will use Spring WebFlux for both cases instead of WebMVC and RestTemplate, which is deprecated. 

## Consequences

Web requests will be handled more efficiently because the reactive stack is non-blocking and we are more future-proof
by not using deprecated technology. However, it is easy to break the reactive nature of an app by introducing blocking
code and the learning curve for the reactive paradigm is rather steep.
