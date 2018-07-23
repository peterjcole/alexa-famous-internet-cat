package net.petercole.alexapusheen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;


public class IsFullIntentHandler implements RequestHandler {


	@Override
	public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("IsFullIntent"));

	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText;
		String fedFood = (String) input.getAttributesManager().getSessionAttributes().get("Fed");
		
		if (fedFood != null && !fedFood.isEmpty()) {
			speechText = String.format("You fed me %s. I'm super full. Burp", fedFood);
		
		} else {
			speechText = "I'm hungry. Feed me something!";
		}
		
		return input.getResponseBuilder()
				.withSpeech(speechText)
				.withSimpleCard("HelloWorld", speechText)
				.build();
		
	}

}
