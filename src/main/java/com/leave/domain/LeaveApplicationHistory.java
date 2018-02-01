package com.leave.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.leave.domain.enumeration.Status;

/**
 * A LeaveApplicationHistory.
 */
@Entity
@Table(name = "leave_application_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LeaveApplicationHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "jhi_comment", nullable = false)
    private String comment;

    @NotNull
    @Column(name = "action_date", nullable = false)
    private LocalDate actionDate;
    
    
//    @ManyToOne(optional=false)
//    @JoinColumn(name="leave_application_id")
//    private LeaveApplication leaveApplication;
//
//    public LeaveApplication getLeaveApplication() {
//		return leaveApplication;
//	}
//
//	public void setLeaveApplication(LeaveApplication leaveApplication) {
//		this.leaveApplication = leaveApplication;
//	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public LeaveApplicationHistory status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public LeaveApplicationHistory comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getActionDate() {
        return actionDate;
    }

    public LeaveApplicationHistory actionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
        return this;
    }

    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeaveApplicationHistory leaveApplicationHistory = (LeaveApplicationHistory) o;
        if (leaveApplicationHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveApplicationHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveApplicationHistory{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", comment='" + getComment() + "'" +
            ", actionDate='" + getActionDate() + "'" +
            "}";
    }
}
