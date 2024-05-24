(ns shogidude.pforacle2024.core
  (:require
    [clojure.tools.logging :as log]
    [integrant.core :as ig]
    [shogidude.pforacle2024.config :as config]
    [shogidude.pforacle2024.env :refer [defaults]]

    ;; Edges 
    [kit.edge.cache.redis] 
    [kit.edge.db.sql.conman]
    [kit.edge.db.sql.migratus]
    [kit.edge.db.postgres]
    [kit.edge.db.mysql]  
    [kit.edge.templating.selmer]   
    [kit.edge.utils.nrepl]
    [kit.edge.server.undertow]
    [shogidude.pforacle2024.web.handler]

    ;; Routes
    [shogidude.pforacle2024.web.routes.api]
    
    [shogidude.pforacle2024.web.routes.pages])
  (:gen-class))

;; log uncaught exceptions in threads
(Thread/setDefaultUncaughtExceptionHandler
  (reify Thread$UncaughtExceptionHandler
    (uncaughtException [_ thread ex]
      (log/error {:what :uncaught-exception
                  :exception ex
                  :where (str "Uncaught exception on" (.getName thread))}))))

(defonce system (atom nil))

(defn stop-app []
  ((or (:stop defaults) (fn [])))
  (some-> (deref system) (ig/halt!))
  (shutdown-agents))

(defn start-app [& [params]]
  ((or (:start params) (:start defaults) (fn [])))
  (->> (config/system-config (or (:opts params) (:opts defaults) {}))
       (ig/prep)
       (ig/init)
       (reset! system))
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app)))

(defn -main [& _]
  (start-app))
