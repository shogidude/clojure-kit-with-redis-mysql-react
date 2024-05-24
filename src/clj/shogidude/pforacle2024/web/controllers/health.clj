(ns shogidude.pforacle2024.web.controllers.health
  (:require
    [ring.util.http-response :as http-response]
    [shogidude.pforacle2024.integrations.db.system-queries :as sq]
    [shogidude.pforacle2024.integrations.redis.redis-system :as rs]
    )
  (:import
    [java.util Date]))

(defn healthcheck!
  [req]

  (let [
        redis-connected (if (= "PONG" (rs/ping-redis)) true false)
        database-connected (if (sq/show-db-status) true false)
        up-status (if (and redis-connected database-connected) "up" "down")
        ]




    (http-response/ok
      {:time     (str (Date. (System/currentTimeMillis)))
       :up-since (str (Date. (.getStartTime (java.lang.management.ManagementFactory/getRuntimeMXBean))))
       :clojure-kit-server up-status
       :redis-connected  redis-connected
       :mysql-connected database-connected
       })))



