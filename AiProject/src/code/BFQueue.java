package code;

import java.util.LinkedList;
import java.util.Queue;

public class BFQueue extends QingFn {

	Queue<SearchTreeNode> queue;

	public BFQueue() {
		// TODO Auto-generated constructor stub
		queue = new LinkedList<SearchTreeNode>();
	}

	@Override
	public SearchTreeNode dequeue() {
		// TODO Auto-generated method stub
		return queue.poll();
	}

	@Override
	public void enqueue(SearchTreeNode node) {
		// TODO Auto-generated method stub

		String nodeEnc = node.toString();
		if (!expandedNodes.contains(nodeEnc)) {
			queue.add(node);
			expandedNodes.add(nodeEnc);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

}
