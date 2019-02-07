package elevator_modeling;

public enum Button {
	FLOOR_1_UP(1),
	FLOOR_2_UP(2),
	FLOOR_2_DOWN(3),
	FLOOR_3_UP(4),
	FLOOR_3_DOWN(5),
	FLOOR_4_DOWN(6),

	LEFT_ELV_1(7),
	LEFT_ELV_2(8),
	LEFT_ELV_3(9),
	LEFT_ELV_4(10),
	LEFT_ELV_OPEN(11),
	LEFT_ELV_CLOSE(12),

	RIGHT_ELV_1(13),
	RIGHT_ELV_2(14),
	RIGHT_ELV_3(15),
	RIGHT_ELV_4(16),
	RIGHT_ELV_OPEN(17),
	RIGHT_ELV_CLOSE(18);
	
	private int value;
	
	Button(int val) {
		value = val;
	}
	
	public int val() {
		return value;
	}
}
