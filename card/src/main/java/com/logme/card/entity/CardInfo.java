package com.logme.card.entity;

import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;

public class CardInfo {

	private SuitEnum suitEnum;

	private FaceEnum faceEnum;

	public CardInfo(final SuitEnum suitEnum, final FaceEnum faceEnum) {
		super();
		this.suitEnum = suitEnum;
		this.faceEnum = faceEnum;
	}

	public SuitEnum getSuitEnum() {
		return suitEnum;
	}

	public void setSuitEnum(final SuitEnum suitEnum) {
		this.suitEnum = suitEnum;
	}

	public FaceEnum getFaceEnum() {
		return faceEnum;
	}

	public void setFaceEnum(final FaceEnum faceEnum) {
		this.faceEnum = faceEnum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (faceEnum == null ? 0 : faceEnum.hashCode());
		result = prime * result + (suitEnum == null ? 0 : suitEnum.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CardInfo other = (CardInfo) obj;
		if (faceEnum != other.faceEnum)
			return false;
		if (suitEnum != other.suitEnum)
			return false;
		return true;
	}

}
