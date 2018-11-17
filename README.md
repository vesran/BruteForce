# BruteForce
Brute force and combinaisons.

The idea is to test/generate combinaisons of any objects. The model behind the algorithm has been inspired by gear models.
It used two main classes: CycleList and Cogwheel.
A CycleList is a special type of linked list where its last element is linked to the first element.
A Cogwheel is a linked list of CycleList. It can make its CycleList rotates, meaning that the CycleList will be positionning at its next element. When the n-th Cogwheel has made an entire rotation, the (n-1)-th Cogwheel rotates.

The BruteForce class contains two methods. One ctreates a file with a list of combinaisons and the other finds a specific combinaison using a predicate (see class Main for an exemple).
