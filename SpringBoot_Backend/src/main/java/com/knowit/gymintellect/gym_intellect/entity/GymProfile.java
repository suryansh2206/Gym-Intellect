package com.knowit.gymintellect.gym_intellect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "gym_profile")
public class GymProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_profile_id")
    private Long gymProfileId;

    @NotEmpty(message = "Gym name must not be empty")
    @Size(max = 255, message = "Gym name must not exceed 255 characters")
    @Column(name = "gym_name", nullable = false)
    private String gymName;

    @NotEmpty(message = "Location must not be empty")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    @Column(name = "location", nullable = false)
    private String location;

    @NotEmpty(message = "Contact must not be empty")
    @Size(max = 15, message = "Contact number must not exceed 15 characters")
    @Column(name = "contact", nullable = false)
    private String contact;

    @Size(max = 50, message = "Open hours must not exceed 50 characters")
    @Column(name = "open_hours")
    private String openHours; // Optional field

    @NotEmpty(message = "GST number must not be empty")
    @Size(max = 255, message = "GST number must not exceed 255 characters")
    @Column(name = "gst", nullable = false)
    private String gst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = true)
    private User owner; // Assuming you have a User entity

    @NotEmpty(message = "Status must not be empty")
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