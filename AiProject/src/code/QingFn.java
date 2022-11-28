package code;

import java.util.HashSet;

public abstract class QingFn {
	
	HashSet<String> expandedNodes;
	public QingFn() {
		expandedNodes = new HashSet<String>();
	}
	
	public abstract SearchTreeNode dequeue();
	
	public abstract void enqueue(SearchTreeNode node);
	
	public abstract boolean isEmpty();
}
