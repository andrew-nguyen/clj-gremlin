(ns user
  (:require [clj-gremlin.core3 :refer :all])
  (:import [org.apache.tinkerpop.gremlin.tinkergraph.structure TinkerFactory]))

(def g (TinkerFactory/createClassic))
