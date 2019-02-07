package elevator_modeling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ElevatorModelGui extends JFrame {

	private final Dimension WINDOW_SIZE = new Dimension(825, 600);
	private final String WINDOW_TITLE = "AV Williams Elevator Simulator";
	private final Dimension DRAW_SURFACE_SIZE = new Dimension(650, 425);
	private final int ROOF_HEIGHT = 50;
	private final Dimension ELEVATOR_SIZE = new Dimension(50, 75);
	private final int NUM_FLOORS = 4;
	
	private JPanel drawSurface, floorButtonPanel, elevatorButtonPanel, container0, 
		container1, container2, container3, container4;
	
	// buttons for floors
	private LightableButton floor1Up, floor2Up, floor2Down, floor3Up, floor3Down, floor4Down;
	
	// buttons for elevators
	private LightableButton leftElv1, leftElv2, leftElv3, leftElv4, leftElvOpen, leftElvClose,
					rightElv1, rightElv2, rightElv3, rightElv4, rightElvOpen, rightElvClose;
	HashMap<Button, LightableButton> buttonMap;
	
	private ElevatorManager elevatorManager;
	
	public ElevatorModelGui(ElevatorManager em) {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_SIZE);
		this.setTitle(WINDOW_TITLE);
		this.getContentPane().setLayout(new BorderLayout()); 
		this.setResizable(false);
		centerOnScreen();
		
		this.elevatorManager = em;
		
		initializeGuiComponents();
	}
	
	private void centerOnScreen() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(dimension.getWidth()/2 - getSize().width/2), (int)(dimension.getHeight()/2 - getSize().height/2));
	}
	
	private void initializeJButtons() {
		buttonMap = new HashMap<Button, LightableButton>();
		
		leftElv1 = new LightableButton("1", Button.LEFT_ELV_1);
		leftElv2 = new LightableButton("2", Button.LEFT_ELV_2);
		leftElv3 = new LightableButton("3", Button.LEFT_ELV_3);
		leftElv4 = new LightableButton("4", Button.LEFT_ELV_4);
		leftElvOpen = new LightableButton("open", Button.LEFT_ELV_OPEN);
		leftElvClose = new LightableButton("close", Button.LEFT_ELV_CLOSE);
		
		rightElv1 = new LightableButton("1", Button.RIGHT_ELV_1);
		rightElv2 = new LightableButton("2", Button.RIGHT_ELV_2);
		rightElv3 = new LightableButton("3", Button.RIGHT_ELV_3);
		rightElv4 = new LightableButton("4", Button.RIGHT_ELV_4);
		rightElvOpen = new LightableButton("open", Button.RIGHT_ELV_OPEN);
		rightElvClose = new LightableButton("close", Button.RIGHT_ELV_CLOSE);
		
		floor1Up = new LightableButton("up", Button.FLOOR_1_UP);
		floor2Up = new LightableButton("up", Button.FLOOR_2_UP);
		floor2Down = new LightableButton("down", Button.FLOOR_2_DOWN);
		floor3Up = new LightableButton("up", Button.FLOOR_3_UP);
		floor3Down = new LightableButton("down", Button.FLOOR_3_DOWN);
		floor4Down = new LightableButton("down", Button.FLOOR_4_DOWN);
		
		buttonMap.put(leftElv1.type, leftElv1);
		buttonMap.put(leftElv2.type, leftElv2);
		buttonMap.put(leftElv3.type, leftElv3);
		buttonMap.put(leftElv4.type, leftElv4);
		buttonMap.put(leftElvOpen.type, leftElvOpen);
		buttonMap.put(leftElvClose.type, leftElvClose);
		buttonMap.put(rightElv1.type, rightElv1);
		buttonMap.put(rightElv2.type, rightElv2);
		buttonMap.put(rightElv3.type, rightElv3);
		buttonMap.put(rightElv4.type, rightElv4);
		buttonMap.put(rightElvOpen.type, rightElvOpen);
		buttonMap.put(rightElvClose.type, rightElvClose);
		buttonMap.put(floor1Up.type, floor1Up);
		buttonMap.put(floor2Up.type, floor2Up);
		buttonMap.put(floor2Down.type, floor2Down);
		buttonMap.put(floor3Up.type, floor3Up);
		buttonMap.put(floor3Down.type, floor3Down);
		buttonMap.put(floor4Down.type, floor4Down);
	}
	
	private void initializeGuiComponents() {
		initializeJButtons();
		container0 = new JPanel();
		drawSurface = new DrawSurface();
		floorButtonPanel = new JPanel();
		elevatorButtonPanel = new JPanel();
		container1 = new JPanel();
		container2 = new JPanel();
		container3 = new JPanel();
		container4 = new JPanel();
		
		this.getContentPane().add(container0, BorderLayout.WEST);
		this.getContentPane().add(floorButtonPanel, BorderLayout.EAST);
		
		container0.setPreferredSize(new Dimension(650,600));
		container0.setLayout(new BorderLayout());
		drawSurface.setPreferredSize(DRAW_SURFACE_SIZE);
		elevatorButtonPanel.setPreferredSize(new Dimension(600, 150));
		container0.add(drawSurface, BorderLayout.NORTH);
		container0.add(elevatorButtonPanel, BorderLayout.SOUTH);
		
		floorButtonPanel.setPreferredSize(new Dimension(150, 600));
		
		container1.setPreferredSize(new Dimension(300, 150));
		container2.setPreferredSize(new Dimension(300, 150));
		elevatorButtonPanel.setLayout(new BorderLayout());
		elevatorButtonPanel.add(container1, BorderLayout.WEST);
		elevatorButtonPanel.add(container2, BorderLayout.EAST);
		//elevatorButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		container1.setLayout(new GridLayout(2,3));
		container2.setLayout(new GridLayout(2,3));
		container1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		container2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		container1.add(leftElv1);
		container1.add(leftElv2);
		container1.add(leftElvOpen);
		container1.add(leftElv3);
		container1.add(leftElv4);
		container1.add(leftElvClose);
		
		container2.add(rightElv1);
		container2.add(rightElv2);
		container2.add(rightElvOpen);
		container2.add(rightElv3);
		container2.add(rightElv4);
		container2.add(rightElvClose);
		
		floorButtonPanel.setLayout(null); // use absolute positioning
		//floorButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, ));
		setButtonPos(floorButtonPanel, floor4Down, 70,  ELEVATOR_SIZE.height);
		setButtonPos(floorButtonPanel, floor3Up, 0, 2*ELEVATOR_SIZE.height);
		setButtonPos(floorButtonPanel, floor3Down, 70, 2*ELEVATOR_SIZE.height);
		setButtonPos(floorButtonPanel, floor2Up, 0, 3*ELEVATOR_SIZE.height);
		setButtonPos(floorButtonPanel, floor2Down, 70, 3*ELEVATOR_SIZE.height);
		setButtonPos(floorButtonPanel, floor1Up, 0, 4*ELEVATOR_SIZE.height);
		
		//setPanelColors();
	}
	
	/**
	 * For testing
	 */
	private void setPanelColors() {
		drawSurface.setBackground(Color.WHITE);
		//elevatorButtonPanel.setBackground(Color.RED);
		//floorButtonPanel.setBackground(Color.CYAN);
		//container0.setBackground(Color.GREEN);
		this.getContentPane().setBackground(Color.YELLOW);
	}
	
	private void setButtonPos(JPanel p, JButton b, int xPos, int yPos) {
		p.add(b);
		Insets insets = p.getInsets();
		b.setBounds(insets.left + xPos, insets.top + yPos, b.getPreferredSize().width, b.getPreferredSize().height);
	}
	
	public void makeVisible() {
		this.setVisible(true);
	}
	
	public void startSimulation() {
		
		while(true) {
			elevatorManager.incrementTime();
			updateLitButtons();
			drawSurface.repaint();
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void updateLitButtons() {
		Set<Button> lits = elevatorManager.getLitButtons();
		for(Button b : buttonMap.keySet()) {
			LightableButton lightableButton = buttonMap.get(b);
			lightableButton.setLit(lits.contains(b));
		}
	}
	
	private class LightableButton extends JButton {
		
		Button type;
		
		public LightableButton(String text, Button typ) {
			super(text);
			setOpaque(true);
			type = typ;
			
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					elevatorManager.pressButton(type);
					if(elevatorManager.getLitButtons().contains(type)) {
						setLit(true);
					}
				}
				
			});
		}
		
		public void setLit(boolean lit) {
			if(lit) {
				setBackground(Color.YELLOW);
			} else {
				setBackground(Color.GRAY);
			}
		}
	}
	
	private class DrawSurface extends JPanel {
		
		public DrawSurface() {
			super();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawBackgroundItems(g, 50, ELEVATOR_SIZE.height, ELEVATOR_SIZE.width, NUM_FLOORS);
			if(elevatorManager != null) {
				//System.out.println(elevatorManager.getLeftElevatorPos() + " " + elevatorManager.getRightElevatorPos());
				drawElevator(g, elevatorManager.getLeftElevatorPos(), 50, elevatorManager.isLeftElevatorDoorOpen(), ELEVATOR_SIZE.height, ELEVATOR_SIZE.width, "left");
				drawElevator(g, elevatorManager.getRightElevatorPos(), 50, elevatorManager.isRightElevatorDoorOpen(), ELEVATOR_SIZE.height, ELEVATOR_SIZE.width, "right");
			} 
		}
		
		public void drawBackgroundItems(Graphics g, int roofPos, int elvHeight, int elvWidth, int numFloors) {
		
			// draw the lines representing floors
			if(numFloors > 0) {
				g.drawLine(elvWidth, roofPos, DRAW_SURFACE_SIZE.width - elvWidth, roofPos); // draw the roof
				for(int i = 1; i <= numFloors; i++) {
					int floorSpacing = i*elvHeight;
					g.drawString(String.format("FLOOR %d", NUM_FLOORS - i + 1), DRAW_SURFACE_SIZE.width / 2, roofPos + floorSpacing - elvHeight/2);
					g.drawLine(elvWidth, roofPos + floorSpacing, DRAW_SURFACE_SIZE.width - elvWidth, roofPos + floorSpacing);
				}
			}
			
			// draw labels for the panels
			g.drawString("Left Elevator Controls", 80, DRAW_SURFACE_SIZE.height - 10);
			g.drawString("Right Elevator Controls", DRAW_SURFACE_SIZE.width - 220, DRAW_SURFACE_SIZE.height - 10);
		}
		
		public void drawElevator(Graphics g, int floor, int roofPos, boolean doorsOpen, int elvHeight, int elvWidth, String side) {
		
			int xPos = 0;
			if(side.equals("right")) {
				xPos = DRAW_SURFACE_SIZE.width - elvWidth;
			}
			int yPos = roofPos + (NUM_FLOORS - floor)*elvHeight;
			//System.out.println("Doors open = " + doorsOpen);
			if(doorsOpen) {
				g.fillRect(xPos, yPos, elvWidth/3, elvHeight);
				g.fillRect((int)(xPos + (2.0/3.0)*elvWidth), yPos, elvWidth/3, elvHeight);
			} else {
				g.fillRect(xPos, yPos, elvWidth, elvHeight);
			}
		}
	}
}
