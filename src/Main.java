/*
Kevin Baltazar Reyes
916353599
kreyes6@mail.sfsu.edu
 */

import java.util.Scanner;

class DijkstrasAlgorithm {

    private static void dijkstra(int[][] Matrix, int beginVert, int destination) {
        int numVert = Matrix[0].length;

        int[] minDistArray = new int[numVert];   //this will hold all the shortest distances

        boolean[] newVert = new boolean[numVert];   //added to this array if it is the shortest path

        for (int vertPtr = 0; vertPtr < numVert; vertPtr++) {   //initializes the grid with 'infinite' and false
            minDistArray[vertPtr] = Integer.MAX_VALUE;
            newVert[vertPtr] = false;
        }

        minDistArray[beginVert] = 0;   //source is always 0 to itself

        int[] parents = new int[numVert]; //array stores shortest path

        parents[beginVert] = -1;     //-1 means it has no parent

        for (int i = 1; i < numVert; ++i) {   //iterates through and finds shortest path for all vertices

            int closestVert = -1;
            int minDist = Integer.MAX_VALUE;
            for (int vertPtr = 0; vertPtr < numVert; ++vertPtr) {   //calculates the minimum distances
                if (!newVert[vertPtr] && minDistArray[vertPtr] < minDist) {
                    closestVert = vertPtr;
                    minDist = minDistArray[vertPtr];
                }
            }

            newVert[closestVert] = true; //its true when it is processed

            for (int vertPtr = 0; vertPtr < numVert; ++vertPtr) {   //update all the values for vertix next to them
                int tempDist = Matrix[closestVert][vertPtr];

                if (tempDist > 0 && ((minDist + tempDist) < minDistArray[vertPtr])) {
                    parents[vertPtr] = closestVert;
                    minDistArray[vertPtr] = minDist + tempDist;
                }
            }
        }

        System.out.print("Vertex\tCost\tPath");
        System.out.print("\n" + (char) (beginVert + 97) + " -> " + (char) (destination + 97) + " \t " + minDistArray[destination] + "\t\t"); //print out the shortest path and cost

        printPath(parents[destination], parents);

        System.out.print((char) (destination + 97));
    }

    private static void printPath(int currentVertex, int[] parents) {   //print all the shortest paths
        if (currentVertex == -1) {
            return;
        }
        printPath(parents[currentVertex], parents); //recursive call
        System.out.print((char) (currentVertex + 97) + " > ");
    }

    public static void main(String[] args) {
        int[][] Matrix = {
                {0, 2, 0, 0, 0, 0, 6, 0},
                {2, 0, 7, 0, 2, 0, 0, 0},
                {0, 7, 0, 3, 0, 3, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 2},
                {0, 2, 0, 9, 0, 2, 1, 0},
                {0, 0, 3, 0, 2, 0, 0, 2},
                {6, 0, 0, 0, 1, 0, 0, 4},
                {0, 0, 0, 2, 0, 2, 4, 0}};
        System.out.print("Source Node(use lower case): ");
        Scanner in1 = new Scanner(System.in);
        char sourceChar = in1.next().charAt(0);
        int sourceInt = java.lang.Character.getNumericValue(sourceChar) - 10;
        System.out.print("Destination Node(use lower case): ");
        Scanner in2 = new Scanner(System.in);
        char endChar = in1.next().charAt(0);
        int endInt = java.lang.Character.getNumericValue(endChar) - 10;
        dijkstra(Matrix, sourceInt, endInt);
    }
}