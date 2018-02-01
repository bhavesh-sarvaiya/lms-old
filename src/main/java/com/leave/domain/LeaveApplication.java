package com.leave.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.leave.domain.enumeration.Status;

/**
 * A LeaveApplication.
 */
@Entity
@Table(name = "leave_application")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LeaveApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reason", nullable = false)
    private String reason;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

   /* @Column(name="no_of_days",nullable= false)
    private double noOfDays;
   */ 
    @ManyToOne
    private LeaveType leaveType;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="employee_applied_id")
    private Employee appliedBy;
    
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="employee_id")
    private Employee assignedTo;
    
    @OneToMany(cascade=CascadeType.ALL)
    private List<LeaveApplicationHistory> leaveApplicationHistories;

    public Employee getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(Employee appliedBy) {
		this.appliedBy = appliedBy;
	}

	public Employee getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}

	public List<LeaveApplicationHistory> getLeaveApplicationHistories() {
		return leaveApplicationHistories;
	}

	public void setLeaveApplicationHistories(List<LeaveApplicationHistory> leaveApplicationHistories) {
		this.leaveApplicationHistories = leaveApplicationHistories;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public LeaveApplication reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public LeaveApplication status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LeaveApplication dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LeaveApplication endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public LeaveApplication leaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
        return this;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    
   /* public double getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(double noOfDays) {
		this.noOfDays = noOfDays;
	}*/

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeaveApplication leaveApplication = (LeaveApplication) o;
        if (leaveApplication.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveApplication.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveApplication{" +
            "id=" + getId() +
            ", reason='" + getReason() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateFrom='" + getDateFrom() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
