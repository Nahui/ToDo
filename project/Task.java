package project;
import java.io.Serializable;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	// private String taskID;
	private String taskTitle;
	private String taskDueDate;
	private String taskStatus;

	public Task() {
	}

	public Task(String taskTitle, String taskDueDate, String taskStatus) {
		// this.setTaskID(taskID);
		this.setTaskTitle(taskTitle);
		this.setTaskDueDate(taskDueDate);
		this.setTaskStatus(taskStatus);
	}

//	public String getTaskID() {
//		return taskID;
//	}
//
//	public void setTaskID(String taskID) {
//		this.taskID = taskID;
//	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "Title: " + taskTitle + "\nDueDate: " + taskDueDate + "\nStatus: " + taskStatus;
	}

}
