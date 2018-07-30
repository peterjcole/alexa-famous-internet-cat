package net.petercole.alexapusheen.model;

public class RecipeInstruction {

	private int stepNo;
	private String instructionText;
	
	public RecipeInstruction() {}

	public RecipeInstruction(int stepNo, String instructionText) {
		super();
		this.stepNo = stepNo;
		this.instructionText = instructionText;
	}

	public int getStepNo() {
		return stepNo;
	}

	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}

	public String getInstructionText() {
		return instructionText;
	}

	public void setInstructionText(String instructionText) {
		this.instructionText = instructionText;
	}

	@Override
	public String toString() {
		return "Step number " + stepNo + ", " + instructionText;
	}

	
}
