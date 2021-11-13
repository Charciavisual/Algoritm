class Solution {
    
    class Edge {
        int vertex;
        double prob;

        public Edge(int vertex, double prob) {
            this.vertex = vertex;
            this.prob = prob;
        }
    }
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Edge>[] vertices = new List[n];
        
        for(int i=0; i<edges.length; i++) {
            int vertex1 = edges[i][0];
            int vertex2 = edges[i][1];
            double prob = succProb[i];
            
            List<Edge> edgesOfVertex1 = vertices[vertex1];
            List<Edge> edgesOfVertex2 = vertices[vertex2];
    
            if(edgesOfVertex1 == null) {
                edgesOfVertex1 = new ArrayList<>();
            }
            edgesOfVertex1.add(new Edge(vertex2, prob));
            
            if(edgesOfVertex2 == null) {
                edgesOfVertex2 = new ArrayList<>();
            }
            edgesOfVertex2.add(new Edge(vertex1, prob));
            
            vertices[vertex1] = edgesOfVertex1;
            vertices[vertex2] = edgesOfVertex2;
        }
        
        return findMaximumProbability(vertices, n, start, end);
    }
    
    private double findMaximumProbability(List<Edge>[] vertices, int n, int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((e1,e2)-> Double.compare(e2.prob, e1.prob));
        double[] maximumSuccProbPerVertex = new double[n];
        
        pq.add(new Edge(start, 1.0));
        maximumSuccProbPerVertex[start] = 1.0;
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            List<Edge> edgesOfCurVertex = vertices[cur.vertex];
            
            if(edgesOfCurVertex == null) {
                continue;
            }
            
            for(Edge edge : edgesOfCurVertex) {
                if(cur.prob * edge.prob > maximumSuccProbPerVertex[edge.vertex]) {
                    maximumSuccProbPerVertex[edge.vertex] = cur.prob * edge.prob;
                    pq.add(new Edge(edge.vertex, cur.prob * edge.prob));
                }
            }
        }
    
        return maximumSuccProbPerVertex[end];
    }
}
