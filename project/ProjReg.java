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

	// Adding a new project
	public void addProject(Project newProject) {
		this.getProjects().add(newProject);
	}

	// Removing a project
	public void removeProject(String idNum) {
		Project aProject = this.findProject(idNum);
		if (aProject != null) {
			this.projects.remove(aProject);
		}
	}

	// Finding a project
	public Project findProject(String idNum) {
		for (Project aProject : this.projects) {
			if (aProject.getProjectTitle().equals(idNum)) {
				return aProject;
			}
		}
		return null;
	}

	// Renaming a project
	public void setProjectTitle(String projectTitle, String newName) {
		Project aProject = this.findProject(projectTitle);
		if (aProject != null) {
			aProject.setProjectTitle(newName);
		}
	}

	// Method that shows all the projects
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

	// Method that shows all the tasks in a project
	public void showTasksFromProject() {
		Scanner input = new Scanner(System.in);
		this.showProjects();
		System.out.println("Please choose the project to view by typing its title: ");
		System.out.printf("> ");
		chosenProject = input.nextLine();
		this.showTasksFromSelectedProject(chosenProject);
		input.close();
	}

	// Method that helps the method showTasksFromProject() to show all the tasks
	// from a selected project
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

	// Method that adds a new project
	public void addNewProject() {
		Scanner input = new Scanner(System.in);
		// Collect title and due date of the project
		System.out.println("Title of project (it has to be unique): ");
		System.out.println("> ");
		chosenProjectTitle = input.nextLine();
		System.out.println("Due date of project: ");
		System.out.println("> ");
		chosenProjectDate = input.nextLine();
		Project project = new Project(chosenProjectTitle, chosenProjectDate);
		// Add the new project into the list of projects
		this.addProject(project);
		// Write the new project into the file
		this.writeToFile();
		// Let user know new project has been saved.
		System.out.println(
				"Your new project " + chosenProjectTitle + " with due date: " + chosenProjectDate + " has been added!");
		// Show all the current projects
		this.showProjects();
		input.close();
	}

	// Method that adds a new task to a selected project
	public void addNewTask() {
		Scanner input = new Scanner(System.in);
		// Choose one of the projects
		System.out.println("Please choose the project where you want to add your task by typing its title: ");
		System.out.printf("> ");
		chosenProject = input.nextLine();
		// Collect title, due date and status of the task
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
		// Add the new task into the list of tasks
		this.findProject(chosenProject).addTaskToProject(task);
		// Write task to file
		this.writeToFile();
		// Let the user know that the new task has been added
		System.out.println("Your new task " + chosenTaskTitle + " with due date: " + chosenTaskDate + " and status: "
				+ "\"" + chosenTaskStatus + "\"" + " has been added!");
		// Show all the current tasks
		this.showTasksFromSelectedProject(chosenProject);
		input.close();
	}

	// Method that allows user to edit or remove a task
	public void editTask() {
		Scanner input = new Scanner(System.in);
		boolean status = true;
		while (status == true) {
			// Show all the projects
			this.showProjects();
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

			// If user wants to change title
			if (chosenTaskInfoToEdit.equals("Title")) {
				System.out.println("Write the new title: ");
				System.out.printf("> ");
				chosenNewTitle = input.nextLine();
				this.findProject(chosenProject).findTaskInProject(chosenTask).setTaskTitle(chosenNewTitle);
				this.writeToFile();
				System.out.println("We have updated your task!");
				status = false;
			}
			// If user wants to change due date
			else if (chosenTaskInfoToEdit.equals("DueDate")) {
				System.out.println("Write the new due date: ");
				System.out.printf("> ");
				chosenNewDueDate = input.nextLine();
				this.findProject(chosenProject).findTaskInProject(chosenTask).setTaskDueDate(chosenNewDueDate);
				this.writeToFile();
				System.out.println("We have updated your task!");
				status = false;

			}
			// If user wants to change status
			else if (chosenTaskInfoToEdit.equals("Status")) {

				System.out.println("Write the new status (Done / Not Done): ");
				System.out.printf("> ");
				chosenNewStatus = input.nextLine();
				this.findProject(chosenProject).findTaskInProject(chosenTask).setTaskStatus(chosenNewStatus);
				this.writeToFile();
				System.out.println("We have updated your task!");
				status = false;
			}
			// If user wants to change task
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
		input.close();
	}

	// Method that gets the number of tasks completed and not completed
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

	// Method that helps the method getTasksDoneAndNotDone() to sum up the number of
	// tasks done
	public int numberOfTasksCompleted(ArrayList<Task> taskList) {
		int number = 0;
		for (Task i : taskList) {
			if (i.getTaskStatus().equals("Done")) {
				number++;
			}
		}
		return number;
	}

	// Method that helps the method getTasksDoneAndNotDone() to sum up the number of
	// tasks not done
	public int numberOfTasksNotCompleted(ArrayList<Task> taskList) {
		int number = 0;
		for (Task i : taskList) {
			if (i.getTaskStatus().equals("Not Done")) {
				number++;
			}
		}
		return number;
	}

	// Method that shows help options to the user
	public void getHelp() {
		System.out.println("(1) Add New Project				: Create a new project where you will be ");
		System.out.println("keeping your tasks. To create a new project you need to specify Project Title ");
		System.out.println("which has to be unique, and a project due date.");
		System.out.println("");
		System.out.println("(2) Add New Task				: Create a new task. In order to create");
		System.out.println("a new task, the program will ask you to first choose a Project where you want");
		System.out.println("to save that task. Then it will ask you to specify the Task Title, which has to");
		System.out.println("be unique, and the due date of the task.");
		System.out.println("");
		System.out.println("(3) Show Project List				: Show a list with all your created projects.");
		System.out.println("If you have no project, the list will be empty. Projects are shown in alphabetical order.");
		System.out.println("");
		System.out.println("(4) Show Task List (by project)			: Show a list of all your tasks from a");
		System.out.println("specific project. The program will ask you to choose a project and then it will");
		System.out.println("show you all the tasks in that project.");
		System.out.println("");
		System.out
				.println("(5) Edit Task (update, mark as done, remove)	: Edit a task. You can edit a task's Title,");
		System.out.println(
				"Due Date, or Status. You can also remove a task. The program will ask you what project you want,");
		System.out.println("and then it will let you choose what task of tha project you want to edit or remove.");
		System.out.println(
				"If you want to edit, it will ask you what part of the task you want to edit (Title, DueDate, Status).");
		System.out.println("");
		System.out.println("(6) Help on how to use this app.		: This help message.");
		System.out.println("");
		System.out.println("(7) Save and Quit				: Save all the changes and quit the program.");
		System.out.println("");
		System.out.printf("\n\n");
	}

	// Method that writes objects to file
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

	// Method that reads objects from file
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

	// Method that sorts by project
	public void sortProject() {
		Collections.sort(this.getProjects());
	}
}
