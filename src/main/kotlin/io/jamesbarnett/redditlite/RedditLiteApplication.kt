package io.jamesbarnett.redditlite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedditLiteApplication

fun main(args: Array<String>) {
	runApplication<RedditLiteApplication>(*args)
}
