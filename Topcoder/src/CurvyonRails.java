/********
// BEGIN CUT HERE
// PROBLEM STATEMENT
// Cat Carol is the queen of Gridland.
Gridland is a rectangular country divided into a grid of unit square cells.
Each cell contains either a town or a wasteland.
Carol has decided to construct some railroad tracks according to the following rules:


The cells with wasteland must not contain any railroad tracks.
Each town cell has to contain a single segment of railroad tracks that connects two of its four sides.
Each segment of tracks has to be connected to two other segments of tracks (one on each end).


Note that Carol does not require the entire set of tracks to be connected.
Configurations that consist of multiple disjoint loops are acceptable, too.

A Curvy is a curious animal indigenous to Gridland.
These animals love curves and hate straight things.
Some of the towns in Gridland are inhabited by Curvies.
If such a town happens to contain a straight segment of tracks (i.e., a segment that connects two opposite sides of the cell), the Curvies in the town are very unhappy.

You are given a String[] field that describes Gridland:
each character of each element of field describes one of its cells.
The meaning of individual characters follows.


The character '.' represents a town without Curvies.
The character 'C' represents a town with Curvies.
The character 'w' represents a wasteland.


Compute and return the minimal number of towns with unhappy Curvies when the railroad tracks are constructed according to the above constraints.
If there is no way to construct the railroads according to the given rules, return -1 instead.

DEFINITION
Class:CurvyonRails
Method:getmin
Parameters:String[]
Returns:int
Method signature:int getmin(String[] field)


CONSTRAINTS
-field will contain between 1 and 25 elements, inclusive.
-Each element of field will contain between 1 and 25 characters, inclusive.
-Each element of field will contain the same number of characters.
-Each character of each element of field will be '.', 'w' or 'C'.


EXAMPLES

0)
{".."
,".."}

Returns: 0

It is possible to construct a round railroad that passes through all towns.

1)
{"wCCww"
,"wCC.."
,"..w.."
,"....w"
,"ww..w"}

Returns: 0

 ? 

There are two valid ways to construct the railroads. In the left picture, there is one town with unhappy Curvies. In the right picture, there are no towns with unhappy Curvies.

2)
{"C.w"
,"..."
,".C."}

Returns: 1

The curvy in the middle of the bottom row will be unhappy.

3)
{"."}

Returns: -1

There is no way to construct the railroads.

4)
{"w"}

Returns: 0

There is no town.

5)
{"CC..CCCw.CwC..CC.w.C",
 "C.CCCwCCC.w.w..C.w..",
 "wwww...CC.wC.Cw.CC..",
 "CC..CC.w..w.C..CCCC.",
 "CC.CCC..CwwCCC.wCC..",
 "w.C..wwCC.CC.wwwCC..",
 ".CC.CC..CCC..CC.CC.C",
 "Cw....C.C.CCC...CC..",
 "CC.C..Cww.C.CwwwC..w",
 "wCCww..C...CCCCCCC.w",
 "C.CCw.CC.ww...C.CCww",
 "C.C.C.CCwCC..wCCw.Cw",
 "CCC.C...w..C.wC.wCCw",
 "CC.C..C..CCC.CC.C...",
 "C.ww...CCC..CC...CCC",
 "...CCC.CwwwC..www.C.",
 "wwCCCCC.w.C.C...wCwC",
 "CCwC.CwCCC.C.w.Cw...",
 "C.w.wC.CC.CCC.C.w.Cw",
 "CCw.CCC..C..CC.CwCCw",
 "C.wwwww.CwwCCwwwwwww"}

Returns: 9



// END CUT HERE
********/
import java.util.*;
public class CurvyonRails {
	public int getmin(String[] field) {
		             return 1;
	}
	public static void main(String[] args) {
		CurvyonRails temp = new CurvyonRails();
		//System.out.println(temp.getmin(String[] field));
	}
}
