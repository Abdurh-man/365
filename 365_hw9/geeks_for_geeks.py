
# Python code to demonstrate
# finding whether element
# exists in listof list
  
# initialising nested lists
ini_list = [[1, 2, 5, 10, 7],
            [4, 3, 4, 3, 21],
            [45, 65, 8, 8, 9, 9]]
  
elem = 8
elem1 = 0
  
# element exists in listof listor not?
res1 = elem in (item for sublist in ini_list for item in sublist)
res2 = elem1 in (item for sublist in ini_list for item in sublist)
  
# # printing result
print(str(res1), "\n", str(res2))
if res2:
    print(True)
else:
    print(False)