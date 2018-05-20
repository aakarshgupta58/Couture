# Couture
This is the API 

Take an array input of length n.
and a number input: g

Transform input array to remove min no. of elements, such that MAX_Val[Arr] - MIN_Val[Arr] < g

return array

Example: arr= {34, 656, 565, 412, 67, 23, 900, 6} and g= 200

Incorrect solution: Drop of 34, 23, 67, 23, 412, 900 and 6

Correct Solution: Drop of 412, 656, 565, 900
thus, newArr = [34, 23, 67, 6].