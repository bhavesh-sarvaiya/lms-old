package com.leave.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A LeaveAllocation.
 */
@Entity
@Table(name = "leave_allocation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LeaveAllocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teaching")
    private Boolean teaching;

    @Column(name = "can_have_vacation")
    private Boolean canHaveVacation;

    @Column(name = "granted")
    private Boolean granted;

    @Column(name = "no_of_leaves")
    private Double noOfLeaves;

    @Column(name = "allocation_date")
    private LocalDate allocationDate;

    @ManyToOne
    private LeaveType leaveType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isTeaching() {
        return teaching;
    }

    public LeaveAllocation teaching(Boolean teaching) {
        this.teaching = teaching;
        return this;
    }

    public void setTeaching(Boolean teaching) {
        this.teaching = teaching;
    }

    public Boolean isCanHaveVacation() {
        return canHaveVacation;
    }

    public LeaveAllocation canHaveVacation(Boolean canHaveVacation) {
        this.canHaveVacation = canHaveVacation;
        return this;
    }

    public void setCanHaveVacation(Boolean canHaveVacation) {
        this.canHaveVacation = canHaveVacation;
    }

    public Boolean isGranted() {
        return granted;
    }

    public LeaveAllocation granted(Boolean granted) {
        this.granted = granted;
        return this;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    public Double getNoOfLeaves() {
        return noOfLeaves;
    }

    public LeaveAllocation noOfLeaves(Double noOfLeaves) {
        this.noOfLeaves = noOfLeaves;
        return this;
    }

    public void setNoOfLeaves(Double noOfLeaves) {
        this.noOfLeaves = noOfLeaves;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public LeaveAllocation allocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
        return this;
    }

    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public LeaveAllocation leaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
        return this;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeaveAllocation leaveAllocation = (LeaveAllocation) o;
        if (leaveAllocation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveAllocation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveAllocation{" +
            "id=" + getId() +
            ", teaching='" + isTeaching() + "'" +
            ", canHaveVacation='" + isCanHaveVacation() + "'" +
            ", granted='" + isGranted() + "'" +
            ", noOfLeaves='" + getNoOfLeaves() + "'" +
            ", allocationDate='" + getAllocationDate() + "'" +
            "}";
    }
}
