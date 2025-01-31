package com.knowit.gymintellect.gym_intellect.dto;

public class JwtResponse {
	   private String token;
	    private String type = "Bearer";
	    private String username;
	    private String email;
	    private String role;
	    private String status;
	    private Long userId;

	    public JwtResponse(String token, String username, String email, String role, String status) {
	        this.token = token;
	        this.username = username;
	        this.email = email;
	        this.role = role;
	        this.setStatus(status);
	    }
	    
	    public JwtResponse(String token, String username, String email, String role, String status, Long userId) {
	        this.token = token;
	        this.username = username;
	        this.email = email;
	        this.role = role;
	        this.status = status;
	        this.userId = userId;  // Set userId
	    }

	    
	    public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public JwtResponse(String token, String username, String email, String role, Long userId) {
	        this.token = token;
	        this.username = username;
	        this.email = email;
	        this.role = role;
	        this.userId = userId;
	    }

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}
	    
	    
}
