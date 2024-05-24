# Clojure Kit Framework with Redis, MySQL, ReactJS and RESTful APIs

The developers over at [Kit](https://kit-clj.github.io/) have done a great job setting up a Clojure-specific, opinionated web application framework. Absolutely, my favorite web server framework.

Having said that, currently there are some missing examples and documentation for Redis, MySQL and ReactJS setups. I really don't have the time to invest in making modules or profiles for the project. However, I do have time to present a fully working version of Kit under the MIT license with the raw Redis, MySQL, RESTful APIs, nREPL, migrations and React fully integrated.

Some of the code here is probably under other licenses than MIT. I have left all code licenses in place if they were shipped with a license. Check with the original creator of React and other libraries for their current licensing.

![alt Health Status component in React page](https://raw.githubusercontent.com/shogidude/clojure-kit-with-redis-mysql-react/main/readme_healthstatus.png)

## Reason and History of this Project

You will notice references to 'shogidude.pforacle2024' sprinkled around the code. That is because I derived this code from that project. Also, the React client code is in 'gmclient', short for Game Master Client. There are probably other references I'm not aware of. Replace those references with your own project names, but don't forget to modify paths and namespaces when needed.

I was working on my [backrooms.net PFOracle](https://backrooms.net) web application for playing RPGs (especially Pathfinder) solo, and realized my custom configuration of the Clojure Kit web framework wasn't really documented any where. Since I strongly believe that if you use opensource software you should give back to the community, I felt I should release this setup for developers that needed some help setting up their Clojure web server.

Some of the things (currently) missing for Clojure Kit, but found here include: raw Redis connections and interactions; Mysql setup with a connection pool; and a deployable React client set up with the ability to use 'npm start' so that you can develop on the server's REPL and the client's React code at the same time.

Starting with React, their is a component built in with this deployment that uses 'fetch' to grab the Clojure Kit server's current health status. (That's the image above.)

## What you'll need

You will need a running copy of MySQL. For development, I recommend 'localhost' with the standard port. You will find the connection string with username and password in 'resources/system.edn'. DON'T FORGET TO URL ENCODE THE PASSWORD. It will cause you major headaches if you forget.

You will need a running copy of Redis. For this version, I don't have a password setup. I have set up a password on other projects using this library. It's very doable. If you can't figure it out, drop me message, and if I have time, I'll see if I can help. Look in 'resources/system.edn' for the Redis configurations.

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
