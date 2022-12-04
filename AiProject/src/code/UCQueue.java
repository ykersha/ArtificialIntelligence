package code;

import java.util.PriorityQueue;

public class UCQueue extends QingFn {

	PriorityQueue<SearchTreeNode> queue;

	public UCQueue() {
		// TODO Auto-generated constructor stub
		queue = new PriorityQueue<SearchTreeNode>((SearchTreeNode a, SearchTreeNode b) -> a.pathCost - b.pathCost);
		
		/*
		 * if( a.pathCost.deaths ==  b.pathCost.deaths)
		 * 		return a.pathCost.expired - b.pathCost.expired
		 * else
		 * 		return a.pathCost.deaths -  b.pathCost.deaths
		 * */
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
