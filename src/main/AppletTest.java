package main;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import elevator_modeling.ElevatorManagerImpl3;
import elevator_modeling.ElevatorModelGui;

public class AppletTest extends JApplet {

	@Override
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ElevatorModelGui window = new ElevatorModelGui(new ElevatorManagerImpl3());
					
					setContentPane(window.getContentPane());
					setSize(new Dimension(800, 600));
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							window.startSimulation();
						}
						
					});
					t.start();
					
				}
				
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
