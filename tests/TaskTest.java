package tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Task;

class TaskTest {

	private Task t1 = new Task();
	private Task t2 = new Task();

	@BeforeEach
	void setUp() {
		t1.setTaskTitle("Jump");
		t1.setTaskDueDate("1234");
		t1.setTaskStatus("done");

		t2.setTaskTitle("Swim");
		t2.setTaskDueDate("12399");
		t2.setTaskStatus("not done");
	}

	@Test
	void testGetTaskTitle() {
		Assert.assertTrue(t1.getTaskTitle().equals("Jump"));
		Assert.assertFalse(t1.getTaskTitle().contentEquals("Dance"));
	}

	@Test
	void testGetTaskDueDate() {
		Assert.assertTrue(t1.getTaskDueDate().equals("1234"));
		Assert.assertFalse(t1.getTaskDueDate().contentEquals("45345"));
	}

	@Test
	void testGetTaskStatus() {
		Assert.assertTrue(t1.getTaskStatus().equals("done"));
		Assert.assertFalse(t1.getTaskStatus().contentEquals("not done"));

	}

}
