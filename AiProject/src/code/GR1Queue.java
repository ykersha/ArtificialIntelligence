package code;

import java.util.PriorityQueue;

//treated as A* for now
public class GR1Queue extends QingFn {

	PriorityQueue<SearchTreeNode> queue;

	public GR1Queue() {
		queue = new PriorityQueue<SearchTreeNode>((SearchTreeNode a,
				SearchTreeNode b) -> (a.deathHeuristic1() == b.deathHeuristic1()
						? (a.expiredHeuristic() - b.expiredHeuristic())
						: a.deathHeuristic1() - b.deathHeuristic1()));

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
