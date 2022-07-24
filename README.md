# reactive-java-benchmarks

How to run tests:

* Either start both Web MVC and WebFlux applications on the same device or on separate machines
* Update the `gatling/.env` file with appropriate device addresses
* Run `docker compose up` inside `gatling` folder to run default load tests

By default, testing will take 160 minutes in total. The list of tests can be updated in `gatling/docker-compose.yml` as part of the `ENDPOINTS` system variable.

To get the most accurate results, run the Gatling server on a separate machine.
