package code;

import java.util.PriorityQueue;

//treated as A* for now
public class GR1Queue extends QingFn {

	PriorityQueue<SearchTreeNode> queue;

	public GR1Queue() {
		// TODO Auto-generated constructor stub

		// deaths = pathCost[0], blackBoxesExpired = pathCost[1]

		// sort based on heuristic and if h is equal sort with lower expired first
//		queue = new PriorityQueue<SearchTreeNode>((SearchTreeNode a,
//				SearchTreeNode b) -> a.pathCost[0] + a.AsHeuristic3() == b.pathCost[0] + b.AsHeuristic3()
//						? a.pathCost[1] - b.pathCost[1]
//						: (a.pathCost[0] + a.AsHeuristic3()) - (b.pathCost[0] + b.AsHeuristic3()));

		queue = new PriorityQueue<SearchTreeNode>(
				(SearchTreeNode a, SearchTreeNode b) -> a.GrHeuristic1() - b.GrHeuristic1());
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
