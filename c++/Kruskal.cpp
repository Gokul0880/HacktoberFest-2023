#include <bits/stdc++.h>
using namespace std;

class Edge {
public:
    int source;
    int destination;
    int weight;
};

// Comparator for sorting edges based on their weight
bool compare(Edge e1, Edge e2) {
    return e1.weight < e2.weight;
}

// Find function to get the parent of a node with path compression
int find(int v, int *parents) {
    if (parents[v] == v)
        return v;
    // Path compression
    parents[v] = find(parents[v], parents);
    return parents[v];
}

// Union function to join two sets and perform the union operation
void Union(Edge *output, Edge *input, int v, int *parents) {
    int count = 0, i = 0;
    while (count < v - 1) {
        int parentS = find(input[i].source, parents);
        int parentD = find(input[i].destination, parents);
        
        // If the two vertices belong to different sets, add the edge to MST
        if (parentS != parentD) {
            output[count] = input[i];
            count++;
            parents[parentS] = parentD;  // Union operation: attach the parent of one to the other
        }
        i++;
    }
}

int main() {
    // Input number of vertices and edges
    int v, e;
    cin >> v >> e;

    Edge *input = new Edge[e];
    for (int i = 0; i < e; i++) {
        cin >> input[i].source >> input[i].destination >> input[i].weight;
    }

    // Sort the edges based on weight
    sort(input, input + e, compare);

    // Initialize the parent array where each vertex is its own parent initially
    int *parents = new int[v];
    for (int i = 0; i < v; i++) {
        parents[i] = i;
    }

    // Output array to store the edges of the MST
    Edge *output = new Edge[v - 1];

    // Apply Kruskal's algorithm to get the MST
    Union(output, input, v, parents);

    // Output the edges of the MST
    for (int i = 0; i < v - 1; i++) {
        cout << min(output[i].source, output[i].destination) << " "
             << max(output[i].source, output[i].destination) << " "
             << output[i].weight << endl;
    }

    // Cleanup memory
    delete[] input;
    delete[] output;
    delete[] parents;

    return 0;
}
