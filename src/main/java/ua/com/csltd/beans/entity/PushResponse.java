package ua.com.csltd.beans.entity;

/**
 * Created by valeriy_solyanik
 * on 10.08.2016.
 */

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * User: makiyov
 * Date: 10.04.12
 * Time: 14:57
 */
public class PushResponse {
	private BigDecimal id;
	private String guid;
	private BigDecimal parentId;
	private BigDecimal code;
	private String comment;
	private String changeState;
	private BigDecimal queueId;
	private BigDecimal stateId;
	private String absDocumentId;
	private String errorMessage;
	private BigDecimal accToboId;

	public PushResponse() {
	}

	public PushResponse(BigDecimal code) {
		this.code = code;
	}

	public PushResponse(BigDecimal code, String comment, String changeState) {
		this.code = code;
		this.comment = comment;
		this.changeState = changeState;
	}

	public PushResponse(String comment, String changeState, BigDecimal id) {
		this.comment = comment;
		this.changeState = changeState;
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	// for mapping from collection
	public void setGUID(String guid) {
		this.guid = guid;
	}

	public BigDecimal getCode() {
		return code;
	}

	public void setCode(BigDecimal code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setRejectComment(String comment) {
		this.comment = comment;
	}

	public String getChangeState() {
		return changeState;
	}

	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public void setDocId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getParentId() {
		return parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	// for mapping from collection
	public void setDocParentId(BigDecimal docParentId) {
		this.parentId = parentId;
	}

	public void setDocparentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getQueueId() {
		return queueId;
	}

	public void setQueueId(BigDecimal queueId) {
		this.queueId = queueId;
	}

	public BigDecimal getStateId() {
		return stateId;
	}

	public void setStateId(BigDecimal stateId) {
		this.stateId = stateId;
	}

	public String getAbsDocumentId() {
		return absDocumentId;
	}

	public void setAbsDocumentId(String absDocumentId) {
		this.absDocumentId = absDocumentId;
	}

	public String getErrorMessage() {
		return StringUtils.hasText(errorMessage) ? errorMessage : comment;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	// for mapping from collection
	public void setProcess_errormsg(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorMessage(Throwable t) {
		this.errorMessage = t.getMessage();
	}

	public void setErrorMessageToLog(Throwable t) {
		this.errorMessage = t.getMessage();
	}


	public String getOnlyErrorMessage() {
		return errorMessage;
	}

	public BigDecimal getAccToboId() {
		return accToboId;
	}

	public void setAccToboId(BigDecimal accToboId) {
		this.accToboId = accToboId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PushResponse{");
		sb.append("id=").append(id);
		sb.append(", guid='").append(guid).append('\'');
		sb.append(", parentId=").append(parentId);
		sb.append(", code=").append(code);
		sb.append(", comment='").append(comment).append('\'');
		sb.append(", changeState='").append(changeState).append('\'');
		sb.append(", queueId=").append(queueId);
		sb.append(", stateId=").append(stateId);
		sb.append(", absDocumentId='").append(absDocumentId).append('\'');
		sb.append(", errorMessage='").append(errorMessage).append('\'');
		sb.append(", accToboId=").append(accToboId);
		sb.append('}');
		return sb.toString();
	}
}
