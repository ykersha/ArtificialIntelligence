package code;

import java.util.Stack;

public class DFQueue extends QingFn{

	Stack<SearchTreeNode> st;
	public DFQueue() {
		// TODO Auto-generated constructor stub
		st = new Stack<SearchTreeNode>();
	}

	@Override
	public SearchTreeNode dequeue() {
		// TODO Auto-generated method stub
		return st.pop();
	}
	@Override
	public void enqueue(SearchTreeNode node) {
		// TODO Auto-generated method stub
		String nodeEnc = node.toString();
		if (!expandedNodes.contains(nodeEnc)) {
			st.push(node);
			expandedNodes.add(nodeEnc);
		}
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return st.isEmpty();
	}
	
}
