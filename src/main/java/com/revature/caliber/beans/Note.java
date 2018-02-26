package com.revature.caliber.beans;

import java.io.Serializable;

public class Note implements Serializable{

	private static final long serialVersionUID = -4960654794116385953L;

	private int noteId;

	private String content;
	private short week;

	/**
	 * Will be null if the note is individual trainee feedback
	 */
	private Batch batch;

	/**
	 * Will be null if the note is overall batch feedback
	 */
	private Trainee trainee;

	private TrainerRole maxVisibility;

	private NoteType type;
	private boolean qcFeedback;
	private QCStatus qcStatus;

	public Note() {
		super();
		this.maxVisibility = TrainerRole.ROLE_TRAINER;
	}
	
	/**
	 * QC Status for the batch. Constructs the note and it's visibility If the
	 * feedback is public, anyone can view. If not, the feedback can only be
	 * viewed by QC and the VP.
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @param maxVisibility
	 * @param type
	 * @param qcFeedback
	 * @param qcStatus
	 */
	private Note(String content, short week, Batch batch, NoteType type, QCStatus qcStatus) {
		this();
		this.content = content;
		this.week = week;
		this.batch = batch;
		if (type == NoteType.QC_BATCH)
			this.maxVisibility = TrainerRole.ROLE_QC;
		else
			throw new IllegalArgumentException("Select proper NoteType");
		this.type = type;
		this.qcFeedback = true;
		this.qcStatus = qcStatus;
	}

	/**
	 * QC Status for each trainee. Constructs the note and it's visibility If
	 * the feedback is public, anyone can view. If not, the feedback can only be
	 * viewed by QC and the VP.
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @param maxVisibility
	 * @param type
	 * @param qcFeedback
	 * @param qcStatus
	 */
	private Note(String content, short week, Trainee trainee, NoteType type, QCStatus qcStatus) {
		this();
		this.content = content;
		this.week = week;
		this.trainee = trainee;
		if (type == NoteType.QC_TRAINEE)
			this.maxVisibility = TrainerRole.ROLE_QC;
		else
			throw new IllegalArgumentException("Select proper NoteType");
		this.type = type;
		this.qcFeedback = true;
		this.qcStatus = qcStatus;
	}

	/**
	 * Trainer feedback for a trainee
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @param maxVisibility
	 * @param type
	 */
	private Note(String content, short week, Trainee trainee) {
		this();
		this.content = content;
		this.week = week;
		this.trainee = trainee;
		this.maxVisibility = TrainerRole.ROLE_TRAINER;
		this.type = NoteType.TRAINEE;
		this.qcFeedback = false;
	}

	/**
	 * Trainer feedback for a batch
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @param maxVisibility
	 * @param type
	 */
	private Note(String content, short week, Batch batch) {
		this();
		this.content = content;
		this.week = week;
		this.batch = batch;
		this.maxVisibility = TrainerRole.ROLE_TRAINER;
		this.type = NoteType.BATCH;
		this.qcFeedback = false;
	}
	
	/**
	 * Factory method to construct new QC weekly batch note
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @param qcStatus
	 * @param isPublic
	 * @return
	 */
	public static Note qcBatchNote(String content, Integer week, Batch batch, QCStatus qcStatus) {
		return new Note(content, week.shortValue(), batch, NoteType.QC_BATCH, qcStatus);
	}

	/**
	 * Factory method for creating new QC weekly individual trainee note
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @param qcStatus
	 * @param isPublic
	 * @return
	 */
	public static Note qcIndividualNote(String content, Integer week, Trainee trainee, QCStatus qcStatus) {
		return new Note(content, week.shortValue(), trainee, NoteType.QC_TRAINEE, qcStatus);
	}

	/**
	 * Factory method for creating a new Trainer weekly batch note
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @return
	 */
	public static Note trainerBatchNote(String content, Integer week, Batch batch) {
		return new Note(content, week.shortValue(), batch);
	}

	/**
	 * Factory method for creating a new Trainer weekly individual trainee note
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @return
	 */
	public static Note trainerIndividualNote(String content, Integer week, Trainee trainee) {
		return new Note(content, week.shortValue(), trainee);
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getWeek() {
		return week;
	}

	public void setWeek(short week) {
		this.week = week;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public TrainerRole getMaxVisibility() {
		return maxVisibility;
	}

	public void setMaxVisibility(TrainerRole maxVisibility) {
		this.maxVisibility = maxVisibility;
	}

	public NoteType getType() {
		return type;
	}

	public void setType(NoteType type) {
		this.type = type;
	}

	public boolean isQcFeedback() {
		return qcFeedback;
	}

	public void setQcFeedback(boolean qcFeedback) {
		this.qcFeedback = qcFeedback;
	}

	public QCStatus getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(QCStatus qcStatus) {
		this.qcStatus = qcStatus;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((maxVisibility == null) ? 0 : maxVisibility.hashCode());
		result = prime * result + (qcFeedback ? 1231 : 1237);
		result = prime * result + ((qcStatus == null) ? 0 : qcStatus.hashCode());
		result = prime * result + ((trainee == null) ? 0 : trainee.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + week;
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
		Note other = (Note) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (maxVisibility != other.maxVisibility)
			return false;
		if (qcFeedback != other.qcFeedback)
			return false;
		if (qcStatus != other.qcStatus)
			return false;
		if (trainee == null) {
			if (other.trainee != null)
				return false;
		} else if (!trainee.equals(other.trainee))
			return false;
		if (type != other.type)
			return false;
		if (week != other.week)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", content=" + content + ", week=" + week
				+ ", trainee=" + trainee + ", maxVisibility=" + maxVisibility + ", type=" + type
				+ ", qcFeedback=" + qcFeedback + ", qcStatus=" + qcStatus + "]";
	}

}
