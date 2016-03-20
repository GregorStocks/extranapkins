(ns extranapkins.core
  (:require [clojure.string :as string])
  (:gen-class))

(def number->words)
(defn small->word [n]
  (case n
    1 "one"
    2 "two"
    3 "three"
    4 "four"
    5 "five"
    6 "six"
    7 "seven"
    8 "eight"
    9 "nine"
    10 "ten"
    11 "eleven"
    12 "twelve"
    13 "thirteen"
    14 "fourteen"
    15 "fifteen"
    16 "sixteen"
    17 "seventeen"
    18 "eighteen"
    19 "nineteen"))

(defn twenty-to-ninety-nine->word [n]
  (let [remainder (mod n 10)
        quotient-string (case (quot n 10)
                          2 "twenty"
                          3 "thirty"
                          4 "forty"
                          5 "fifty"
                          6 "sixty"
                          7 "seventy"
                          8 "eighty"
                          9 "ninety")]
    (if (= 0 remainder)
      quotient-string
      (str quotient-string "-" (small->word remainder)))))

(defn under-a-thousand->word [n]
  (let [hundreds (quot n 100)
        rest (mod n 100)]
    (if (= rest 0)
      (str (small->word hundreds) " hundred")
      (str (small->word hundreds) " hundred and " (number->words rest)))))

(defn number->words [n]
  (condp > n
    20 (small->word n)
    100 (twenty-to-ninety-nine->word n)
    1000 (under-a-thousand->word n)
    (str "uhh" (number->words (mod n 1000)))))

(defn extranapkin [s]
  (let [n (number->words (count (string/split s #" ")))]
    (str "I've got " n " words for you: " s)))

(defn extranapkins [x]
  (loop [so-far "Fuck off"
         i 1]
    (if (> i x)
      so-far
      (recur (extranapkin so-far) (inc i)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (extranapkins 109)))
