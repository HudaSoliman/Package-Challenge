# Package-Challenge
Solving package challenge with java 11


Input	sample
Your APIshould accept as its first argument a path to a filename. The input file contains several lines.
Each line is one test case.
Each line contains the weight that the package can take (before the colon) and the list of itemsyou need
to choose. Each itemis enclosed in parentheses where the 1
st
number is a item’s index number, the 2
nd
is its weight and the 3rd is its cost. E.g.
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9)
(6,46.34,€48)
8 : (1,15.3,€34)
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52)
(6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36)
(6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
Output	sample
For each set of itemsthat you put into a package provide a new row in the output string (items’ index
numbers are separated by comma). E.g.
4
-
2,7
8,9

Constraints
You should write a class com.mobiquity.packer.Packer with a static API method named pack. This
method accepts the absolute path to a test file as a String. The test file will be in UTF-8 format. The pack
method returns the solution as a String.
Your method should throw an com.mobiquity.exception.APIException if incorrect parameters are being
passed. Therefore your signature should look like
public static String pack(String filePath) throws APIException


Solution 
Problem solved using JAVA 11 and tested with junit5
Solved with Knapsack Algorithm with back tracking

