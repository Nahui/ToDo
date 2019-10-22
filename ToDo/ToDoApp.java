package ToDo;

import java.util.Scanner;

public class ToDoApp {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);
		String absolutePath = "/Users/valeria/Documents/eclipse-workspace/ToDoList/src/ToDo.txt";

		// Our example data
//		Task task1 = new Task("1", "Sleep", "12122019", true);
//		Task task2 = new Task("2", "Eat", "12122020", true);
//
//		// Adding example data to array
//		ArrayList<Task> tasks = new ArrayList<Task>();
//		tasks.add(task1);
//		tasks.add(task2);

		int tasksToDo = 0;
		int tasksDone = 0;

		int mainChoice = 0;
		String chosenProject, chosenTaskTitle, chosenTaskDate, chosenTaskStatus, chosenTask, chosenTaskInfoToEdit,
				chosenNewTitle, chosenNewDueDate, chosenNewStatus, chosenProjectTitle, chosenProjectDate;
		int count = 0;

		System.out.println("Welcome to ToDoApp");
		while (true) {

			System.out.println();

			System.out.println("You have " + tasksToDo + " to do and " + tasksDone + " are done!");
			System.out.println("(1) Show Project List");
			System.out.println("(2) Show Task List (by date or project)");
			System.out.println("(3) Add New Project");
			System.out.println("(4) Add New Task");
			System.out.println("(5) Edit Task (update, mark as done, remove)");
			System.out.println("(6) Save and Quit");
			// System.out.println("Type 'help' to know how to use this app.");
			System.out.printf("> ");

			ProjReg projects = new ProjReg();
			projects.readFromFile();

			mainChoice = input.nextInt();

			switch (mainChoice) {

			// (1) Show project list
			case 1:
				// Show all the projects
				projects.showProjects();

				break;

			// (2) Show Task List (by date or project)
			case 2:

				// Show all tasks from a project
				projects.showTasksFromProject();

				break;

			// (3) Add New Project
			case 3:
				// Add a new project
				projects.addNewProject();

				break;

			// (4) Add New Task
			case 4:
				// Show all projects
				projects.showProjects();
				// Add new task to a selected project
				projects.addNewTask();

				break;

			// (5) Edit Task (update, mark as done, remove)
			case 5:
				// Edit a task
				projects.editTask();

				break;

			// (6) Save and Quit
			case 6:
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
