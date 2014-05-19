package com.ghsoft.treetask;

import java.io.Serializable;

public class TaskLeaf extends Task implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean complete;

	public TaskLeaf(TaskNode parent) {
		super(parent);
		this.complete = false;
	}

	public static TaskLeaf fromNode(TaskNode from) {
		TaskLeaf tl = new TaskLeaf((TaskNode) from.getParent());
		tl.setName(from.getName());
		tl.setDescription(from.getDescription());
		tl.setColor(from.getColor());
		tl.setTimeStamp(from.getTimeStamp());
		tl.setWeight(from.getWeight());
		return tl;
	}

	@Override
	public int completion() {
		return complete ? 100 : 0;
	}

	@Override
	public int subTaskCount() {
		return 0;
	}

	public void setFinished(boolean set) {
		this.complete = set;
	}

	public boolean getFinished() {
		return complete;
	}

}
