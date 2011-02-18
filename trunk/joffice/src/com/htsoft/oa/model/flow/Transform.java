package com.htsoft.oa.model.flow;

import org.jbpm.pvm.internal.model.Transition;

public class Transform {
	private String name;
	private String destination;
	private String source;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Transform() {
	}

	public Transform(Transition jbpmtran) {
		this.name = jbpmtran.getName();
		this.source = jbpmtran.getSource().getName();
		this.destination = jbpmtran.getDestination().getName();
	}
}


 
 
 
 