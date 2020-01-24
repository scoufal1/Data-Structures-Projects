Name: Sarah Coufal
How to compile:
How to run:
Known bugs and limitations:
Discussion:

There is a significant speedup in this assignment compared to in assignment 2. In assignment 2, we used linear search to weave the files together and search for a given zipcode. There are over 40,000 zipcodes, and for each zipcode, we had to traverse the list and find the Place object with a matching zipcode. Because the input size is so large and there are nested loops, the runtime is very long. In assignment 5, instead of searching through every Place object linearly, we use binary search. Binary search is log(n) complexity so it is much faster to weave the files together, so it takes much less time for the program to run.

A2
real 0m43.945s
user 0m43.823s
sys 0m2.046s

A5
real 0m0.688s
user 0m1.589s
sys 0m0.207s

