package net.petercole.alexapusheen.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import static net.petercole.alexapusheen.handlers.IsFullIntentHandler.FOOD_KEY;
import static net.petercole.alexapusheen.handlers.IsFullIntentHandler.FOOD_SLOT;




public class FeedMeIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("FeedMeIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();
		
		Slot foodSlot = slots.get(FOOD_SLOT);
		
		String speechText, repromptText = null;
		boolean isAskResponse = false;
		
		Map<String, Object> sessionMap = input.getAttributesManager().getSessionAttributes();
	
		// todo: refactor using an array
		if (foodSlot.getValue() != null) {
			if (sessionMap.size() > 2) {
				StringJoiner joiner = new StringJoiner(", ");
				sessionMap.forEach((k,v)->joiner.add(v.toString()));
				String foodList = joiner.toString();

				speechText = String.format("No thank you, I'm full. You already fed me %s.", foodList);
			} else {
				String fedFood = foodSlot.getValue();
				String key = "Food " + (sessionMap.size() + 1);
				sessionMap.put(key, fedFood);
				input.getAttributesManager().setSessionAttributes(sessionMap);
				speechText = String.format("Meow, thanks for the %s. I'm still hungry!", fedFood);
				repromptText = "Feed me more food!";
				
			}
			

			
		} else {
			speechText = "What is this rubbish? I'm hungry, please feed me something that I want to eat!";
			repromptText = "What is this rubbish? I'm hungry, please feed me something that I want to eat!";
			isAskResponse = true;
			
		}
		
		ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("Hello World", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();	
		
	}

}
