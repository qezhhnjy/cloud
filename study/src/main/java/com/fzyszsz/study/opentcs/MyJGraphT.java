package com.fzyszsz.study.opentcs;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author fuzy
 * create time 19-3-14-下午2:58
 */
public class MyJGraphT {

    public static void main(String[] args) {
/*
        DefaultDirectedGraph<Object, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        graph.addVertex("www");
        Object o = graph.vertexSet().stream().filter(uri -> "www".equals(uri.toString())).findAny().get();
        System.out.println(o);
*/

/*
        int size = 9;

        // Create the graph object
        AtomicInteger id = new AtomicInteger(1);
        Graph<String, DefaultWeightedEdge> completeGraph =
                new SimpleGraph<>(() -> "v" + id.getAndIncrement(), SupplierUtil.createDefaultWeightedEdgeSupplier(), true);

        CompleteGraphGenerator<String, DefaultWeightedEdge> completeGraphGenerator = new CompleteGraphGenerator<>(size);
        completeGraphGenerator.generateGraph(completeGraph);

        DepthFirstIterator<String, DefaultWeightedEdge> iterator = new DepthFirstIterator<>(completeGraph);
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(completeGraph.edgesOf(next));
        }
*/
        strongConnectedComponents();
    }

    public static void strongConnectedComponents() {
        Graph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addVertex("h");
        directedGraph.addVertex("i");

        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "c");
        directedGraph.addEdge("c", "a");
        directedGraph.addEdge("e", "d");
        directedGraph.addEdge("e", "f");
        directedGraph.addEdge("f", "g");
        directedGraph.addEdge("g", "e");
        directedGraph.addEdge("h", "e");
        directedGraph.addEdge("i", "h");

        KosarajuStrongConnectivityInspector<String, DefaultEdge> inspector = new KosarajuStrongConnectivityInspector<>(directedGraph);
        List<Graph<String, DefaultEdge>> list = inspector.getStronglyConnectedComponents();

        System.out.println("Strongly connected components:");
        for (Graph<String, DefaultEdge> graph : list) {
            System.out.println(graph);
        }
        System.out.println();

        System.out.println("Shortest path from i to c:");

        DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<>(directedGraph);
        ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> i = dijkstra.getPaths("i");
        System.out.println(i.getPath("c") + "\n");

        System.out.println("Shortest path from c to i:");
        ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> c = dijkstra.getPaths("c");
        System.out.println(c.getPath("i"));

    }
}
