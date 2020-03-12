### Intro
This is selenium based UI test automation framework for both member and recruiter sides.

### Environment Setup

#### Development Environment

1. [Java](https://en.wikipedia.org/wiki/Java_(programming_language) - is used as the main coding language. 
1. [Maven](https://maven.apache.org/) - is used as build and dependencies management tool.  
1. [Chrome](https://www.google.com/chrome/) - Chrome browser.
1. [ChromeDriver](https://chromedriver.chromium.org/) - standalone server for Chrome browser that implements the W3C WebDriver standard.

#### CI Environment

1. [Docker](https://www.docker.com/) - is used:
    1. spin up [Selenoid](https://aerokube.com/selenoid/) - Selenium Grid
    1. executed tests

### Configuration Options
All the test framework related configs are stored in [config.properties](/ui_tests/src/test/resources/config.properties) file.
In order to overwrite default values either:
* Update the appropriate key in [config.properties](/ui_tests/src/test/resources/config.properties) file.
* Pass the updated key as java environment variable into during tests run with maven. 
E.g. _selenium.browser.isGrid=false_ is set to _false_ in [config.properties](/ui_tests/src/test/resources/config.properties)
but during run on _Jenkins_ we need to use selenium grid so that we are setting it to true by:

```
mvn clean install -Dselenium.browser.isGrid=true
```

### Tests Execution

There are 3 ways of tests execution: fully local, fully dockerized and mixed.
* **Fully local** is the preferred way during tests development as it simplifies debug process.
It requires [Development Environment] environment setup because tests are executed (from IDE or using maven) against
[locally installed Chrome browser](https://www.seleniumhq.org/docs/03_webdriver.jsp#introducing-the-selenium-webdriver-api-by-example).
As it's the default way of running tests (as per the configuration file) no additional parameters are required for running all the tests with maven:
```
mvn clean install
```
* **Fully dockerized** is the preferred way to execute tests as part of CI flow. See [docker-compose.yml](docker-compose.yml)
It requires [CI Environment] environment setup because tests are executed in docker containers.
For the first run related docker containers should be pulled with:
```
docker pull selenoid/chrome:latest 
docker pull selenoid/opera:latest
docker-compose pull
```
In order to execute all the tests:
```
docker-compose down
docker-compose run tests mvn clean install -Dselenium.browser.isGrid=true -DparallelTestsCount=7
docker-compose down
```
* **Mixed** In order to execute tests from local environment using selenium grid.
It requires both [Development Environment] and [CI Environment] setup. In order to run tests:
1. Selenium Grid should be started
2. Grid URL should be set in [config.properties](/ui_tests/src/test/resources/config.properties)
3. isGrid flag should be set to true
After that you will be able to execute tests in the same way as in [Fully local] case but the dockerized browsers will be used.

### Reporting

Generated reports are stored in the following locations:
* cucumber report: _ui_tests/target/cucumber/index.html_
* cucumber timeline report: _ui_tests/target/cucumberTimeline/index.html_
The better implementation of reports is available on CI with [cucumber-reports](https://plugins.jenkins.io/cucumber-reports/) plugin.  

### Screenshots capture

Set _selenium.browser.enableScreenShotsCapture_ flag to _true_ in order to capture screenshots on open of page objects.
They will be stored in: _ui_tests/target/features_ folder

### Libraries

1. [TestNG](https://testng.org/doc/index.html) - tests execution and assertions.
1. [Cucumber](https://docs.cucumber.io/) - behaviour driven development (BDD) support.
1. [Selenium](https://www.seleniumhq.org/) - browser interaction API.


### Troubleshooting Notes

1. If browser doesn't start:
    1. Check if version of chromedriver (_chromedriver(.exe) -v_ in terminal) matches version of inatalled chrome browser
    1. Check if Glue is set to _com.saucedemo_ in _Run/Debug Configurations > Templates > Cucumber java > Glue_ line 
1. Fully Dockerized execution on Windows platform is TBD. Please use Mixed approach.
