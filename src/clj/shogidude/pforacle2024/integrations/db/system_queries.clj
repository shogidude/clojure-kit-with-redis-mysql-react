(ns shogidude.pforacle2024.integrations.db.system-queries
  (:require [integrant.repl.state :as state]))

(defn query-fn []
  (:db.sql/query-fn state/system))

(defn show-db-status []
  ((query-fn) :show-status {}))
