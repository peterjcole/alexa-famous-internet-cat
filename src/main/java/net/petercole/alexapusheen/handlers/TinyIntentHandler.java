package net.petercole.alexapusheen.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;
import java.util.Random;

public class TinyIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		// TODO Auto-generated method stub
        return input.matches(Predicates.intentName("TinyIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
        String[] tinyResponse = new String[] { "get groomed with a toothbrush", "bathe in a teacup", "play with thread", "step on a marshmallow"};
 		int rnd = new Random().nextInt(tinyResponse.length);
		String speechText = tinyResponse[rnd];
         
        return input.getResponseBuilder()
                 .withSpeech(speechText)
                 .withSimpleCard("HelloWorld", speechText)
                 .build();

	}

}
