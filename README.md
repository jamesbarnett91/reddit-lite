# reddit-lite
Available at [reddit.jamesbarnett.io](https://reddit.jamesbarnett.io)

A lightweight, minimal, readonly Reddit client, designed for mobile devices or slow connections.
![comments](https://jamesbarnett.io/files/reddit-lite/screenshots/rl3.png)

## Running locally
Reddit-lite is written in Kotlin using the [Spring Boot](https://spring.io/projects/spring-boot) framework.
You will need Java 8 or later to run it.

```sh
git clone https://github.com/jamesbarnett91/reddit-lite && cd reddit-lite
./gradlew run
```
Alternatively, you can pull the `jbarnett/reddit-lite` docker image and run that.
```sh
docker run --name reddit-lite -d -p 8080:8080 jbarnett/reddit-lite
```
Then navigate to `localhost:8080` in your browser.

Or go to [reddit.jamesbarnett.io)](https://reddit.jamesbarnett.io) for a hosted version.

## TODOs
* ~~option to hide thumbnails~~ [done]
* ~~collapsible comments~~ [done]
* async load of deeply nested comments
* sort posts/comments by top/hot/new
* view subreddit info/sidebar
* highlight gilded posts/comments
* highlight comments from OP
* should probably write some tests...
