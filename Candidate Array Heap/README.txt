Name: Sarah Coufal
How to compile:
How to run: java Main <optional flags -n followed by number of top candidates, -rfollowed by last names of candidates to be removed from top N candidates> <csv files>
Known bugs and limitations:
Discussion:

For my peekTopN method, I create a copy of the arraylist, then use Collections.sort to sort the array, then remove the extra elements so that it only contains the top k elements. The runtime complexity of this method is O(nlogn), which comes from sorting the array. However, in the special cases where k is 1 or 2, it is O(1). In the case that it is 1, the root element (peek()) is simply added to the arraylist and returned. For k=2, the root is added to the arraylist, and the root's children are compared so that the larger one (smallerChild since it is written as a min heap) is added to the arraylist and returned.

