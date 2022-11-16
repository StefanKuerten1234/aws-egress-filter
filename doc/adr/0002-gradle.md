# 2. gradle

Date: 2022-11-16

## Status

Accepted

## Context

We need to build, test and package the project.

## Decision

We will use Gradle because it has a convenient DSL and supports Maven repositories. We have a fairly good grasp on its
use.

## Consequences

Dependencies will have to be declared in build.gradle and we will gain the ability to add custom tasks.