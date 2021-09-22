package employees;

public class Human {
	
	/**
	 * Jmeno zamestnance
	 */
	protected final String name; //required
	
	/**
	 * Plat
	 */
	protected int salary; //optional
	
	protected Human(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder(name);
		stringBuilder.append(", pozice: ");
		stringBuilder.append(getClass().getSimpleName());
		stringBuilder.append(", plat: ");
		stringBuilder.append(salary);
		
		return stringBuilder.toString();
	}
	
	
}
