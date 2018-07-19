package net.petercole.alexapusheen;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

import net.petercole.alexapusheen.handlers.*;

public class PusheenStreamHandler extends SkillStreamHandler{

	private static Skill getSkill() {
		return Skills.standard()
				 .addRequestHandlers(new FallbackIntentHandler(), new CancelandStopIntentHandler(), new HelloWorldIntentHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler(), new TinyIntentHandler())
                 .build();
	}
	
	   public PusheenStreamHandler() {
	          super(getSkill());
	      }

}
