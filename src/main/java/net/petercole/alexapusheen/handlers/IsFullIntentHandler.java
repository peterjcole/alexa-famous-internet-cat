package net.petercole.alexapusheen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import net.petercole.alexapusheen.model.Utils;

import static net.petercole.alexapusheen.model.Attributes.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class IsFullIntentHandler implements RequestHandler {

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
				speechText = "I'm full! You fed me "+ Utils.listToCommaString(foodList) + ".";
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
