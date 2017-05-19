package co.bwsc.assignment3.database;

public class Pattern {
	private Integer id;
	private String name;
	private String group;
	private String implementation;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the implementation
	 */
	public String getImplementation() {
		return implementation;
	}

	/**
	 * @param implementation
	 *            the implementation to set
	 */
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	@Override
	public String toString() {
		return String.format("Pattern %d [name=%s, group=%s, implementation=%s]", id, name, group, implementation);
	}

}
