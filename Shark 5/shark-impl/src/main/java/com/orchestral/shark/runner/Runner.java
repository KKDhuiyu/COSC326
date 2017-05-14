package com.orchestral.shark.runner;
import java.util.Scanner;

import org.json.JSONObject;

import com.orchestral.shark.YourStrategy;
import com.orchestral.shark.ai.CardGameStrategy;
import com.orchestral.shark.command.CommandManager;
import com.orchestral.shark.lib.GameState;
import com.orchestral.shark.lib.Move;

public class Runner {
	public static CardGameStrategy ai;
	public static CommandManager commandManager;

	public static void main(final String[] args) {
		ai = new YourStrategy();
		commandManager = new CommandManager();

		// Check we have the right number of arguments
		// if (args.length != 2) {
		// System.err.println("Usage: java -jar AI.jar <command> <arguments>\nWith arguments in parsable json form");
		// return;
		// }
		// Set up commands
		commandManager.registerCommand("playcardfromhand", (theArgs) -> commandPlayCardFromHand(theArgs));

		// Get command and arguments
		final Scanner sc = new Scanner(System.in);
		while (true) {
			final String command = sc.nextLine();
			if (command.toLowerCase().equals("end")) {
				break;
			}

			final JSONObject arguments = new JSONObject(sc.nextLine());

			// Perform Commands
			commandManager.runCommand(command.toLowerCase(), arguments);
			System.out.flush();

		}
		sc.close();
	}

	public static void commandPlayCardFromHand(final JSONObject arguments) {
		final GameState gameState = GameState.fromJson(arguments.getJSONObject("gamestate"));
		final Move result = ai.playCardFromHand(gameState);
		final Integer moveID = arguments.getInt("moveID");
		final JSONObject resultObj = new JSONObject();
		resultObj.put("move", result.toJson());
		resultObj.put("moveID", moveID);
		System.out.println(resultObj);
	}

}

