# Clojure Kit Framework with Redis, MySQL, ReactJS and RESTful APIs

The developers over at [Kit](https://kit-clj.github.io/) have done a great job setting up a Clojure-specific, opinionated web application framework. Absolutely, my favorite web server framework.

Having said that, currently there are some missing examples and documentation for Redis, MySQL and ReactJS setups. I really don't have the time to invest in making modules or profiles for the project. However, I do have time to present a fully working version of Kit under the MIT license with the Redis, MySQL, RESTful APIs, nREPL and React fully integrated.

Some of the code here is probably under other licenses than MIT. I have left all code licenses in place if they were shipped with a license. Check with the original creator of React and other libraries for their current licensing.

![alt Health Status component in React page](https://raw.githubusercontent.com/shogidude/clojure-kit-with-redis-mysql-react/main/readme_healthstatus.png)

## What you'll need

You will need a running copy of MySQL. For development, I recommend 'localhost' with the standard port. You will find the connection string with username and password in 'resources/system.edn'. DON'T FORGET TO URL ENCODE THE PASSWORD. It will cause you major headaches if you forget.

You will need a running copy of Redis. For this version, I don't have a password setup. I have set up a password on other projects using this library. It's very doable. If you can't figure it out, drop me message, and if I have time, I'll see if I can help.

You will need all the Node and npm goodies installed. Look up the first few steps of creating React projects. Those instructions almost always explain the process.

## Running and Developing in Dev Mode

Start a [REPL](#repls) in your editor or terminal of choice.

Start the server with:

```clojure
(go)
```

The default API is available under http://localhost:3000/api

System configuration is available under `resources/system.edn`.

To reload changes:

```clojure
(reset)
```

## REPLs

### Cursive

Configure a [REPL following the Cursive documentation](https://cursive-ide.com/userguide/repl.html). Using the default "Run with IntelliJ project classpath" option will let you select an alias from the ["Clojure deps" aliases selection](https://cursive-ide.com/userguide/deps.html#refreshing-deps-dependencies).

### CIDER

Use the `cider` alias for CIDER nREPL support (run `clj -M:dev:cider`). See the [CIDER docs](https://docs.cider.mx/cider/basics/up_and_running.html) for more help.

Note that this alias runs nREPL during development. To run nREPL in production (typically when the system starts), use the kit-nrepl library through the +nrepl profile as described in [the documentation](https://kit-clj.github.io/docs/profiles.html#profiles).

### Command Line

Run `clj -M:dev:nrepl` or `make repl`.

Note that, just like with [CIDER](#cider), this alias runs nREPL during development. To run nREPL in production (typically when the system starts), use the kit-nrepl library through the +nrepl profile as described in [the documentation](https://kit-clj.github.io/docs/profiles.html#profiles).
