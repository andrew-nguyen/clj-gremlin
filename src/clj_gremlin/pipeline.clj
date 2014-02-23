(ns clj-gremlin.pipeline
  (:import (com.tinkerpop.blueprints Element)
           (com.tinkerpop.pipes.transform PropertyMapPipe))
  (:gen-class
   :name clj-gremlin.pipeline.GremlinClojurePipeline
   :extends com.tinkerpop.gremlin.java.GremlinPipeline
   :implements [clojure.lang.ILookup]
   :methods [[keywordMap ["[Ljava.lang.String;"] clj-gremlin.pipeline.GremlinClojurePipeline]]
   :constructors {[Object] [Object]}))

(defn property-map-pipe [f prop-keys]
  (proxy [PropertyMapPipe] [prop-keys]
    (processNextStart []
      (let [result (proxy-super processNextStart)]
        (into {} (map (fn [[k v]] [(f k) v]) result))))))

(defn -valAt [p k]
  (.property p (name k)))

(defn -map [p prop-keys]
  (.add p (property-map-pipe identity prop-keys)))

(defn -keywordMap [p prop-keys]
  (.add p (property-map-pipe keyword prop-keys)))
