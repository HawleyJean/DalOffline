package com.csci3130.daloffline.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		// not all these actually work since their methods don't exist
		Result result = JUnitCore.runClasses(AuthenticatorTest.class, UITest.class, CourseTest.class, UserTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}