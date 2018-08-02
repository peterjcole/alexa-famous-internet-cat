package net.petercole.alexapusheen.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import net.petercole.alexapusheen.model.Attributes;
import net.petercole.alexapusheen.model.Constants;

public class RecipeRepeatIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("RecipeRepeatIntent"));

	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		
		Map<String, Object> sessionMap = input.getAttributesManager().getSessionAttributes();
		Optional<Object> state = Optional.ofNullable(sessionMap.get(Attributes.STATE_KEY));
		
		if (state.isPresent()) {
			speechText = "state is present";
			if (state.get().equals(Attributes.START_STATE)) {
				RecipeInfoIntentHandler infoHandler = new RecipeInfoIntentHandler();
				return infoHandler.handle(input);			
							
			} else if (state.get().equals(Attributes.RECIPE_STATE)) {
				int recipeItem = (int) sessionMap.get(Attributes.RECIPE_ITEM_KEY);
				speechText = Constants.INSTRUCTIONS.get(recipeItem).toString();
				}
			
			
		} else {
			speechText = "Ask me about my pizza dough recipe!";
		}
	
	
	return input.getResponseBuilder()
            .withSpeech(speechText)
            .withReprompt(Constants.HELP_MESSAGE)
            .withShouldEndSession(false)
            .build();
		
	} 

	

}
