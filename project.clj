(defproject nectar "0.1.0-SNAPSHOT"
  :description "A Honeycheker implementation"
  :url "https://github.com/abedra/nectar"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [org.clojure/data.json "0.2.4"]
                 [com.taoensso/carmine "2.6.2"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler nectar.handler/app})
