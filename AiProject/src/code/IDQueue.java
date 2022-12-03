package code;

import java.util.Stack;

public class IDQueue extends QingFn {

	int depth;
	Stack<SearchTreeNode> st;

	public IDQueue(int depth) {
		// TODO Auto-generated constructor stub
		st = new Stack<SearchTreeNode>();
		this.depth = depth;
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
		if (node.depth <= depth && !expandedNodes.contains(nodeEnc)) {
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
