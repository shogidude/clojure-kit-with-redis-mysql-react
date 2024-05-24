# Clojure Kit Framework with Redis, MySQL, ReactJS and RESTful APIs

The developers over at [Kit](https://kit-clj.github.io/) have done a great job setting up a Clojure-specific, opinionated web application framework. Absolutely, my favorite web server framework. (Some of this readme is left in place from their orginal README file. You can compare their project with this to see which text.)

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

## Notable Files and Configurations

### MySQL, SQL, and Migrations

You will find the SQL for the server hiding in 'resources/sql/queries'. The function for calling the sample SQL is found in 'shogidude.pforacle2024.integrations.db.system-queries'. 

My use of integrant is not great. Forgive me :-)

All the SQL is converted automagically to clojure functions that are retrievable using the query-fn and the SQL name as a key. So, retieving the SQL named 'show-status' is done with the line of code ... '((query-fn) :show-status {})'.

Migrations, both up and down, work just like the Kit website explains. Also, the SQL in 'queries.sql' works the same as the Kit website explains.

### Raw Redis

Currently, the Kit website explains caching using Redis, but isn't extremely clear on how to use Redis all by itself. Some features of Redis have nothing to do with caching, after all.

The developers over at Kit made a great decision using Carmine for their Redis integrations. You can pretty much do anything that Redis allows with Carmine. However, you might need a little help figuring out how to use Redis directly in your Kit web application.

Check out the 'shogidude.pforacle2024.integrations.redis.redis-system' namespace. Specifically, you want to use the following code to get Kit and Carmine playing nice.

<code><pre>(ns shogidude.pforacle2024.integrations.redis.redis-system
  (:require
    [integrant.repl.state :as state]
    [taoensso.carmine :as car :refer (wcar)]
    ))

(defn redis-connection []
  (:cache/redis state/system))

(defmacro wcar* [& body] `(car/wcar (redis-connection) ~@body))

(defn ping-redis []
  (wcar* (car/ping)))</pre></code>

Again, I'm new to Integrant, so there might be better ways of getting (:cache/redis state/system) for your connection. The rest of the code is easily understood when you read through the Carmine Redis library's documentation.

### React Client

React, often refered to as ReactJS, works great with the Clojure Kit web framework. However, if you are newish to Kit or React, it might be a bit of a challenge getting your server and your development environment set up.

The first trick is to create your React project inside of your Kit project. Your React client becomes a sub-project of your Clojure Kit project.

Next, you need a deploy script. The current one in this project is made for *nix computers including Apple computers. You can easily modify it if you work on a Windows box. Just ask one of the many decent LLMs for help if you get stuck. 

You will find the delpoyment script in, 'gmclient/package.json'. <strong>CAUTION:</strong> <em>The React 'deploy' script deletes the resources/public directory every time it is run, so don't get too attached to anything in that directory.</em>

The other trick is setting up the React project to allow 'npm start' based development to take place while you develop with your running Clojere Kit project. First, I changed the port that 'npm start' runs on to 3002. Next, I added the proxy setting to 'package.json' so that fetching relative paths would work. See the fetch example in HealthStatus.jsx, if you want to see it in action.

## Running and Developing in Dev Mode

For Clojure's Kit, cd to your Kit project root, and start a [REPL](#repls) in your editor or terminal of choice using the following command for development servers.

```
clj -M:dev
```

Start the Clojure Kit server with:

```clojure
(go)
```

Stop the Clojure Kit server with:

```clojure
(halt)
```

The default API is available under http://localhost:3000/api

System configuration is available under `resources/system.edn`.

To reload changes:

```clojure
(reset)
```

For React, cd to your React project root and use the standard 'npm start'

## REPLs

### Cursive

Configure a [REPL following the Cursive documentation](https://cursive-ide.com/userguide/repl.html). Using the default "Run with IntelliJ project classpath" option will let you select an alias from the ["Clojure deps" aliases selection](https://cursive-ide.com/userguide/deps.html#refreshing-deps-dependencies).

### CIDER

Use the `cider` alias for CIDER nREPL support (run `clj -M:dev:cider`). See the [CIDER docs](https://docs.cider.mx/cider/basics/up_and_running.html) for more help.

Note that this alias runs nREPL during development. To run nREPL in production (typically when the system starts), use the kit-nrepl library through the +nrepl profile as described in [the documentation](https://kit-clj.github.io/docs/profiles.html#profiles).

### Command Line

Run `clj -M:dev:nrepl` or `make repl`.

Note that, just like with [CIDER](#cider), this alias runs nREPL during development. To run nREPL in production (typically when the system starts), use the kit-nrepl library through the +nrepl profile as described in [the documentation](https://kit-clj.github.io/docs/profiles.html#profiles).
