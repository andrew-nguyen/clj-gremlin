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
  (run [g] "Added step not from Gremlin to 'run' the traversal, returning a list")

  ; filters
  (has [g k v])

  ; properties
  (value [g])
  (internal-values [g property-keys])
  (internal-properties [g property-keys])

  ; navigation
  (internal-both [g edge-labels])
  (internal-both-e [g edge-labels])

  (internal-in [g edge-labels])
  (internal-in-e  [g edge-labels])

  (internal-out [g edge-labels])
  (internal-out-e [g edge-labels])

  (in-v [g])
  (out-v [g])
  (both-v [g])

  (internal-has-label [g labels])
  )

(defmacro internal-varargs-string
  [fname]
  (let [internal-fname (symbol (str "internal-" (name fname)))]
    `(defn ~fname [g# & args#] (~internal-fname g# (into-array String (mapv name args#))))))

(internal-varargs-string values)
(internal-varargs-string properties)
(internal-varargs-string both)
(internal-varargs-string both-e)
(internal-varargs-string in)
(internal-varargs-string in-e)
(internal-varargs-string out)
(internal-varargs-string out-e)
(internal-varargs-string has-label)

(extend-protocol TraversalSteps
  GraphTraversal
  (run [g] (-> g iterator-seq))

  ; filters
  (has [g k v] (-> g (.has (name k) v)))

  ;properties
  (value [g] (.value g))
  (internal-values [g property-keys] (-> g (.values property-keys)))
  (internal-properties [g property-keys] (-> g (.properties property-keys)))

  ; navigation
  (internal-both [g edge-labels] (-> g (.both edge-labels)))
  (internal-both-e [g edge-labels] (-> g (.bothE edge-labels)))

  (internal-in [g edge-labels] (-> g (.in edge-labels)))
  (internal-in-e  [g edge-labels] (-> g (.inE edge-labels)))

  (internal-out [g edge-labels] (-> g (.out edge-labels)))
  (internal-out-e [g edge-labels] (-> g (.outE edge-labels)))

  (in-v [g] (-> g (.inV)))
  (out-v [g] (-> g (.outV)))
  (both-v [g] (-> g (.bothV)))

  (internal-has-label [g labels] (-> g (.hasLabel labels)))
  )