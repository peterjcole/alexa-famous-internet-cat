package net.petercole.alexapusheen.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;
public class LaunchRequestHandler implements RequestHandler {

	 @Override
     public boolean canHandle(HandlerInput input) {
         return input.matches(Predicates.requestType(LaunchRequest.class));
     }

     @Override
     public Optional<Response> handle(HandlerInput input) {
         String speechText = "Hi, I am pusheen. Say hello, or ask me what I would do if I were tiny.";
         return input.getResponseBuilder()
                 .withSpeech(speechText)
                 .withSimpleCard("HelloWorld", speechText)
                 .withReprompt(speechText)
                 .build();
     }

}
