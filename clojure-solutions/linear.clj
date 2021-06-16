(defn vec? [vec] (and (vector? vec) (every? number? vec)))
(defn sizes-equal? [vectors] (apply = (mapv count vectors)))
(defn matr? [matr] (and (vector? matr) (every? vec? matr) (sizes-equal? matr)))
(defn tensor? [tensor]
  (or
    (number? tensor)
    (every? number? tensor)
    (and (every? vector? tensor) (sizes-equal? tensor) (tensor? (apply concat [] tensor)))))

(defn elem-operation [check-function]
  (fn [f]
    (letfn [(recursive-operation [& elems]
      {:pre [(or (every? number? elems) (sizes-equal? elems))]}
        (if (every? number? elems)
          (apply f elems)
          (apply mapv recursive-operation elems)))]
      (fn [& args]
        {:pre [(every? check-function args)]}
        (apply recursive-operation args)))))


(def vec-operation (elem-operation vec?))
(def v+ (vec-operation +))
(def v- (vec-operation -))
(def v* (vec-operation *))
(def vd (vec-operation /))

(defn scalar [& vectors]
  {:pre [(every? vec? vectors) (sizes-equal? vectors)]}
  (apply + (apply v* vectors)))

(defn coord* [vec1 vec2 c1 c2] (- (* (nth vec1 c1) (nth vec2 c2)) (* (nth vec1 c2) (nth vec2 c1))))
(defn vect [& vectors]
  {:pre [(every? vec? vectors) (sizes-equal? (conj vectors [1, 2, 3]))]}
  (reduce #(vector (coord* %1 %2 1 2) (coord* %1 %2 2 0) (coord* %1 %2 0 1)) vectors))

(defn v*s [vec & scalars]
  {:pre [(vec? vec) (every? number? scalars)]}
  (let [prod (apply * scalars)] (mapv (partial * prod) vec)))

(def matr-operation (elem-operation matr?))
(def m+ (matr-operation +))
(def m- (matr-operation -))
(def m* (matr-operation *))
(def md (matr-operation /))

(defn transpose [matr] {:pre [(matr? matr)]} (apply mapv vector matr))
(defn m*s [matr & scalars]
  {:pre [(matr? matr) (every? number? scalars)]}
  (let [prod (apply * scalars)] (mapv #(v*s % prod) matr)))
(defn m*v [matr vec]
  {:pre [(vec? vec) (matr? matr) (= (count vec) (count (first matr)))]}
  (mapv (partial scalar vec) matr))
(defn m*m [& matrices]
  {:pre [(every? matr? matrices)]}
  (reduce (fn [a b] (mapv #(m*v (transpose b) %) a)) matrices))

(defn shape [tensor] {:pre [(not= tensor [])]} (if (number? tensor) [] (cons (count tensor) (shape (first tensor)))))

(defn broadcast? [sh1 sh2]
  (if (or (empty? sh1) (empty? sh2))
    true
    (and (broadcast? (rest sh1) (rest sh2)) (= (first sh1) (first sh2)))))

(defn copy-tensor [sh diff tensor]
  (cond
    (> (count sh) diff) (mapv #(copy-tensor (rest sh) diff %) tensor)
    (empty? sh) tensor
    :else (mapv #(copy-tensor (rest sh) (- diff 1) %) (repeat (first sh) tensor))))

(defn broadcast [t1 t2]
  {:pre [(tensor? t1) (tensor? t2) (broadcast? (shape t1) (shape t2))]}
  (let [sh1 (shape t1) sh2 (shape t2) l1 (count sh1) l2 (count sh2)]
    (cond
      (= l1 l2) (vector t1 t2)
      (> l1 l2) (vector t1 (copy-tensor sh1 (- l1 l2) t2))
      :else (vector (copy-tensor sh2 (- l2 l1) t1) t2))))

(defn broadcast-many [tensors]
  (mapv
    (fn [tens]
      (first (broadcast tens (apply max-key #(count (shape %)) tensors)))) tensors))

(defn tb-operation [operation]
  (fn [& tensors]
    (apply ((elem-operation tensor?) operation) (broadcast-many tensors))))
(def tb+ (tb-operation +))
(def tb- (tb-operation -))
(def tb* (tb-operation *))
(def tbd (tb-operation /))
