package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.utils.BinaryHeap;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	
    	// get data
    	
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        final int nbNodes = graph.size() ;    
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        
        // Initialisation
        Label[] labels = new Label[nbNodes] ;
        Node origin = data.getOrigin() ;
        for (Node node: graph.getNodes()) {
        		labels[node.getId()]=new Label(node.getId(),false,Double.POSITIVE_INFINITY,null) ;
        		}
        labels[origin.getId()]=new Label(origin.getId(),false,0.0,null) ;
        heap.insert(labels[origin.getId()]) ;

        // itérations 
        int x ;
        while (!heap.isEmpty() && !labels[data.getDestination().getId()].getMark()) {
        	
        	x = heap.deleteMin().getCurrentNode() ;
        	labels[x].setMark(true) ;
        	notifyNodeMarked(graph.get(x));
        	for (Arc arc : graph.get(x).getSuccessors()) {
        		int currentid=arc.getDestination().getId() ;
        		if (!data.isAllowed(arc) || labels[currentid].getMark()) {
                    continue;
                }
        		if (labels[currentid].getCost()==Double.POSITIVE_INFINITY) {
        			labels[currentid].setCost(labels[x].getCost()+data.getCost(arc)) ;
        			labels[currentid].setFather(arc) ;
        			heap.insert(labels[currentid]) ;
        			notifyNodeReached(arc.getDestination());
        		} else {
        			if (labels[currentid].getCost() > labels[x].getCost()+data.getCost(arc)) {
        				try {
        					heap.remove(labels[currentid]) ;
        				}
        				catch (Exception ElementNotFoundException) {
        					;
        				}
        				labels[currentid].setCost(labels[x].getCost()+data.getCost(arc)) ;
        				labels[currentid].setFather(arc) ;
        				heap.insert(labels[currentid]) ;
        			}
        			
        		}
        	}
        }
        
        if (labels[data.getDestination().getId()].getFather()==null) {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        	} else {
        		notifyDestinationReached(data.getDestination());
					        // Créer le chemin
	        ArrayList<Arc> arcs = new ArrayList<>();
	        Arc arc = labels[data.getDestination().getId()].getFather() ; // en partant de la fin
	        while (arc != null) {
	            arcs.add(arc);
	            arc = labels[arc.getOrigin().getId()].getFather();
	        }
	        // Reverse the path...
	        Collections.reverse(arcs);
	        
	        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }
        
        return solution;      
        
    }

}
