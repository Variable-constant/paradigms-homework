(defn variable [name] (fn [variables] (variables name)))
(def constant constantly)

(defn operation [f] (fn [& args] (fn [variables] (apply f (mapv #(% variables) args)))))

(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(defn my-divide
  ([elem] (/ 1 (double elem)))
  ([elem & rest] (reduce #(/ (double %1) (double %2)) elem rest)))
(def divide (operation my-divide))
(def negate subtract)
(defn count-mean [& args] (/ (apply + args) (count args)))
(defn square [a] (* a a))
(def mean (operation count-mean))
(def varn
  (operation
    #(- (apply count-mean (mapv square %&)) (let [prod (apply count-mean %&)] (square prod)))))

(def op-to-func
  {
   '+ add
   '* multiply
   '- subtract
   '/ divide
   'negate negate
   'mean mean
   'varn varn})

(def variables {'x (variable "x") 'y (variable "y") 'z (variable "z")})

(defn parse-source [source]
  (cond
    (list? source) (apply (op-to-func (first source)) (mapv parse-source (rest source)))
    (number? source) (constant source)
    (contains? variables source) (variables source)))

(defn parseFunction [string] (parse-source (read-string string)))



(load-file "proto.clj")
(def evaluate (method :evaluate))
(def diff (method :diff))
(def toString (method :toString))
(def toStringInfix (method :toStringInfix))
(defn ExpressionPrototype [evaluation to-string differentiation to-infix]
  {:evaluate evaluation
   :diff differentiation
   :toString to-string
   :toStringInfix to-infix})

(def Constant
  (let [_value (field :value)]
    (constructor
      (fn [this value] (assoc this :value value))
      (ExpressionPrototype
        (fn [this _] (_value this))
        (fn [this] (format "%.1f" (double (_value this))))
        (fn [_ _] (Constant 0))
        (fn [this] (format "%.1f" (double (_value this))))))))

(def one (Constant 1))
(def zero (Constant 0))
(def two (Constant 2))

(def Variable
  (let [_variable (field :variable) _str (field :str)]
    (constructor
      (fn [this variable] (assoc this :variable (clojure.string/lower-case (subs variable 0 1)) :str variable))
      (ExpressionPrototype
        (fn [this args] (args (_variable this)))
        _str
        (fn [this d] (if (= d (_variable this)) one zero))
        _str))))

(defn OperationCtor [operation diff-child symbol]
  (let [_operands (field :operands)]
    (constructor
      (fn [this & operands] (assoc this :operands operands))
      (ExpressionPrototype
        (fn [this args] (apply operation (mapv #(evaluate % args) (_operands this))))
        (fn [this] (str "(" symbol " " (clojure.string/join " " (mapv toString (_operands this))) ")"))
        (fn [this d] (diff-child (_operands this) (mapv #(diff % d) (_operands this))))
        (fn [this] (if (= (count (_operands this)) 1)
                     (str symbol "(" (toStringInfix (first (_operands this))) ")")
                     (str "(" (clojure.string/join (str " " symbol " ") (mapv toStringInfix (_operands this))) ")")))))))

(def Negate (OperationCtor - (fn [_ dops] (apply Negate dops)) "negate"))
(def Add (OperationCtor + (fn [_ dops] (apply Add dops)) "+"))
(def Subtract (OperationCtor - (fn [_ dops] (apply Subtract dops)) "-"))
(declare Multiply)
(defn tricky-mul [operands dops]
  (println (mapv toString operands) (mapv toString dops))
  (second
    (reduce
     (fn [[a da][b db]] [(Multiply a b) (Add (Multiply a db) (Multiply b da))])
     (mapv vector operands dops))))

(def Multiply (OperationCtor * tricky-mul "*"))

(def Divide
  (OperationCtor my-divide
    (fn [[op & rest-op] [dop & rest-dop]]
      (if (empty? rest-op)
        (Negate (Divide dop (Multiply op op)))
        (let [mul (apply Multiply rest-op)]
          (Divide
            (Subtract
              (Multiply dop mul)
              (Multiply op (tricky-mul rest-op rest-dop)))
            (Multiply mul mul))))) "/"))


(def ArithMean (OperationCtor count-mean (fn [_ dops] (apply ArithMean dops)) "arith-mean"))
(def GeomMean (OperationCtor
  #(Math/pow (Math/abs (double(apply * %&))) (/ 1 (count %&)))
  (fn [operands dops]
    (Divide
      (Multiply
        (apply GeomMean operands)
        (tricky-mul operands dops))
      (Multiply
        (Constant (count operands))
        (apply Multiply operands))))
  "geom-mean"))
(def HarmMean (OperationCtor
  (fn [& args] (/ (count args) (double (apply + (mapv #(/ 1 (double %)) args)))))
  (fn [operands dops]
    (let [gmean (apply HarmMean operands)]
      (Multiply
        (Multiply gmean gmean)
        (apply ArithMean (mapv #(Divide %1 (Multiply %2 %2)) dops operands))
        )))
  "harm-mean"))

(defn convert-to-bool [x] (> x 0))
(def bool-to-num {false 0 true 1})

(defn create-bool [operation symbol]
  (OperationCtor #(bool-to-num (operation (convert-to-bool %1) (convert-to-bool %2))) nil symbol))

(def And (create-bool #(and %1 %2) "&&"))
(def Or (create-bool #(or %1 %2) "||"))
(def Xor (create-bool #(not= %1 %2) "^^"))
(def Impl (create-bool #(or (not %1) %2) "->"))
(def Iff (create-bool #(= %1 %2) "<->"))


(def op-to-obj
  {
   '+ Add
   '* Multiply
   '- Subtract
   '/ Divide
   'negate Negate
   'arith-mean ArithMean
   'geom-mean GeomMean
   'harm-mean HarmMean})

(def variable-to-obj {'x (Variable "x") 'y (Variable "y") 'z (Variable "z")})
(defn parse-object [source]
  (cond
    (list? source) (apply (op-to-obj (first source)) (mapv parse-object (rest source)))
    (number? source) (Constant source)
    (contains? variable-to-obj source) (variable-to-obj source)))

(defn parseObject [string] (parse-object (read-string string)))


(load-file "parser.clj")
(def *all-chars (mapv char (range 0 128)))
(def *space (+char (apply str (filter #(Character/isWhitespace %) *all-chars))))
(def *ws (+ignore (+star *space)))
(def *digit (+char (apply str (filter #(or (= % \.) (Character/isDigit %)) *all-chars))))
(def *number (+map read-string (+str (+or (+seqf cons *ws (+char "-") (+plus *digit)) (+plus *digit)))))
(def *letter (+char (apply str (filter #(Character/isLetter %) *all-chars))))
(def *string (fn [s] (apply +seq (mapv #(+char (str %1)) s))))
(def *v (+str (+plus *letter)))

(declare *parse-expression)
(declare *parse-token)
(def *Constant (+map Constant *number))
(def *Variable (+map Variable *v))
(def *Negate (+map Negate (+seqn 0 (+ignore (*string "negate")) *ws (delay (*parse-token)) *ws)))
(def *parse-token #(+or *Constant *Negate *Variable
                       (+seqn 1 (+char "(") *ws (delay (*parse-expression 0)) *ws (+char ")") *ws)))

(defn recursive-fold [f r]
  (if (empty? r)
    f
    ((first (first r)) f (recursive-fold (second (first r)) (rest r)))))

(defn *parse-expression [level]
  (let [
    *left-assoc (partial reduce #((first %2) %1 (second %2)))
    *right-assoc #(recursive-fold (first %) (rest %))
    *parse-operation #(apply +or (mapv (fn [[key value]] (+seqf (constantly value) (*string key))) %))

    op-assoc [[{"<->" Iff}, *left-assoc],
              [{"->" Impl}, *right-assoc],
              [{"^^" Xor}, *left-assoc],
              [{"||" Or}, *left-assoc],
              [{"&&" And}, *left-assoc],
              [{"+" Add "-" Subtract}, *left-assoc],
              [{"*" Multiply "/" Divide}, *left-assoc]]

    *next-level
      #(if (= (+ % 1) (count op-assoc))
        (delay (*parse-token))
        (delay (*parse-expression (+ % 1))))]
    (+seqf
      (fn [operation rest-exp] ((second (nth op-assoc level)) (cons operation rest-exp)))
      (*next-level level)
      (+star (+seq *ws (*parse-operation (first (nth op-assoc level))) *ws (*next-level level))))))

(def parseObjectInfix (+parser (+seqn 0 *ws (*parse-expression 0) *ws)))