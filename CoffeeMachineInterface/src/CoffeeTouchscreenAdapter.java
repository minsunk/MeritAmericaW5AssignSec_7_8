

public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
	 
	OldCoffeeMachine theMachine;
	
	public CoffeeTouchscreenAdapter(OldCoffeeMachine newMachine) {
		theMachine = newMachine;
	}
	
	public void chooseFirstSelection() {
		theMachine.selectA();
	}
	
	public void chooseSecondSelection() {
		theMachine.selectB();
	}

	public static void main(String[]args) {
		OldCoffeeMachine oldMachine = new OldCoffeeMachine();
		CoffeeTouchscreenAdapter adapter = new CoffeeTouchscreenAdapter(oldMachine);
		adapter.chooseFirstSelection();
		adapter.chooseSecondSelection();
	}
}
