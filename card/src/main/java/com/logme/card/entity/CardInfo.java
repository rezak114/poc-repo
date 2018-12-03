package com.logme.card.entity;

import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;

public class CardInfo {

	private Long deckId;

	private SuitEnum suitEnum;

	private FaceEnum faceEnum;

	public CardInfo(Long deckId, SuitEnum suitEnum, FaceEnum faceEnum) {
		super();
		this.deckId = deckId;
		this.suitEnum = suitEnum;
		this.faceEnum = faceEnum;
	}

	public Long getDeckId() {
		return deckId;
	}

	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}

	public SuitEnum getSuitEnum() {
		return suitEnum;
	}

	public void setSuitEnum(SuitEnum suitEnum) {
		this.suitEnum = suitEnum;
	}

	public FaceEnum getFaceEnum() {
		return faceEnum;
	}

	public void setFaceEnum(FaceEnum faceEnum) {
		this.faceEnum = faceEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deckId == null) ? 0 : deckId.hashCode());
		result = prime * result + ((faceEnum == null) ? 0 : faceEnum.hashCode());
		result = prime * result + ((suitEnum == null) ? 0 : suitEnum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardInfo other = (CardInfo) obj;
		if (deckId == null) {
			if (other.deckId != null)
				return false;
		} else if (!deckId.equals(other.deckId))
			return false;
		if (faceEnum != other.faceEnum)
			return false;
		if (suitEnum != other.suitEnum)
			return false;
		return true;
	}

}
