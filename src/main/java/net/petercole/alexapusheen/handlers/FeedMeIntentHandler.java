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
		
		String speechText, repromptText;
		boolean isAskResponse = false;
		
		if (foodSlot.getValue() != null) {
			String fedFood = foodSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(FOOD_KEY, fedFood));
			speechText = String.format("Meow, thanks for the %s. You can ask me whether I'm full.", fedFood);
			repromptText = "You can ask me whether I'm full by saying, are you full?";
			
			
		} else {
			speechText = "I'm hungry, please feed me something that I want to eat!";
			repromptText = "I'm hungry, please feed me something that I want to eat!";
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
