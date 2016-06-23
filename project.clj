(defproject andrew-nguyen/clj-gremlin "3.1.1"
  :description "Implementation of TinkerPop Gremlin 3.x"
  :url "https://github.com/andrew-nguyen/gremlin-clj"

  :dependencies [[org.clojure/clojure "1.9.0-alpha5"]
                 [org.apache.tinkerpop/gremlin-core "3.1.1-incubating"]
                 [org.apache.tinkerpop/tinkergraph-gremlin "3.1.1-incubating"]]

  :profiles {:dev {:dependencies [[expectations "2.1.8"]]
                   :plugins      [[lein-autoexpect "1.9.0"]
                                  [lein-expectations "0.0.8"]]}}

  ;:aot [clj-gremlin.pipeline]

  :repl-options {:init-ns user}

  ;  :warn-on-reflection true
  )
