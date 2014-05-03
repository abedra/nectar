# nectar

nectar is a honeychecker implementation based on the
[Honeywords Project](http://people.csail.mit.edu/rivest/honeywords/)
by Ari Juels and Ronald Rivest. This is a standalone service that can
be called by an authentication routine which will perform the lookup
and return if the provided sweetword is the correct password.

## Prerequisites

* [Leiningen] 1.7.0 or above https://github.com/technomancy/leiningen
* [Redis] 2.4.0 or above https://redis.io

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 Aaron Bedra
