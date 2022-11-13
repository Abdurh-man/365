#grade script by Neh Patel

import os
import sys

# diction of test cases and their answers
test_cases = {
    "(swap 9 6)": "(6 . 9)",
    "(swap 5 3)": "(3 . 5)",
    "(swap 0 1)": "(0 . 1)",
    "(swap 11 36)": "(11 . 36)",
    "(swap 123 456)": "(123 . 456)",
    "(swap 222 111)": "(111 . 222)",
    "(swap 4 1113)": "(4 . 1113)",
    "(swap 10 1)": "(1 . 10)",
    "(swap 11 0)": "(0 . 11)",
    "(swap 2 3)": "(2 . 3)",
    "(avg \'(3 6 17 12 15))": "10.6",
    "(avg \'(1.5 2.65))": "2.075",
    "(avg \'(2))": "2.0",
    "(avg \'(111))": "111.0",
    "(avg \'(222 111 10 22 33))": "79.6",
    "(avg \'(12 13 45))": "23.333333333333332",
    "(avg \'(7 3 11))": "7.0",
    "(avg \'(-11 10 -12))": "-4.333333333333333",
    "(avg \'(2 4 -6 8 10))": "3.6",
    "(avg \'(10))": "10.0",
    "(max \'(1 7 3 9 2 3 1))": "9",
    "(max \'(5))": "5",
    "(max \'(1 2 3))": "3",
    "(max \'(1.5 1.34 1.2))": "1.5",
    "(max \'(1 1 1 1))": "1",
    "(max \'(1034 187 392 102))": "1034",
    "(max \'(420 12))": "420",
    "(max \'(-11 -1 -2 -3 -15))": "-1",
    "(max \'(-12 -6 10 2 0))": "10",
    "(max \'(2 5 -8))": "5",
    "(increasing 4 12 82)": "#t",
    "(increasing 12 4 82 107)": "#f",
    "(increasing 1 2 2)": "#f",
    "(increasing 1.23 1.54 1.89)": "#t",
    "(increasing 2 3 4.5 4.3)": "#f",
    "(increasing -1 -2 -44 -2 -4)": "#f",
    "(increasing -22 -20 0)": "#t",
    "(increasing 0)": "#t",
    "(increasing 3 5 6 8 1 21)": "#f",
    "(increasing 2 4 5 6 10)": "#t",
    "(apowb 5 2)": "25",
    "(apowb 1.5 2)": "2.25",
    "(apowb 7.12 2)": "50.6944",
    "(apowb -12 3)": "-1728",
    "(apowb 0 3)": "0",
    "(apowb 5 0)": "1",
    "(apowb -2.10 2)": "4.41",
    "(apowb 2222 0)": "1",
    "(apowb 112 1)": "112",
    "(apowb 324 5)": "3570467226624",
    "(filter (lambda(x) (< x 5)) '(3 9 5 8 2 4 7))": "(3 2 4)",
    "(filter (lambda(x) (eq? x \'())) '( \'() (2 3) (1156)))":"()",
    "(filter (lambda(x) (> x 3.4)) '(3.4 3.55 2.4 3.66))":"(3.55 3.66)",
    "(filter (lambda(x) (< x -12)) '(-1 -3 -4 -56 -2))":"(-56)",
    "(filter (lambda(x) (< x 5)) '(1.2 12 3))": "(1.2 3)",
    "(filter (lambda(x) (eq? x)) '(3 9 5 8 2 4 7))": "(3 9 5 8 2 4 7)",
    "(filter (lambda(x) (> x 5)) '(0 1 2 3))": "()",
    "(filter (lambda(x) (eq? 1 x)) '(1 9 5 8 2 4 7))": "(1)",
    "(filter (lambda(x) (< x .2)) '(1 2 3 3.4 .1 .22 .34))": "(0.1)",
    "(filter (lambda(x) (< x 10)) '())": "()",
}

if len(sys.argv) > 1:
    try:
        num = int(sys.argv[1]) -1
        val = list(test_cases.values())[num]
        key = list(test_cases)[num]
        command  = "guile -l hw10functions.scm -c \"(display %s)\"" %str(key)
        output_stream = os.popen(command)
        yours = output_stream.read()
        if yours == val:
            print("case %d: correct!" % num )
        else:
            print("case %d: incorrect!" %int(sys.argv[1]))
            print("tested: %s" % command)
            print("your output: ", yours)
            print("script output: ", val)
    except:
        print("incorrect case number")
        print("Useage: python3 grade.py case# ")
        print("tests all by default: python3 grade.py")

else:
    total = 0
    for case_num, (case, answer) in enumerate(test_cases.items()):
        command  = "guile -l hw10functions.scm -c \"(display %s)\"" %case
        try:
            print('case %2d/%d: ' %((case_num + 1), len(test_cases) ) , end='')
            output_stream = os.popen(command)
            if (output_stream.read() == answer):
                print("correct!")
                total+= 1
            else:
                print("incorrect :(")
        except:
            print("something went wrong on case %d" %(case_num + 1))
            print("tested: ", command)
    print("\nYou got %d out of %d correct!" %(total, len(test_cases)))

