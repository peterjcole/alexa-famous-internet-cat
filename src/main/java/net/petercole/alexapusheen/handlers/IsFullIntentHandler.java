package net.petercole.alexapusheen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import static net.petercole.alexapusheen.handlers.IsFullIntentHandler.FOOD_KEY;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;


public class IsFullIntentHandler implements RequestHandler {
	public static final String FOOD_KEY = "Fed";
	public static final String FOOD_SLOT = "Food";

	public String listToString (List<String> list) {
		StringJoiner joiner = new StringJoiner(", ");
		list.forEach(item->joiner.add(item));
		return joiner.toString(); 
		
	}
	
	@Override
	public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("IsFullIntent"));

	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		Map<String, Object> sessionMap = input.getAttributesManager().getSessionAttributes();
		if (sessionMap.containsKey(FOOD_KEY)) {
			List<String> foodList = (List<String>) sessionMap.get(FOOD_KEY);
			if (foodList.size() >= 3) {
				speechText = "I'm full! You fed me "+ listToString(foodList) + ".";
				//full
			} else { 
				//not full
				speechText = "I'm still hungry, feed me now.";
				
			}
			
		} else {
			//not fed
			speechText = "Of course I'm not full, you haven't fed me anything!";
		}
		
		
		
		return input.getResponseBuilder()
				.withSpeech(speechText)
				.withSimpleCard("HelloWorld", speechText)
                .withShouldEndSession(false)
				.build();
		
	}


}
