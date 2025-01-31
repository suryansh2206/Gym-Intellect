package com.knowit.gymintellect.gym_intellect.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GymProfileRequest {
	@NotNull(message = "User ID must not be null")
    private Long userId;

    @NotEmpty(message = "Gym name must not be empty")
    @Size(max = 255, message = "Gym name must not exceed 255 characters")
    private String gymName;

    @NotEmpty(message = "Location must not be empty")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @NotEmpty(message = "Contact must not be empty")
    @Size(max = 15, message = "Contact number must not exceed 15 characters")
    private String contact;

    private String openHours; // Optional field

    @NotEmpty(message = "GST number must not be empty")
    @Size(max = 255, message = "GST number must not exceed 255 characters")
    private String gst;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
	public String toString() {
		return "GymProfileRequest [userId=" + userId + ", gymName=" + gymName + ", location=" + location + ", contact="
				+ contact + ", openHours=" + openHours + ", gst=" + gst + "]";
	}

	public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}
