package net.petercole.alexapusheen.handlers;

import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

public class PangPangIntentHandler implements RequestHandler {

	public static final String PANG_SLOT = "Pang";
	
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("PangPangIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		Intent intent = intentRequest.getIntent();
		
		Map<String, Slot> slots = intent.getSlots();
		Slot pangSlot = slots.get(PANG_SLOT);
		String speechText = "meow";
		
		
		if (pangSlot.getValue() != null) {
			// 2 or more pangs
			String pangs = pangSlot.getValue();
			String[] pangsArray = pangs.split("\\s");
			int numPangs = pangsArray.length;
			
			if (numPangs < 4) {
				for (int x=0; x<numPangs; x++) {
					speechText = speechText + " meow";
				}
			} else {
				speechText = "Stamina";
				}
			
			
		}
		
		ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("Hello World", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        return responseBuilder.build();	
		
	}

}
