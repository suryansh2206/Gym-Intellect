package com.KnowIt.Gym_intellect_Crud.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "gym_profile")
public class GymProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "gym_profile_id")
    private Long gymProfileId;

    @Column(name = "gym_name", nullable = false)
    private String gymName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "contact", nullable = false)
    private String contact;

    private String openHours; // Optional field

    @Column(name = "gst", nullable = false)
    private String gst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = true)
    private User owner; // Assuming you have a User entity

    @Column(name = "status", nullable = false)
    private String status;

    // Getters and Setters
    public Long getGymProfileId() {
        return gymProfileId;
    }

    @Override
	public String toString() {
		return "GymProfile [gymProfileId=" + gymProfileId + ", gymName=" + gymName + ", location=" + location
				+ ", contact=" + contact + ", openHours=" + openHours + ", gst=" + gst + ", owner=" + owner
				+ ", status=" + status + "]";
	}

	public void setGymProfileId(Long gymProfileId) {
        this.gymProfileId = gymProfileId;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Enum for GymStatus (Optional)
    public enum GymStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
