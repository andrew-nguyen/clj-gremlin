(defproject clj-gremlin "2.4.0-ALPHA-1-SNAPSHOT"
  :description "Implementation of TinkerPop Gremlin 2.0"
  :url "https://github.com/olabini/gremlin-clj"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.tinkerpop.gremlin/gremlin-java "2.4.0"]
                 [com.tinkerpop.blueprints/blueprints-core "2.4.0"]]
  :aot [clj-gremlin.pipeline]

;  :warn-on-reflection true
  
  :repositories [["bd-snapshots" {:url "s3p://bd-deps/snapshots/"
                                  :username ~(System/getenv "LEIN_USERNAME")
                                  :passphrase ~(System/getenv "LEIN_PASSPHRASE")}]]

  :plugins [[s3-wagon-private "1.1.2"]])
