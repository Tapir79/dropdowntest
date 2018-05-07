(ns dropdowntest.core
  (:require [reagent.core :as reagent :refer [atom]])
  )

(enable-console-print!)

(println "Comments in console")

(defonce app-state (atom {:text "Hello world!"
                          :target-id 1}
                         ))

(def table-contents
  [{:id 1 :first-name "Bram"    :last-name "Moolenaar"  :known-for [:creator "Vim"
                                                                    :studied "Active member of open source community"]}
   {:id 2 :first-name "Richard" :last-name "Stallman"   :known-for [:creator "GNU"
                                                                    :studied "The hacker"]}
   {:id 3 :first-name "Dennis"  :last-name "Ritchie"    :known-for [:creator "C"
                                                                    :studied "Smart"]}
   {:id 4 :first-name "Rich"    :last-name "Hickey"     :known-for [:creator "Clojure"
                                                                    :studied "Curls"]}
   {:id 5 :first-name "Guido"   :last-name "Van Rossum" :known-for [:creator "Python"
                                                                    :studied "Spelling OCD"]}
   {:id 6 :first-name "Linus"   :last-name "Torvalds"   :known-for [:creator "Linux"
                                                                    :studied "The Finn"]}
   {:id 7 :first-name "Yehuda"  :last-name "Katz"       :known-for [:creator "Ember"
                                                                    :studied "Agent"]}])

(defn lister [items]
  [:ul
   (for [item items]
     ^{:key item} [:li "Item " item])])



(defn lister2 [table-contents]
  [:select
                (for [item (range (get-in (last table-contents) [:id]))]
                ^{:key item} [:option {:value item} (get-in (nth table-contents item) [:first-name])
                             ])])

(defn lister-user [table-contents]
  [:div
   "Here is a list:"
   [lister2 table-contents]])

(defn target-info [table-contents]
  [:div
   "Target info"
   (:target-id @app-state)])

(defn hello-world []
  (let [table-contents
        [{:id 1 :first-name "Bram" :last-name "Moolenaar" :known-for [:creator "Vim"
                                                                      :studied ""]}
         {:id 2 :first-name "Richard" :last-name "Stallman" :known-for [:creator "GNU"
                                                                        :studied ""]}
         {:id 3 :first-name "Dennis" :last-name "Ritchie" :known-for [:creator "C"
                                                                      :studied ""]}
         {:id 4 :first-name "Rich" :last-name "Hickey" :known-for [:creator "Clojure"
                                                                   :studied ""]}
         {:id 5 :first-name "Guido" :last-name "Van Rossum" :known-for [:creator "Python"
                                                                        :studied ""]}
         {:id 6 :first-name "Linus" :last-name "Torvalds" :known-for [:creator "Linux"
                                                                      :studied "University of Helsinki"]}
         {:id 7 :first-name "Yehuda" :last-name "Katz" :known-for [:creator "Ember"
                                                                   :studied ""]}]]
  [:div
   [:h1 (:text @app-state)]
   (lister-user table-contents)
   (target-info table-contents)]))

; build a map of options that is passed to select

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )



