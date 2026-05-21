# Graph Algorithms — Java Implementation

## Bonus Task: Dijkstra's Algorithm (Shortest Path)

###  Overview

This bonus task extends the existing graph implementation by adding **edge weights** and implementing **Dijkstra's
Algorithm** to find the shortest path from a starting vertex to all other vertices in the graph.

---

###  File

| File | Description |
|------|-------------|
| `Dijkstra.java` | Contains the `Edge`, `Graph`, and `Dijkstra` classes with full implementation |

---

### Class Structure

#### `Edge`
Represents a weighted directed edge in the graph.

```java
class Edge {
    int destination;
    int weight;
}
```

#### `Graph`
Stores the graph as an **adjacency list** of `Edge` objects.

- `addEdge(int src, int dest, int weight)` — adds an undirected weighted edge
- `dijkstra(int start)` — runs Dijkstra's algorithm from the given start vertex

---

### How Dijkstra's Algorithm Works

1. Set the distance to the **start vertex = 0**, all others = **∞**
2. Repeat for every vertex:
   - Pick the **unvisited vertex** with the **smallest distance**
   - Mark it as visited
   - **Relax** all its neighbors — update their distance if a shorter path is found
3. Print the final distance from the start vertex to every other vertex

>  Implemented using simple **arrays** and **loops** — no priority queue required.

---

###  Example Graph

```
Vertices: 6
Edges (undirected, weighted):

  0 ── 1  (weight 1)
  0 ── 2  (weight 4)
  0 ── 3  (weight 2)
  1 ── 3  (weight 1)
  1 ── 4  (weight 2)
  2 ── 5  (weight 1)
  3 ── 4  (weight 3)
  3 ── 5  (weight 5)
  4 ── 5  (weight 1)
```

---

### 🖥 Sample Output

```
=============================================
  Dijkstra's Algorithm  |  Start vertex: 0
=============================================
Vertex     Distance from 0
----------------------------
0          0
1          1
2          4
3          2
4          3
5          4
=============================================
```

---

###  How to Run

```bash
javac Dijkstra.java
java Dijkstra
```

> Requires **Java 8** or higher.

---

###  Complexity

| Type | Value |
|------|-------|
| Time Complexity | O(V²) |
| Space Complexity | O(V + E) |

- **V** — number of vertices
- **E** — number of edges
