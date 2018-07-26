package net.petercole.alexapusheen.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import net.petercole.alexpusheen.model.Attributes;
import net.petercole.alexpusheen.model.Constants;

public class RecipeInfoIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("RecipeInfoIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		 Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
	        sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
	        return input.getResponseBuilder()
	                .withSpeech(Constants.INFO_MESSAGE)
	                .withReprompt(Constants.HELP_MESSAGE)
	                .withShouldEndSession(false)
	                .build();
	}



}
