package elevator_modeling;

import java.util.Set;

public interface ElevatorManager {

	/**
	 * 
	 * @return The current floor of the left elevator
	 */
	public abstract int getLeftElevatorPos();
	
	/**
	 * 
	 * @return The current floor of the right elevator
	 */
	public abstract int getRightElevatorPos();
	
	/**
	 * 
	 * @return an array containing all of the buttons that are lit up
	 */
	public abstract Set<Button> getLitButtons();
	
	/**
	 * @return whether door is open or closed
	 */
	public abstract boolean isLeftElevatorDoorOpen();
	
	/**
	 * 
	 * @return whether door is open or closed
	 */
	public abstract boolean isRightElevatorDoorOpen();
	
	/**
	 * Presses the button. <br><br>
	 * @param button - the button that is being pressed.
	 */
	public abstract void pressButton(Button button);
	
	/**
	 * Increments the simulation time counter by 1. <br>
	 * Performs actions related to the simulation, such as moving the elevator, <br>
	 * stopping the elevator, opening/closing doors, and turning button <br>
	 * lights on or off. 
	 */
	public abstract void incrementTime();
}
