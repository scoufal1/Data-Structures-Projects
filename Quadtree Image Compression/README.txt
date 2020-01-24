Name: Sarah Coufal and Shaili Regmi

How to compile:

How to run it: java Main -i <filename> -o <output name> -c/-e -t

Known Bugs and Limitations:

The thresholds for the compression levels were determined experimentally on the photo
kira.ppm, where the first six levels are approximately the same as the levels
given in the assignment. The last two compression levels are as high as possible
for kira.ppm. This means that the compression ratios will be different for other
images, depending on the size and color variance of the image.

For edge detection, we chose to set the threshold for compression to 1000, which
was chosen experimentally based on how different images looked.

For images that do not have resolution greater than 100 leaves, the images were
not generated and instead an error message is printed, showing the actual
compression levels based on calculations instead of the expected levels.

Discussion: No extra credit implemented
