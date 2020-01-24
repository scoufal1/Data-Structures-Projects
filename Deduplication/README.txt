Name: Sarah Coufal
How to compile:
How to run: java Main <csv file>
Known bugs and limitations:
Discussion:

I chose to determine uniqueness based on sex, race, dob, age, ht_feet, ht_inch,
weight, haircolr, and eyecolor. It was difficult to find a characteristic that
would differentiate individuals due to the fact that many people's dates of
birth weren't known, and were entered in as 12311900. Some traits, such as
weight and age, may change during the year, but were more useful for
deduplicating than other fields, so I included them in the function to determine
equality.

how much time each method takes on the full file:
allPairsDeduplication: too long to run on the full file, due to the fact that it
is O(n^2) (since it has nested for loops).
hashLinearDeduplication: 1552 ms
hashDoubleDeduplication: 1642 ms
Both hash table deduplication methods are O(n), due to copying to and from the
arraylist.
quicksortDeduplication: 104854 ms
builtinSortDeduplication: 4055 ms
Both sorting methods are expected O(nlogn), but built in sort is faster than
quicksort. Collections.sort uses merge sort, which guareentees n(logn)
performance, so built in sort has a consistently better time. (Note: in the graph
builtinSort looks somewhat linear, but is actually O(nlogn) which can be seen
with more data points, but running allPairs longer than this took too long)

The hash tables were by far the most efficient methods, as seen in the graph.
Both are O(n), due to the copying to and from the arraylist, since the actual
use of the hash tables is close to O(1). While the load factor is low, both hash
tables have an average amount of probes close to 1 and their time difference is
not significant. However, double hash has a lower maximum number of probes
compared to linear hash, due to the second hash function that changes the jump
size, therefore reduces the amount of clustering. Therefore, double hash is the
most efficient.

