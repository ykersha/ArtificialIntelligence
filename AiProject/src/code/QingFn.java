package code;

public abstract class QingFn {
	
	public QingFn() {
		
	}
	
	public abstract SearchTreeNode dequeue();
	
	public abstract void enqueue(SearchTreeNode node);
	
	public abstract boolean isEmpty();
}
