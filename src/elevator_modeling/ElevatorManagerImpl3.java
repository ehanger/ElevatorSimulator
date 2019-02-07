package elevator_modeling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorManagerImpl3 implements ElevatorManager {

	Set<Button> litFloorButtons = new HashSet<Button>();
	Elevator leftElv = new Elevator("left", 1, true), rightElv = new Elevator("right", 1, true);
	
	@Override
	public int getLeftElevatorPos() {
		return leftElv.currentFloor;
	}

	@Override
	public int getRightElevatorPos() {
		return rightElv.currentFloor;
	}

	@Override
	public Set<Button> getLitButtons() {
		Set<Button> allLit = new HashSet<Button>();
		allLit.addAll(litFloorButtons);
		allLit.addAll(leftElv.litButtons);
		allLit.addAll(rightElv.litButtons);
		return allLit;
	}

	@Override
	public boolean isLeftElevatorDoorOpen() {
		return leftElv.isDoorOpen;
	}

	@Override
	public boolean isRightElevatorDoorOpen() {
		return rightElv.isDoorOpen;
	}

	@Override
	public void pressButton(Button button) {
		if(button.val() < Button.LEFT_ELV_1.val()) {
			litFloorButtons.add(button);
		} else if(button.val() < Button.RIGHT_ELV_1.val()) {
			if(button == Button.LEFT_ELV_1) {
				leftElv.addUnique(1);
				leftElv.litButtons.add(button);
			} else if(button == Button.LEFT_ELV_2) {
				leftElv.addUnique(2);
				leftElv.litButtons.add(button);
			} else if(button == Button.LEFT_ELV_3) {
				leftElv.addUnique(3);
				leftElv.litButtons.add(button);
			} else if(button == Button.LEFT_ELV_4) {
				leftElv.addUnique(4);
				leftElv.litButtons.add(button);
			} else if(button == Button.LEFT_ELV_OPEN) {
				leftElv.addUnique(leftElv.currentFloor);
				leftElv.litButtons.add(button);
			} else if(button == Button.LEFT_ELV_CLOSE) {
				leftElv.isDoorOpen = false;
				leftElv.litButtons.remove(Button.LEFT_ELV_OPEN);
			}
		} else {
			if(button == Button.RIGHT_ELV_1) {
				rightElv.litButtons.add(button);
				rightElv.addUnique(1);
			} else if(button == Button.RIGHT_ELV_2) {
				rightElv.litButtons.add(button);
				rightElv.addUnique(2);
			} else if(button == Button.RIGHT_ELV_3) {
				rightElv.litButtons.add(button);
				rightElv.addUnique(3);
			} else if(button == Button.RIGHT_ELV_4) {
				rightElv.litButtons.add(button);
				rightElv.addUnique(4);
			} else if(button == Button.RIGHT_ELV_OPEN) {
				rightElv.addUnique(rightElv.currentFloor);
				rightElv.litButtons.add(button);
			} else if(button == Button.RIGHT_ELV_OPEN) {
				rightElv.isDoorOpen = false;
				rightElv.litButtons.remove(Button.RIGHT_ELV_OPEN);
			}
		}
	}
	
	@Override
	public void incrementTime() {
		processFloorButtons();
		leftElv.performNextAction();
		rightElv.performNextAction();
	}
	
	private int travelDistance(Elevator elv, int targetFloor) {
		if (elv.currentFloor == targetFloor) {
			return 0;
			
		} else {
			if (elv.isRising) {
				if (targetFloor > elv.currentFloor) {
					return targetFloor - elv.currentFloor;
				} else {
					return ((4 - elv.currentFloor) + (4 - targetFloor)); 
				}
			} else {
				if (targetFloor < elv.currentFloor) {
					return (elv.currentFloor - targetFloor);
				} else {
					return ((elv.currentFloor - 1) + (targetFloor - 1));
				}
			}
		}
	}
	
	public void addFloor(int i) {
		int distanceLeft = travelDistance(leftElv, i);
		int distanceRight = travelDistance(rightElv, i);
		
		if (distanceLeft < distanceRight) {
			leftElv.addUnique(i);
		} else if (distanceRight < distanceLeft){
			rightElv.addUnique(i);
		} else {
			leftElv.addUnique(i);
		}
	}
	
	private void processFloorButtons() {
		//TODO
		for (Button button: litFloorButtons) {
			if (button == Button.FLOOR_1_UP) {
				addFloor(1);
			} else if (button == Button.FLOOR_2_DOWN || button == Button.FLOOR_2_UP) {
				addFloor(2);
			} else if (button == Button.FLOOR_3_DOWN || button == Button.FLOOR_3_UP) {
				addFloor(3);
			} else if (button == Button.FLOOR_4_DOWN) {
				addFloor(4);
			}
			
		}
	}

	class Elevator {
		boolean isRising = true, isDoorOpen = false;
		int currentFloor = 1;
		Queue<Integer> floorRequests = new ConcurrentLinkedQueue<Integer>();
		Set<Button> litButtons = new HashSet<Button>();
		String type;
		
		Elevator(String typ, int startingFloor, boolean rising) {
			currentFloor = startingFloor;
			isRising = rising;
			type = typ;
		}
		
		void addUnique(Integer floor) {
			if(!floorRequests.contains(floor)) {
				floorRequests.add(floor);
			}
		}
		
		void performNextAction() {
			
			if(floorRequests.contains(currentFloor)) {
				if(litButtons.contains(getCurrentInternalElvButton(currentFloor)) || litButtons.contains(Button.LEFT_ELV_OPEN) || litButtons.contains(Button.RIGHT_ELV_OPEN)) {
					litButtons.remove(getCurrentInternalElvButton(currentFloor));
					litButtons.remove(Button.LEFT_ELV_OPEN);
					litButtons.remove(Button.RIGHT_ELV_OPEN);
					isDoorOpen = true;
				} else if(!getCurrentLitFloorButtons().isEmpty()) {
					ArrayList<Button> lit = getCurrentLitFloorButtons();
					for(Button b : lit) {
						litFloorButtons.remove(b);
						
					}
					isDoorOpen = true;
				} else {
					isDoorOpen = false;
					floorRequests.remove(new Integer(currentFloor));
				}
			} else if(!floorRequests.isEmpty()) {
				if(currentFloor == 1) {
					isRising = true;
				} else if(currentFloor == 4) {
					isRising = false;
				} else if(floorRequests.size() == 1) {
					isRising = floorRequests.peek() > currentFloor;
				}
				
				if(isRising) {
					currentFloor++;
				} else {
					currentFloor--;
				}
			}

		}
		 public boolean isStationary() {
		    	if (null == floorRequests.peek()) {
		    		return true;
		    	} else {
		    	return false;
		    	}
		 }
		    
		    public boolean isRising() {
		    	if (!isStationary() && currentFloor< floorRequests.peek()) {
		    		return true;
		    	}
		    	return false;
		    }
		    
		    public boolean isFalling() {
		    	if (!isStationary() && currentFloor > floorRequests.peek()) {
		    		return true;
		    	}
		    	return false;
		    }
		
		    
		    
		Button getCurrentInternalElvButton(int floor) {
			if(type.equals("right")) {
				switch(floor) {
					case 1: return Button.RIGHT_ELV_1;
					case 2: return Button.RIGHT_ELV_2;
					case 3: return Button.RIGHT_ELV_3;
					case 4: return Button.RIGHT_ELV_4;
				}
			} else if(type.equals("left")) {
				switch(floor) {
					case 1: return Button.LEFT_ELV_1;
					case 2: return Button.LEFT_ELV_2;
					case 3: return Button.LEFT_ELV_3;
					case 4: return Button.LEFT_ELV_4;
				}
			}
			
			return null;
		}
		
		ArrayList<Button> getCurrentLitFloorButtons() {
			ArrayList<Button> buttons = new ArrayList<Button>();
			switch(currentFloor) {
			case 1: 
				if(litFloorButtons.contains(Button.FLOOR_1_UP)) { buttons.add(Button.FLOOR_1_UP); } break;
			case 2: 
				if(litFloorButtons.contains(Button.FLOOR_2_UP)) { buttons.add(Button.FLOOR_2_UP); } 
				if(litFloorButtons.contains(Button.FLOOR_2_DOWN)) { buttons.add(Button.FLOOR_2_DOWN); } break;
			case 3: 
				if(litFloorButtons.contains(Button.FLOOR_3_UP)) { buttons.add(Button.FLOOR_3_UP); } 
				if(litFloorButtons.contains(Button.FLOOR_3_DOWN)) { buttons.add(Button.FLOOR_3_DOWN); } break;
			case 4: 
				if(litFloorButtons.contains(Button.FLOOR_4_DOWN)) { buttons.add(Button.FLOOR_4_DOWN); } break;
			}
			return buttons;
		}
	}
} 