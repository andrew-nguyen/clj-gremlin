(ns clj-gremlin.pipeline
  (:import (com.tinkerpop.blueprints Element)
           (com.tinkerpop.pipes.transform PropertyMapPipe))
  (:gen-class
   :name clj-gremlin.pipeline.GremlinClojurePipeline
   :extends com.tinkerpop.gremlin.java.GremlinPipeline
   :implements [clojure.lang.ILookup]
   :constructors {[Object] [Object]}))

(defn property-map-pipe [prop-keys]
  (proxy [PropertyMapPipe] [prop-keys]
    (processNextStart []
      (let [result (proxy-super processNextStart)]
        (into {} (map (fn [[k v]] [(keyword k) v]) result))))))

(defn -valAt [p key]
  (.property p (name key)))

(defn -map [p prop-keys]
  (.add p (property-map-pipe prop-keys)))
