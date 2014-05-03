(ns nectar.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as ring]
            [clojure.data.json :as json]
            [taoensso.carmine :as redis :refer (wcar)]))

(defmacro wcar*
  [& body]
  `(redis/wcar {:pool {} :spec {}} ~@body))

(defn check
  [id index]
  (if (= (wcar* (redis/get id)) index)
    (json/write-str {:result true})
    (json/write-str {:result false})))

(defn create
  [id index]
  (wcar* (redis/set id index))
  (json/write-str {:result "success"}))

(defroutes app-routes
  (POST "/" [id index] (check id index))
  (POST "/create" [id index] (create id index))
  (route/not-found "Not Found"))

(def app
  (handler/api app-routes))
