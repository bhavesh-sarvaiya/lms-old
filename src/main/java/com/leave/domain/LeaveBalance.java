package com.leave.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A LeaveBalance.
 */
@Entity
@Table(name = "leave_balance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LeaveBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "no_of_leave", nullable = false)
    private double noOfLeave;
    
    public double getNoOfLeave() {
		return noOfLeave;
	}

	public void setNoOfLeave(double noOfLeave) {
		this.noOfLeave = noOfLeave;
	}

	@ManyToOne(optional=false)
    @JoinColumn(name="leave_type_id")
    private LeaveType leaveType;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public LeaveBalance noOfLeave(Integer noOfLeave) {
        this.noOfLeave = noOfLeave;
        return this;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeaveBalance leaveBalance = (LeaveBalance) o;
        if (leaveBalance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveBalance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveBalance{" +
            "id=" + getId() +
            ", noOfLeave='" + getNoOfLeave() + "'" +
            "}";
    }
}
