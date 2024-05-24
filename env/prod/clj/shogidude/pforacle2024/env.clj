(ns shogidude.pforacle2024.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[pforacle2024 starting]=-"))
   :start      (fn []
                 (log/info "\n-=[pforacle2024 started successfully]=-"))
   :stop       (fn []
                 (log/info "\n-=[pforacle2024 has shut down successfully]=-"))
   :middleware (fn [handler _] handler)
   :opts       {:profile :prod}})
