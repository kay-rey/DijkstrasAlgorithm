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
        System.out.print("\n" + beginVert + " -> " + destination + " \t " + minDistArray[destination] + "\t\t"); //print out the shortest path and cost

        printPath(parents[destination], parents);

        System.out.print(destination);
    }

    private static void printPath(int currentVertex, int[] parents) {   //print all the shortest paths
        if (currentVertex == -1) {
            return;
        }
        printPath(parents[currentVertex], parents); //recursive call
        System.out.print(currentVertex + " > ");
    }

    public static void main(String[] args) {
        int[][] Matrix = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 0, 10, 0, 2, 0, 0},
                {0, 0, 0, 14, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        System.out.print("Source Node: ");
        Scanner in1 = new Scanner(System.in);
        int source = in1.nextInt();
        System.out.print("Destination Node: ");
        Scanner in2 = new Scanner(System.in);
        int end = in2.nextInt();
        dijkstra(Matrix, source, end);
    }
}