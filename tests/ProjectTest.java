package tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Project;

class ProjectTest {

	private Project p1 = new Project();
	private Project p2 = new Project();

	@BeforeEach
	void setUp() {
		p1.setProjectTitle("School");
		p1.setProjectDueDate("1234");

		p2.setProjectTitle("Home");
		p2.setProjectDueDate("12399");
	}

	@Test
	void testGetProjectTitle() {
		Assert.assertTrue(p1.getProjectTitle().equals("School"));
		Assert.assertFalse(p1.getProjectTitle().contentEquals("Home"));
	}

	@Test
	void testGetProjectDueDate() {
		Assert.assertTrue(p1.getProjectDueDate().equals("1234"));
		Assert.assertFalse(p1.getProjectDueDate().contentEquals("45345"));
	}

}
