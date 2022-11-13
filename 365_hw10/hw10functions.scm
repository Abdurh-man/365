#!/usr/local/bin/guile -s
!#

;swap function
(define (swap x y)
  (if(> x y) 
    (cons y x)
    (cons x y)
    )
  )

;avg as floating
(define (avg x)
  (sum x 0 0.0)
  )
(define (sum x sumSoFar sizeSoFar)
  (if (null? x)
      (/ sumSoFar sizeSoFar)
      (sum (cdr x) (+ (car x) sumSoFar) (+ 1 sizeSoFar))
      )
  )

;max number from a list
(define (max x) 
  (max-helper x (car x))
  )
(define (max-helper x biggest)
  (if (null? x)
      biggest
      (if(< (car x) biggest)
        (max-helper (cdr x) biggest)
        (max-helper (cdr x) (car x))
        )
      )
  )

(define (increasing . x)
  (if(= (inc-helper (cdr x) (car x) 0) 0)
    #t
    #f
    )
  )
(define (inc-helper x check flag)
  (if (null? x)
      flag
      (if(> (car x) check)
        (inc-helper (cdr x) (car x) flag)
        (inc-helper (cdr x) (car x) (+ 1 flag))
        )
      )
  )

;power function
(define (apowb a b)
  (if(= b 0)
    "1"
    (apowb-helper a b 1 a)
  )
  )
(define (apowb-helper a b c power)
  (if(= b c)
    power
    (apowb-helper a b (+ 1 c) (* a power))
    )
  )


(define (filter fun List)
  (filter-helper fun List)
  )
(define (filter-helper fun List)
  (if(null? List)
    '()
    (if(fun (car List)) 
      ; we traverse regardless if the lambda function is true or now
      (cons (car List) (filter-helper fun (cdr List)))
      (filter-helper fun (cdr List))
      )
    )
  )
