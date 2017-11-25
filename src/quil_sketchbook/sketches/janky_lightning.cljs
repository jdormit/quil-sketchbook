(ns quil-sketchbook.sketches.janky-lightning
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def num-lines 20)

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb 360 100 100)
  {:points (vec
            (for [i (range (+ num-lines 1))]
              [(* i (/ (q/width) num-lines))
               (+ (/ (q/height) 2)
                  (* (q/random-gaussian) 30))]))})

(defn update-state [state]
  ;; Update sketch state
  {:points (map (fn [point]
                  [(first point)
                   (+ (/ (q/height) 2)
                      (* (q/random-gaussian) 30))])
                (:points state))})

(defn draw-state [state]
  (q/background 293 76 90)
  (q/stroke 123 77 92)
  (q/stroke-weight 5)
  (doseq [i (range num-lines)]
    (let [p1 (nth (:points state) i)
          p2 (nth (:points state) (+ i 1))]
      (q/line p1 p2))))

(q/defsketch janky-lightning
  :host "sketch"
  :size [768 768]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])
