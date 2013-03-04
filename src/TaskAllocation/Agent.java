package TaskAllocation;


import java.util.Vector;

public class Agent {
	protected Location location;
	protected int id;
	protected Vector<Task> tasks;
	
	public Agent(Location location, int id) {
		super();
		tasks=new Vector<Task>();
		this.location = location;
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public Vector<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task t){
		tasks.add(t);
	}
	
	public void removeTask(Task t){
		tasks.remove(t);
	}
	@Override
	public String toString() {
		return "Agent [location=" + location + ", id=" + id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Agent))
			return false;
		Agent other = (Agent) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
