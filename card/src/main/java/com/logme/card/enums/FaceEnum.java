package com.logme.card.enums;

public enum FaceEnum {

	ACE("ace", 1),

	TWO("two", 2),

	THREE("three", 3),

	FOUR("four", 4),

	FIVE("five", 5),

	SIX("six", 6),

	SEVEN("seven", 7),

	HEIGHT("height", 8),

	NINE("nine", 9),

	TEN("ten", 10),

	JACK("jack", 11),

	QUEEN("queen", 12),

	KING("king", 13);

	private String label;

	private int point;

	private FaceEnum(String label, int point) {
		this.label = label;
		this.point = point;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return this.point;
	}

}
