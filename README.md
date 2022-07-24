# reactive-java-benchmarks

How to run tests:

* Either start both Web MVC and WebFlux applications on the same device or on separate machines
* Update the `gatling/.env` file with appropriate device addresses
* Run `docker compose up` inside the `gatling` folder to run load tests

Running the default tests will take 160 minutes in total. The list of tests to run can be updated in `gatling/docker-compose.yml` as part of the `ENDPOINTS` environment variable.

By default, results will be saved in the `/tmp` directory of the Gatling host machine. This can be changed in `gatling/docker-compose.yml` as well.

To get the most accurate results, run the Gatling server on a separate machine.
