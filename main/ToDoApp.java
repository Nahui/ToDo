package main;

import java.util.Scanner;

import project.ProjReg;

public class ToDoApp {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);
		int mainChoice = 0;

		System.out.println("Welcome to ToDoApp");

		while (true) {
			ProjReg projects = new ProjReg();

			// Read all data from file
			projects.readFromFile();

			// Get tasks done and not done, NOT working
			// int result[] = projects.getTasksDoneAndNotDone();

			System.out.println();

			// System.out.println("You have " + result[0] + " to do and " + result[1] + "
			// are done!");
			System.out.println("(1) Add New Project");
			System.out.println("(2) Add New Task");
			System.out.println("(3) Show Project List");
			System.out.println("(4) Show Task List (by project)");
			System.out.println("(5) Edit Task (update, mark as done, remove)");
			System.out.println("(6) Help on how to use this app.");
			System.out.println("(7) Save and Quit");
			System.out.printf("> ");
			mainChoice = input.nextInt();

			switch (mainChoice) {

			// (1) Add New Project
			case 1:
				// Call to method that adds new project
				projects.addNewProject();
				break;

			// (2) Add New Task
			case 2:
				// Call to method that shows all projects
				projects.showProjects();
				// Call to method that adds new task to a selected project
				projects.addNewTask();
				break;

			// (3) Show project list
			case 3:
				// Call to method that shows all projects
				projects.showProjects();
				break;

			// (4) Show Task List (by project)
			case 4:
				// Call to method that shows all tasks from a project
				projects.showTasksFromProject();
				break;

			// (5) Edit Task (update, mark as done, remove)
			case 5:
				// Call to method that edits a task
				projects.editTask();
				break;

			// (6) Help
			case 6:
				// Call to method that shows help options
				projects.getHelp();
				break;

			// (7) Save and Quit
			case 7:
				System.out.println("Thank you for using our app!");
				return;

			// Wrong option
			default:
				System.out.println("That is not a valid choice.");
				break;
			}

			input.close();

		}

	}

}