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
		
		Slot foodSlot = slots.get("Food");
		
		String speechText, repromptText;
		boolean isAskResponse = false;
		
		if (foodSlot != null) {
			String fedFood = foodSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap("Fed", fedFood));
			speechText = String.format("Burp, thanks for the %s. You can ask me whether I'm full.", fedFood);
			repromptText = "You can ask me whether I'm full by saying, are you full?";
			
			
		} else {
			speechText = "I'm hungry, please feed me something.";
			repromptText = "I'm hungry, please feed me something.";
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
