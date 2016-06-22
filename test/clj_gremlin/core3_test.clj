(ns clj-gremlin.core3-test
  (:require [expectations :refer [expect more-> more-of]]

            [clj-gremlin.core3 :refer :all])
  (:import (org.apache.tinkerpop.gremlin.tinkergraph.structure TinkerFactory)))

; tests based on the "classic" graph
(def g (TinkerFactory/createClassic))

(expect 6 (-> g V run count))

(expect 1 (-> g V (has "name" "marko") run count))

(expect 29 (-> g V (has "name" "marko") (values "age") run first))

(expect "java" (-> g V (has "name" "lop") (values "lang") run first))

(expect 2 (-> g V (has "lang" "java") run count))

(expect 0 (-> g V (has "name" "lop") outE run count))

(expect "lop" (-> g V (has "name" "marko") (out "created") (values "name") run first))

(expect 3 (-> g V (has "name" "lop") inE run count))

(expect 1.0 (-> g V (has "name" "josh") inE (values "weight") run first))

(expect "marko" (-> g V (has "name" "josh") in (values "name") run first))

(expect #{"vadas" "lop" "josh"} (-> g V (has "name" "marko") out (values "name") run set))

(expect "marko" (-> g V (has "name" "josh") inE outV (values "name") run first))

(expect 32 (-> g V (has "name" "josh") inE inV (values "age") run first))

(expect #{"marko" "josh"} (-> g V (has "name" "josh") inE bothV (values "name") run set))