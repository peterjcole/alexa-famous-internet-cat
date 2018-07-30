package net.petercole.alexapusheen.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

import net.petercole.alexapusheen.model.Utils;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import static net.petercole.alexapusheen.model.Attributes.*;

import java.util.ArrayList;
import java.util.List;
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
		
		Slot foodSlot = slots.get(FOOD_SLOT);
		String fedFood = foodSlot.getValue();

		String speechText, repromptText = null;
		boolean isAskResponse = false;
		
		Map<String, Object> sessionMap = input.getAttributesManager().getSessionAttributes();
		
		@SuppressWarnings("unchecked")
		List<String> foodList = (List<String>) sessionMap.get(FOOD_KEY);
		// done: refactor using a list
		if (foodSlot.getValue() != null) {
			if (foodList != null) {
				if (foodList.size() > 2) {
					//full
					String allFood = Utils.listToCommaString(foodList);
					speechText = String.format("No thank you, I'm full. You already fed me %s.", allFood);
				} else if (foodList.size() == 2) {
					//just filled up
					foodList.add(fedFood);
					String allFood = Utils.listToCommaString(foodList);
					speechText = "Burp, thanks for the " + fedFood + ". I'm full now! You fed me " + allFood;

				} else {
					foodList.add(fedFood);
					speechText = String.format("Burp, thanks for the %s. I'm getting fuller but I'm still hungry!", fedFood);
				}
			} else {
				// First bit of food
				foodList = new ArrayList<String>();
				foodList.add(fedFood);
				sessionMap.put(FOOD_KEY, foodList);
				input.getAttributesManager().setSessionAttributes(sessionMap);
				speechText = String.format("Meow, thanks for the %s. I'm still hungry!", fedFood);
				repromptText = "Feed me more food!";
				} 
			
		} else {
			// no food provided
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
