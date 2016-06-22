(ns clj-gremlin.core3
  (:import [org.apache.tinkerpop.gremlin.process.traversal.dsl.graph GraphTraversal GraphTraversalSource]
           [org.apache.tinkerpop.gremlin.structure Graph]))

(def empty-string-array (into-array String []))

(defprotocol StartTraversal
  (V [this]))

(extend-protocol StartTraversal
  Graph
  (V [g] (-> g (.traversal) (.V empty-string-array)))

  GraphTraversalSource
  (V [g] (-> g (.V empty-string-array))))


(defprotocol TraversalSteps
  (run [this] "Added step not from Gremlin to 'run' the traversal, returning a list")

  ; filters
  (has [this k v])

  ; properties
  (value [this])
  (internal-values [this property-keys])
  (internal-properties [this property-keys])

  ; navigation
  (internal-both [this edge-labels])
  (internal-bothE [this edge-labels])

  (internal-in [this edge-labels])
  (internal-inE  [this edge-labels])

  (internal-out [this edge-labels])
  (internal-outE [this edge-labels])
  )

(defmacro internal-varargs
  [fname & [clazz]]
  (let [internal-fname (symbol (str "internal-" (name fname)))
        clazz (or clazz Object)]
    `(defn ~fname [g# & args#] (~internal-fname g# (into-array ~clazz args#)))))

(internal-varargs values String)
(internal-varargs properties String)
(internal-varargs both String)
(internal-varargs bothE String)
(internal-varargs in String)
(internal-varargs inE String)
(internal-varargs out String)
(internal-varargs outE String)

(extend-protocol TraversalSteps
  GraphTraversal
  (run [g] (-> g iterator-seq))

  ; filters
  (has [g k v] (-> g (.has k v)))

  ;properties
  (value [g] (.value g))
  (internal-values [g property-keys] (-> g (.values property-keys)))
  (internal-properties [g property-keys] (-> g (.properties property-keys)))

  ; navigation
  (internal-both [g edge-labels] (-> g (.both edge-labels)))
  (internal-bothE [g edge-labels] (-> g (.bothE edge-labels)))

  (internal-in [g edge-labels] (-> g (.in edge-labels)))
  (internal-inE  [g edge-labels] (-> g (.inE edge-labels)))

  (internal-out [g edge-labels] (-> g (.out edge-labels)))
  (internal-outE [g edge-labels] (-> g (.outE edge-labels)))
  )