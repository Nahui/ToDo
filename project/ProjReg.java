package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProjReg {
	private ArrayList<Project> projects = new ArrayList<>();
	private String chosenProject;

	public ProjReg() {
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	String chosenTask, chosenTaskTitle, chosenTaskDate, chosenTaskStatus, chosenProjectTitle, chosenProjectDate,
			chosenTaskInfoToEdit, chosenNewTitle, chosenNewDueDate, chosenNewStatus;

	// add method
	public void addProject(Project newProject) {
		this.getProjects().add(newProject);
	}

	// remove method
	public void removeProject(String idNum) {
		Project a = this.findProject(idNum);
		if (a != null) {
			this.projects.remove(a);
		}
	}

	// find method
	public Project findProject(String idNum) {
		for (Project a : this.projects) {
			if (a.getProjectTitle().equals(idNum)) {
				return a;
			}
		}
		return null;
	}

	// rename method
	public void setProjectTitle(String taskTitle, String newName) {
		Project a = this.findProject(taskTitle);

		if (a != null) {
			a.setProjectTitle(newName);
		}
	}

	public void showProjects() {
		System.out.println("************ * ***********");
		System.out.println("These are all your projects: ");
		System.out.println("************ * ***********");
		System.out.println("\n");
		System.out.println("*** * ***");
		for (Project aProject : this.getProjects()) {
			System.out.println("\n");
			System.out.println(aProject.toString());
			System.out.println("\n");
			System.out.println("*** * ***");
		}
	}

	public void showTasksFromProject() {
		Scanner input = new Scanner(System.in);
		this.showProjects();
		System.out.println("Please choose the project to view by typing its title: ");
		System.out.printf("> ");
		chosenProject = input.nextLine();

		this.showTasksFromSelectedProject(chosenProject);

	}

	public void showTasksFromSelectedProject(String myProject) {

		System.out.println("************ * ***********");
		System.out.println("These are all the tasks in your project " + myProject + ": ");
		System.out.println("************ * ***********");
		System.out.println("\n");
		System.out.println("*** * ***");
		for (Task aTask : this.findProject(myProject).getTaskList()) {
			System.out.println("\n");
			System.out.println(aTask.toString());
			System.out.println("\n");
			System.out.println("*** * ***");
		}

	}

	public void addNewProject() {
		Scanner input = new Scanner(System.in);
		System.out.println("Title of project (it has to be unique): ");
		System.out.println("> ");
		chosenProjectTitle = input.nextLine();

		System.out.println("Due date of project: ");
		System.out.println("> ");
		chosenProjectDate = input.nextLine();
		// chosenProjectDate = "123";

		Project project = new Project(chosenProjectTitle, chosenProjectDate);
		this.addProject(project);

		this.writeToFile();
		this.showProjects();

		System.out.println(
				"Your new project " + chosenProjectTitle + " with due date: " + chosenProjectDate + " has been added!");

		this.showProjects();
	}

	public void addNewTask() {
		Scanner input = new Scanner(System.in);
		// Choose one of the projects
		System.out.println("Please choose the project where you want to add your task by typing its title: ");
		System.out.printf("> ");
		chosenProject = input.nextLine();

		// Add new task
		System.out.println("Title of task (it has to be unique): ");
		System.out.printf("> ");
		chosenTaskTitle = input.nextLine();
		System.out.println("Due date of task: ");
		System.out.printf("> ");
		chosenTaskDate = input.nextLine();
		System.out.println("Status of task (done / not done: ");
		System.out.printf("> ");
		chosenTaskStatus = input.nextLine();

		Task task = new Task(chosenTaskTitle, chosenTaskDate, chosenTaskStatus);

		this.findProject(chosenProject).addTaskToProject(task);

		// write task to file
		this.writeToFile();

		// Print that it was added
		System.out.println("Your new task " + chosenTaskTitle + " with due date: " + chosenTaskDate + " and status: "
				+ "\"" + chosenTaskStatus + "\"" + " has been added!");

		// Show all the tasks
		this.showTasksFromSelectedProject(chosenProject);
	}

	public void editTask() {
		Scanner input = new Scanner(System.in);
		boolean status = true;
		while (status == true) {
			this.showProjects();
			// showTasks()

			// Show projects
			// System.out.println("These are all your projects: ");
			// projects.getProjects();

			// Choose one of the projects
			System.out.println("Please choose the project where you want to edit your task by typing its title: ");
			System.out.printf("> ");
			chosenProject = input.nextLine();

			// Show the list of tasks for that project
			this.showTasksFromSelectedProject(chosenProject);

			// Choose a task to edit
			System.out.println("Choose the task you want to edit by typing its title: ");
			System.out.printf("> ");
			chosenTask = input.nextLine();

			System.out.println("Choose what you want to edit from the chosen task (Title / DueDate / Status). ");
			System.out.println("If you want to remove the task then write \"Remove\": ");
			System.out.printf("> ");
			chosenTaskInfoToEdit = input.nextLine();

			// Change title
			if (chosenTaskInfoToEdit.equals("Title")) {
				System.out.println("Write the new title: ");
				System.out.printf("> ");
				chosenNewTitle = input.nextLine();
				this.findProject(chosenProject).findTaskInProject(chosenTask).setTaskTitle(chosenNewTitle);
				this.writeToFile();
				System.out.println("We have updated your task!");
				status = false;
			}
			// Change due date
			else if (chosenTaskInfoToEdit.equals("DueDate")) {
				System.out.println("Write the new due date: ");
				System.out.printf("> ");
				chosenNewDueDate = input.nextLine();
				this.findProject(chosenProject).findTaskInProject(chosenTask).setTaskDueDate(chosenNewDueDate);
				this.writeToFile();
				System.out.println("We have updated your task!");
				status = false;

			}
			// Change status
			else if (chosenTaskInfoToEdit.equals("Status")) {

				System.out.println("Write the new status (Done / Not Done): ");
				System.out.printf("> ");
				chosenNewStatus = input.nextLine();
				this.findProject(chosenProject).findTaskInProject(chosenTask).setTaskStatus(chosenNewStatus);
				this.writeToFile();
				System.out.println("We have updated your task!");
				status = false;
			}
			// Remove task
			else if (chosenTaskInfoToEdit.equals("Remove")) {
				this.findProject(chosenProject).removeTaskFromProject(chosenTask);
				this.writeToFile();
				System.out.println("Your task has been removed!");
				status = false;
			}

			// Wrong option
			else {
				System.out.println("That is not a valid choice.");
			}
		}
	}

	public int[] getTasksDoneAndNotDone() {
		ArrayList<Task> tempTaskList = new ArrayList<Task>();
		int tasksToDo = 0;
		int tasksDone = 0;

		for (Project p : this.getProjects()) {
			for (Task t : p.getTaskList()) {
				tempTaskList.add(t);
			}
		}

		tasksToDo = this.numberOfTasksCompleted(tempTaskList);
		tasksDone = this.numberOfTasksNotCompleted(tempTaskList);

		return new int[] { tasksToDo, tasksDone };

	}

	public int numberOfTasksCompleted(ArrayList<Task> taskList) {
		int number = 0;
		for (Task i : taskList) {
			if (i.getTaskStatus().equals("Done")) {
				number++;
			}
		}
		return number;
	}

	public int numberOfTasksNotCompleted(ArrayList<Task> taskList) {
		int number = 0;
		for (Task i : taskList) {
			if (i.getTaskStatus().equals("Not Done")) {
				number++;
			}
		}
		return number;
	}

	public void writeToFile() {

		try {
			FileOutputStream f = new FileOutputStream(new File("MyFile.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// write objects to file
			for (Project aProject : this.getProjects()) {
				o.writeObject(aProject);
			}
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			// System.out.println("Error initializing stream");
		}

	}

	public void readFromFile() {

		try {
			FileInputStream fi = new FileInputStream(new File("MyFile.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// read objects from file
			Project p = (Project) oi.readObject();

			while (p != null) {
				this.addProject(p);
				p = (Project) oi.readObject();
			}

			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println(
					"File not found. We will create the file for you once you create a new Project (option 3).");
		} catch (IOException e) {
			// System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void sortProject() {
		Collections.sort(this.getProjects());
	}
}
