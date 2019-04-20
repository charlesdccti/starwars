package br.com.starwars.component;

import java.util.HashMap;
import java.util.Map;

public class MessageBuilder {
	
	public static final String PLANET_ALREADY_EXISTS = "This planet is already registered in the database, please try a different one.";
	public static final String PLANET_SWAPI_NOT_FOUND = "Planet not found in swapi, please try another one.";
	public static final String PLANET_NOT_FOUND = "Planet not found in database, please try another one.";
	public static final String PLANET_NAME_EMPTY = "Name can't be empty.";
	public static final String PLANET_CLIMATE_EMPTY = "Climate can't be empty.";
	public static final String PLANET_TERRAIN_EMPTY = "Terrain can't be empty.";
	public static final String ERROR_NOT_RECOGNIZED = "Error not recognized for API.";

	public static Map<String, String> message(String value){
		Map<String, String> message = new HashMap<>();
		message.put("message", value);
		return message;
	}

	public static Map<String, String[]> messages(String[] values){
		Map<String, String[]> messages = new HashMap<>();
		messages.put("messages", values);
		return messages;
	}
}
