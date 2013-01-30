package br.com.zilics.archetypes.models.rm.utils.path.parsed;


public abstract class TestNode extends TreeNode {

	private static final long serialVersionUID = 9089027882731588690L;
	private final String nodeTest;
	private final boolean anyAllowed;
	
	public TestNode(String nodeTest) {
		if (nodeTest == null)
			throw new NullPointerException("Null node test");
		this.nodeTest = nodeTest;
		if ("*".equals(nodeTest)) anyAllowed = true;
		else anyAllowed = false;
	}
	
	public String getNodeTest() {
		return nodeTest;
	}
	
	public boolean isAnyAllowed() {
		return anyAllowed;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return nodeTest;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		TestNode other = (TestNode) obj;
		
		return (nodeTest.equals(other.nodeTest));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return nodeTest.hashCode();
	}
}

