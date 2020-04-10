# reddit-lite
Demo [here](https://reddit.james-barnett.net)

A lightweight, minimal, readonly Reddit client, designed for mobile devices or slow connections.
![comments](https://james-barnett.net/files/reddit-lite/screenshots/rl3.png)

## Running locally
Reddit-lite is written in [play](https://www.playframework.com/).
Running a non distribution version requires [sbt](http://www.scala-sbt.org/index.html) (which sucks btw).

```sh
git clone https://github.com/jamesbarnett91/reddit-lite && cd reddit-lite
mv conf/application.conf.sample conf/application.conf
sbt run
```
Alternatively, just use IntelliJ with the scala plugin and import the project.

## TODOs
* ~~option to hide thumbnails~~ [done]
* ~~collapsible comments~~ [done]
* async load of deeply nested comments
* sort posts/comments by top/hot/new
* view subreddit info/sidebar
* highlight gilded posts/comments
* highlight comments from OP
* clean up css and maybe inline it to save an http request
* should probably write some tests...
