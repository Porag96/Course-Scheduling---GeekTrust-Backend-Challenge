package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoInputFileFoundException;

public class App {

	public static void main(String[] args) {

		App.run(args);
	}

	public static void run(String[] args) {

		try {
			if (args.length == 0)
				throw new NoInputFileFoundException("Provide the input file!");

			String inputFileName = args[0];
			ApplicationConfig applicationConfiguration = new ApplicationConfig();
			CommandInvoker commandInvoker = applicationConfiguration.getCommandInvoker();

			BufferedReader reader = new BufferedReader(new FileReader(inputFileName));

			String line = reader.readLine();

			while (line != null) {
				line = line.trim();
				List<String> values = Arrays.asList(line.split(" "));
				commandInvoker.executeCommand(values.get(0), values);
				line = reader.readLine();
			}

			reader.close();

		} catch (NoInputFileFoundException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
