package com.carbonzero.project;

import com.carbonzero.gui.impl.LoginScreen;
import com.carbonzero.gui.services.UIManagerAdjuster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com/carbonzero/databaseconnection/impl"})
public class ProjectApplication {

	public static void main(String[] args) {

		// TODO SPLASH SCREEN

		SpringApplication.run(ProjectApplication.class, args);

		System.setProperty("java.awt.headless", "false");
		UIManagerAdjuster.adjustUI();
		LoginScreen loginScreen = new LoginScreen();


		System.out.println("Program is running!!!");

	}

}
