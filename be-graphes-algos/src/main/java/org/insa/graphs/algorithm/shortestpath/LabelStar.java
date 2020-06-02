package org.insa.graphs.algorithm.shortestpath;


import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label implements Comparable<Label>{
	
    public LabelStar(int currentnode,boolean mark,double cost,Arc father,Graph G, Node dest) {
    	super(currentnode,mark,cost,father) ;	
    	double totalcost = cost + Point.distance(G.get(this.getCurrentNode()).getPoint(),dest.getPoint()) ;
    	this.setCost(totalcost);
    }

}