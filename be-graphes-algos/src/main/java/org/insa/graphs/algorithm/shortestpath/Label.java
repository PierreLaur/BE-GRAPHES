package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class Label implements Comparable<Label>{
	
	private int currentnode ;
	private boolean mark ;
	private double cost ;
	private Arc father ;
	
    public Label(int currentnode,boolean mark,double cost,Arc father) {
    	this.currentnode=currentnode ;
    	this.mark=mark ;
    	this.cost=cost ;
    	this.father=father ;
    }
    
    public void setMark(boolean mark) {
    	this.mark=mark ;
    }
    
    public void setCost(double cost) {
    	this.cost=cost ;
    }
    
    public void setFather(Arc arc) {
    	this.father=arc ;
    }
        
    public boolean getMark() {
    	return mark ;
    }
    
    public Arc getFather() {
    	return father ;
    }
    
    public double getCost() {
    	return cost ;
    }
    
    public int getCurrentNode () {
    	return currentnode ;
    }
    
    @Override
    public int compareTo (Label l) {
    	 return Double.compare(this.getCost(),l.getCost()) ;
    }

}
