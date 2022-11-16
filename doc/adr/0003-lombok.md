# 3. lombok

Date: 2022-11-16

## Status

Accepted

## Context

We want to reduce boilerplate code.

## Decision

We will use Lombok.

## Consequences

Lombok annotations will reduce the boilerplate code we'll have to write significantly. Also, we'll gain easy access to
an implementation of the Builder-Pattern. However, we need to be aware that some tools (i.e. Jackson) won't work well or
not at all (i.e. CodeQL) with it and that it introduces an additional external dependency.
