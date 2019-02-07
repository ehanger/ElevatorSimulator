package main;

import elevator_modeling.ElevatorManagerImpl3;
import elevator_modeling.ElevatorModelGui;

public class ElevatorModelDriver {

	public static void main(String[] args) {
		ElevatorModelGui window = new ElevatorModelGui(new ElevatorManagerImpl3());
		window.makeVisible();
		window.startSimulation();
	}
}
