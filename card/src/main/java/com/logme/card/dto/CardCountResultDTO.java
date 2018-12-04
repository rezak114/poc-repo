package com.logme.card.dto;

import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;

public class CardCountResultDTO {

	private SuitEnum suitEnum;

	private FaceEnum faceEnum;

	private int nb;

	public CardCountResultDTO() {
		super();
	}

	public CardCountResultDTO(final SuitEnum suitEnum, final FaceEnum faceEnum) {
		super();
		this.suitEnum = suitEnum;
		this.faceEnum = faceEnum;
	}

	/**
	 * @return the suitEnum
	 */
	public SuitEnum getSuitEnum() {
		return suitEnum;
	}

	/**
	 * @param suitEnum the suitEnum to set
	 */
	public void setSuitEnum(final SuitEnum suitEnum) {
		this.suitEnum = suitEnum;
	}

	/**
	 * @return the faceEnum
	 */
	public FaceEnum getFaceEnum() {
		return faceEnum;
	}

	/**
	 * @param faceEnum the faceEnum to set
	 */
	public void setFaceEnum(final FaceEnum faceEnum) {
		this.faceEnum = faceEnum;
	}

	/**
	 * @return the nb
	 */
	public int getNb() {
		return nb;
	}

	/**
	 * @param nb the nb to set
	 */
	public void setNb(final int nb) {
		this.nb = nb;
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
		final CardCountResultDTO other = (CardCountResultDTO) obj;
		if (faceEnum != other.faceEnum)
			return false;
		if (suitEnum != other.suitEnum)
			return false;
		return true;
	}

}
