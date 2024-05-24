(ns shogidude.pforacle2024.integrations.redis.redis-system
  (:require
    [integrant.repl.state :as state]
    [taoensso.carmine :as car :refer (wcar)]
    ))

(defn redis-connection []
  (:cache/redis state/system))

(defmacro wcar* [& body] `(car/wcar (redis-connection) ~@body))

(defn ping-redis []
  (wcar* (car/ping)))