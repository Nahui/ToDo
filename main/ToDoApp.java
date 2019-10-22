package main;

import java.util.Scanner;

import project.ProjReg;

public class ToDoApp {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);
		String absolutePath = "/Users/valeria/Documents/eclipse-workspace/ToDoList/src/ToDo.txt";

		int mainChoice = 0;

		System.out.println("Welcome to ToDoApp");
		while (true) {
			ProjReg projects = new ProjReg();

			// get tasks done and not done, NOT working
			projects.readFromFile();
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
			// System.out.println("Type 'help' to know how to use this app.");
			System.out.printf("> ");
			mainChoice = input.nextInt();

			switch (mainChoice) {

			// (1) Add New Project
			case 1:
				// Add a new project
				projects.addNewProject();
				break;

			// (2) Add New Task
			case 2:

				// Show all projects
				projects.showProjects();
				// Add new task to a selected project
				projects.addNewTask();
				break;

			// (3) Show project list
			case 3:
				// Show all the projects
				projects.showProjects();
				break;

			// (4) Show Task List (by project)
			case 4:
				// Show all tasks from a project
				projects.showTasksFromProject();
				break;

			// (5) Edit Task (update, mark as done, remove)
			case 5:
				// Edit a task
				projects.editTask();
				break;

			// (6) Help
			case 6:
				// Show help options
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

		}

	}

}