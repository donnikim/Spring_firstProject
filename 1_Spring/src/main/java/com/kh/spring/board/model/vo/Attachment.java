package com.kh.spring.board.model.vo;

public class Attachment {
	private int attmId;
	private int refBoardId;
	private String originalName;
	private String renameName;
	private String attmPath;
	private int attmLevel;
	private String attmStatus;
	
	public Attachment() {}

	public Attachment(int attmId, int refBoardId, String originalName, String renameName, String attmPath,
			int attmLevel, String attmStatus) {
		super();
		this.attmId = attmId;
		this.refBoardId = refBoardId;
		this.originalName = originalName;
		this.renameName = renameName;
		this.attmPath = attmPath;
		this.attmLevel = attmLevel;
		this.attmStatus = attmStatus;
	}

	public int getAttmId() {
		return attmId;
	}

	public void setAttmId(int attmId) {
		this.attmId = attmId;
	}

	public int getRefBoardId() {
		return refBoardId;
	}

	public void setRefBoardId(int refBoardId) {
		this.refBoardId = refBoardId;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getRenameName() {
		return renameName;
	}

	public void setRenameName(String renameName) {
		this.renameName = renameName;
	}

	public String getAttmPath() {
		return attmPath;
	}

	public void setAttmPath(String attmPath) {
		this.attmPath = attmPath;
	}

	public int getAttmLevel() {
		return attmLevel;
	}

	public void setAttmLevel(int attmLevel) {
		this.attmLevel = attmLevel;
	}

	public String getAttmStatus() {
		return attmStatus;
	}

	public void setAttmStatus(String attmStatus) {
		this.attmStatus = attmStatus;
	}

	@Override
	public String toString() {
		return "Attachment [attmId=" + attmId + ", refBoardId=" + refBoardId + ", originalName=" + originalName
				+ ", renameName=" + renameName + ", attmPath=" + attmPath + ", attmLevel=" + attmLevel + ", attmStatus="
				+ attmStatus + "]";
	}
	
	

}
