package net.petercole.alexapusheen.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import net.petercole.alexpusheen.model.Constants;
import net.petercole.alexpusheen.model.Attributes;


public class RecipeStartOrNextIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("RecipeStartOrNextIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		//todo: new intents to handle previous and repeat
		//todo: use states to implement multiple recipes
		String speechText;
		Map<String, Object> sessionMap = input.getAttributesManager().getSessionAttributes();
		String state = (String) sessionMap.get(Attributes.STATE_KEY);
		
		if (state.equals(Attributes.START_STATE)) {
			// give recipe item 1
			sessionMap.put(Attributes.STATE_KEY, Attributes.RECIPE_STATE);
			sessionMap.put(Attributes.RECIPE_ITEM_KEY, 0);
			speechText = Constants.INSTRUCTIONS.get(0).toString();

		} else if (state.equals(Attributes.RECIPE_STATE)) {
			
			int lastItem = (int) sessionMap.get(Attributes.RECIPE_ITEM_KEY);
			
			if (lastItem < (Constants.INSTRUCTIONS.size()-1)) {
				int nextInstruction = lastItem + 1;
				speechText = Constants.INSTRUCTIONS.get(nextInstruction).toString();
				sessionMap.put(Attributes.RECIPE_ITEM_KEY, nextInstruction);
			} else {
				//lastItem == Constants.INSTRUCTIONS.size()) 
				speechText = "That was all the instructions! Hope you enjoy your pizza, meow";
			}
		
		} else {
			// not been given info yet
			speechText = "Ask me about my pizza dough recipe!";
			}
			
			
		
		return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(Constants.HELP_MESSAGE)
                .withShouldEndSession(false)
                .build();

	}



}
