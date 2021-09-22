package employees;

/**
 * Typ zamestnance Manager
 *
 */
public class Manager extends Human {
	
	/**
	 * Znacka a typ mobilniho telefonu
	 */
	private String mobilePhone;
	
	protected Manager(Builder builder) {
		super(builder.name);
		this.salary = builder.salary;
		this.mobilePhone = builder.mobilePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public static Builder createBuilder(String name) {
		return new Builder(name);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", mobil: " + mobilePhone;
	}

	protected static class Builder {
		
		private final String name;
		private int salary;
		private String mobilePhone = "";
		
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
		
		public Builder setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
			return this;
		}
		
		
		public Human build() {
			
			Human human = new Manager(this);
			
			return human;
		}
	}
}
