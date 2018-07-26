package net.petercole.alexpusheen.model;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final List<RecipeInstruction> INSTRUCTIONS = Arrays.asList(
		new RecipeInstruction(1, "Preheat oven to 230 degrees C"),	
		new RecipeInstruction(2, "In a medium bowl, dissolve 1 package of yeast and 1 teaspoon of white sugar in warm water"),
		new RecipeInstruction(3, "Let stand until creamy, about 10 minutes"),
		new RecipeInstruction(4, "Stir in 2/12 cups bread flour, 1 teaspoon salt and 2 tablespoons olive oil."),
		new RecipeInstruction(5, "Beat until smooth."),
		new RecipeInstruction(6, "Let rest for 5 minutes."),
		new RecipeInstruction(7, "Turn out dough onto a floured surface and form into a round."),
		new RecipeInstruction(8, "Add toppings and bake on a pizza pan or stone for 15 to 20 minutes. You're all done!")
		);


	public static final String INFO_MESSAGE = "I can give you my special pizza dough recipe. You can say 'start' to start the recipe. At any time feel free to say next, previous or repeat.";
	
	public static final String HELP_MESSAGE = "I can give you my special pizza dough recipe. You can say 'start' to start the recipe. At any time feel free to say next, previous or repeat.";

}


