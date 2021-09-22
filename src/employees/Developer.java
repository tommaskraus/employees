package employees;

/**
 * Typ zamestnance Developer
 *
 */
public class Developer extends Human {
	
	/**
	 * Notebook, ktery dostal pro praci
	 */
	private String notebook;
	
	/**
	 * Priorita developera, cim mensi tim zkusenejsi
	 */
	private int priority;
	
	private Developer(Builder builder) {
		super(builder.name);
		this.salary = builder.salary;
		this.priority = builder.priority;
		this.notebook = builder.notebook;
	}

	public String getNotebook() {
		return notebook;
	}

	public int getPriority() {
		return priority;
	}

	public static Builder createBuilder(String name) {
		return new Builder(name);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", priorita: " + priority + ", notebook: " + (notebook != null && notebook.length() > 0 ? notebook : "nema");
	}
	
	protected static class Builder {
		
		private final String name;
		private int salary;
		private int priority;
		private String notebook = "";
		
		private Builder(String name) {
			if (name == null) {
				name = "";
			}
			this.name = name;
		}
		
		public Builder setSalary(int salary) {
			this.salary = salary;
			return this;
		}
		
		public Builder setPriority(int priority) {
			this.priority = priority;
			return this;
		}
		
		public Builder setNotebook(String notebook) {
			this.notebook = notebook;
			return this;
		}
		
		
		public Human build() {
			
			Human human = new Developer(this);
			
			return human;
		}
	}
	
}
