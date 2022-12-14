package code;

import java.util.PriorityQueue;

public class UCQueue extends QingFn {

	PriorityQueue<SearchTreeNode> queue;

	public UCQueue() {
		// TODO Auto-generated constructor stub
		
		//deaths = pathCost[0], blackBoxesExpired = pathCost[1]
		queue = new PriorityQueue<SearchTreeNode>(
				(SearchTreeNode a, SearchTreeNode b) -> a.pathCost[0] == b.pathCost[0] ? a.pathCost[1] - b.pathCost[1]
						: a.pathCost[0] - b.pathCost[0]);

	}

	@Override
	public SearchTreeNode dequeue() {
		// TODO Auto-generated method stub
//		System.out.println(queue.peek().pathCost);
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
