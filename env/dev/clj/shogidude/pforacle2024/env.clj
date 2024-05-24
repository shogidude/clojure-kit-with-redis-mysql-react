(ns shogidude.pforacle2024.env
  (:require
    [clojure.tools.logging :as log]
    [shogidude.pforacle2024.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[pforacle2024 starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[pforacle2024 started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[pforacle2024 has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})
