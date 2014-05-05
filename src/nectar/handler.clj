(ns nectar.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as ring]
            [clojure.data.json :as json]
            [taoensso.carmine :as redis :refer (wcar)]))

(defmacro with-connection
  [& body]
  `(redis/wcar {:pool {} :spec {}} ~@body))

(defn check
  [id index]
  (if (= (with-connection (redis/get id)) index)
    (json/write-str {:result true})
    (json/write-str {:result false})))

(defn create
  [id index]
  (with-connection (redis/set id index))
  (json/write-str {:result "success"}))

(defroutes app-routes
  (POST "/" [id index] (check id index))
  (POST "/create" [id index] (create id index))
  (route/not-found "Not Found"))

(def app
  (handler/api app-routes))
