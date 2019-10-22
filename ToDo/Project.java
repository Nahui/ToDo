package ToDo;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable, Comparable<Project> {
	private static final long serialVersionUID = 1L;
	private String projectTitle;
	private String projectDueDate;
	private ArrayList<Task> TaskList;

	public Project() {
	}

	public Project(String projectTitle, String projectDueDate) {
		this.setProjectTitle(projectTitle);
		this.setProjectDueDate(projectDueDate);
		this.setTaskList(new ArrayList<>());
	}

	public Project(String projectTitle, String projectDueDate, ArrayList<Task> TaskList) {
		this.setProjectTitle(projectTitle);
		this.setProjectDueDate(projectDueDate);
		this.setTaskList(TaskList);
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDueDate() {
		return projectDueDate;
	}

	public void setProjectDueDate(String projectDueDate) {
		this.projectDueDate = projectDueDate;
	}

	public ArrayList<Task> getTaskList() {
		return TaskList;
	}

	public void setTaskList(ArrayList<Task> TaskList) {
		this.TaskList = TaskList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// add method
	public void addTaskToProject(Task task) {
		getTaskList().add(task);
	}

	// remove method
	public void removeTaskFromProject(String taskID) {
		Task a = this.findTaskInProject(taskID);
		if (a != null) {
			this.TaskList.remove(a);
		}
	}

	// find method
	public Task findTaskInProject(String taskID) {
		for (Task a : this.TaskList) {
			if (a.getTaskTitle().equals(taskID)) {
				return a;
			}
		}
		return null;
	}

	// rename method
	public void setTaskName(String taskTitle, String newTitle) {
		Task a = this.findTaskInProject(taskTitle);

		if (a != null) {
			a.setTaskTitle(newTitle);
		}
	}

	public int compareTo(Project o) {
		// TODO Auto-generated method stub
		return this.getProjectTitle().compareTo(o.getProjectTitle());
	}

	@Override
	public String toString() {
		return "Title: " + projectTitle + "\nDue date: " + projectDueDate;
	}

}
