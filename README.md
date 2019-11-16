### Demo E2E tests for web app and RESTful apis
This is a serenity framework with cucumber demo tests for web application (Selenium tests) and RESTful APIs.
For selenium tests, currently there are only Firefox and Chrome drivers for Mac. One can include drivers for Linux or Windows OS.

Firefox version: `70.0.1`
Gecko driver version: `geckodriver-v0.26.0-macos.tar.gz`

Chrome version: `78.0.3904.97`
Chrome driver version: `ChromeDriver 78.0.3904.70`

#### Selenium tests 
* 2 tests for `https://wikipedia.org` with tags `@web` `@wikipedia`
* 1 test for `https://travelex.co.uk` with tags `@web` `@travelex`

All the selenium tests are in: 
`/src/test/resources/features/web`

Note: Default driver is Firefox and is specified in serenity.properties. 
This can be overridden with command line parameter like `-Dwebdriver.driver=chrome`.


#### RESTful API tests
BaseUrl for Fake API endpoint: 
`http://jsonplaceholder.typicode.com/`

* 8 tests for `/posts` endpoint with tags `@rest` `@fake-api` `@posts`
* 2 tests for `/comments` endpoint with tags `@rest` `@fake-api` `@comments`

All the rest tests are in: 
`/src/test/resources/features/rest`

#### Running tests
* To run all the tests (Selenium - on default browser is Firefox, and API tests)
`mvn clean verify`
* To run all the tests (Selenium - on chrome, and API tests)
`mvn clean verify -Dwebdriver.driver=chrome`
* To run tests with specific tags e.g. `@rest` 
`mvn clean verify -Dcucumber.options="--tags @rest"`





