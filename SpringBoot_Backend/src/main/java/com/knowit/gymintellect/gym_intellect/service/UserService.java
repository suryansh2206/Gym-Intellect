package com.knowit.gymintellect.gym_intellect.service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_intellect.dto.JwtResponse;
import com.knowit.gymintellect.gym_intellect.dto.LoginRequest;
import com.knowit.gymintellect.gym_intellect.dto.MemberRegistrationDTO;
import com.knowit.gymintellect.gym_intellect.dto.SignupRequest;
import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.entity.Member;
import com.knowit.gymintellect.gym_intellect.entity.MembershipPlan;
import com.knowit.gymintellect.gym_intellect.entity.MembershipPlanJoin;
import com.knowit.gymintellect.gym_intellect.entity.Role;
import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_intellect.repository.GymProfileRepository;
import com.knowit.gymintellect.gym_intellect.repository.MemberRepository;
import com.knowit.gymintellect.gym_intellect.repository.MembershipPlanJoinRepository;
import com.knowit.gymintellect.gym_intellect.repository.MembershipPlanRepository;
import com.knowit.gymintellect.gym_intellect.repository.RoleRepository;
import com.knowit.gymintellect.gym_intellect.repository.UserRepository;
import com.knowit.gymintellect.gym_intellect.repository.WorkoutPlanRespository;
import com.knowit.gymintellect.gym_intellect.utils.JwtUtils;
import com.knowit.gymintellect.gym_intellect.validation.ValidationGroups;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class UserService {
	
	 @Autowired
	 private AuthenticationManager authenticationManager;

	 @Autowired
	 private JwtUtils jwtUtils;

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private GymProfileRepository gymProfileRepository;
    
    @Autowired
    private WorkoutPlanRespository workoutPlanRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private MembershipPlanJoinRepository membershipPlanJoinRepository;

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    public List<MembershipPlan> getMembershipPlansByGymProfile(Long gymProfileId) {
        if (gymProfileId == null) {
            throw new IllegalArgumentException("GymProfileId is required");
        }

        List<MembershipPlan> plans = membershipPlanRepository.findByGymProfile_GymProfileId(gymProfileId);
        System.out.println("Fetched plans: " + plans);  // Debug log
        return plans;
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll();
    }

    public List<GymProfile> getGymProfilesForOwner(User owner) {
        return gymProfileRepository.findByOwner(owner);
    }
    
    
 // Method to hash the password
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Autowired
    private Validator validator;

    public void registerUser(SignupRequest signupRequest) {
        // Check if the username or email already exists
        if (userRepository.existsByUsername(signupRequest.getUsername()) ||
            userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Username or Email already exists!");
        }

        // Fetch the role for the Gym Owner
        Role role = roleRepository.findByName("GYM_OWNER")
                                  .orElseThrow(() -> new RuntimeException("Role not found!"));

        // Create and save the User entity
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setContact(signupRequest.getContact());
        user.setAadhar(signupRequest.getAadhar());
        user.setRole(role); // Set the role ID

        userRepository.save(user);

        // No GymProfile is created here. It will be created after the Gym Owner logs in and fills the form.
    }

//    public void registerGymOwner(SignupRequest signupRequest) {
//        Role gymOwnerRole = roleRepository.findByName("GYM_OWNER")
//            .orElseThrow(() -> new RuntimeException("Role not found!"));
//
//        User user = new User();
//        user.setUsername(signupRequest.getUsername());
//        user.setEmail(signupRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//        user.setRole(gymOwnerRole);
//        user.setGymProfileSubmitted(false);
//        user.setGymProfileApproved(false);
//
//        userRepository.save(user);
//    }
    
    public Member registerGymMember(MemberRegistrationDTO registrationDTO, User currentOwner) {
        // Create User
        Role gymMemberRole = roleRepository.findByName("MEMBER")
            .orElseThrow(() -> new RuntimeException("Role not found!"));

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setContact(registrationDTO.getContact());
        user.setAadhar(registrationDTO.getAadhar());
        user.setRole(gymMemberRole);
        user = userRepository.save(user);

        // Create Member
        Member member = new Member();
        member.setDob(registrationDTO.getDob());
        member.setGender(registrationDTO.getGender());
        member.setAddress(registrationDTO.getAddress());
        member.setHeight(registrationDTO.getHeight());
        member.setUser(user);
        
        if (registrationDTO.getGymProfileId() == null) {
            throw new IllegalArgumentException("GymProfileId is null in registration request");
        }


        // Handle gym profile
        GymProfile gymProfile = gymProfileRepository.findById(registrationDTO.getGymProfileId())
            .orElseThrow(() -> new RuntimeException("Gym profile not found"));
        
        // Verify gym profile ownership
        if (!gymProfile.getOwner().getUserId().equals(currentOwner.getUserId())) {
            throw new SecurityException("You don't have access to this gym profile");
        }
        member.setGymProfile(gymProfile);

        // Handle workout plan
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(registrationDTO.getPlanId())
            .orElseThrow(() -> new RuntimeException("Workout plan not found"));
        member.setWorkoutPlan(workoutPlan);

        // Handle membership plan
        MembershipPlan membershipPlan = membershipPlanRepository.findById(registrationDTO.getMembershipPlanId())
            .orElseThrow(() -> new RuntimeException("Membership plan not found"));
        
        MembershipPlanJoin membershipJoin = new MembershipPlanJoin();
        membershipJoin.setMembershipPlan(membershipPlan);
        membershipJoin.setStartDate(new Date());
        membershipJoin.setEndDate(calculateEndDate(membershipPlan.getDuration()));
        membershipJoin.setStatus("ACTIVE");
        membershipPlanJoinRepository.save(membershipJoin);
        
        member.setMembership(membershipJoin);
        System.out.print("Here");
        return memberRepository.save(member);
    }

    private Date calculateEndDate(String duration) {
        // Implementation to calculate end date based on duration string
        // Example implementation:
        Calendar cal = Calendar.getInstance();
        String[] parts = duration.split(" ");
        int amount = Integer.parseInt(parts[0]);
        String unit = parts[1].toLowerCase();

        switch (unit) {
            case "month":
            case "months":
                cal.add(Calendar.MONTH, amount);
                break;
            case "year":
            case "years":
                cal.add(Calendar.YEAR, amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid duration unit");
        }
        return cal.getTime();
    }
    
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        System.out.println("Authenticating with username: " + loginRequest.getUsername());

        try {
            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = jwtUtils.generateJwtToken((UserDetails) authentication.getPrincipal());

            // Fetch user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found after authentication"));
            
            Long userId = user.getUserId();

            // Handle ADMIN role
            if ("ADMIN".equals(user.getRole().getName())) {
                System.out.println("Admin user logged in");
                return new JwtResponse(jwt, user.getUsername(), user.getEmail(), "ADMIN", " ", userId);
            }
            
            if ("MEMBER".equals(user.getRole().getName())) {
                System.out.println("MEMBER user logged in");
                return new JwtResponse(jwt, user.getUsername(), user.getEmail(), "MEMBER", " ", userId);
            }
            

            // Handle GYM_OWNER role
            if ("GYM_OWNER".equals(user.getRole().getName())) {
                List<GymProfile> optionalGymProfile = gymProfileRepository.findByOwner(user);

                if (optionalGymProfile.isEmpty()) {
                    // Gym profile not found - redirect to profile creation
                    return new JwtResponse(jwt, user.getUsername(), user.getEmail(), "GYM_OWNER", "CREATE_PROFILE",userId);
                }

                boolean hasApproved = optionalGymProfile.stream().anyMatch(profile -> "APPROVED".equals(profile.getStatus()));
                
               

                // Handle different statuses
                if (hasApproved) {
                    return new JwtResponse(jwt, user.getUsername(), user.getEmail(), "GYM_OWNER", "APPROVED", userId);
                } else if (optionalGymProfile.stream().anyMatch(profile -> "PENDING".equals(profile.getStatus()))) {
                    return new JwtResponse(jwt, user.getUsername(), user.getEmail(), "GYM_OWNER", "PENDING", userId);
                } else {
                    return new JwtResponse(jwt, user.getUsername(), user.getEmail(), "GYM_OWNER", "REJECTED", userId);
                }
            }

            // Default fallback
            throw new RuntimeException("Unknown role or invalid user state");

        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid username or password");
        }
    }


 
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void savePasswordResetToken(User user, String token) {
        user.setResetToken(token);
        user.setTokenExpiration(Instant.now().plusSeconds(1800)); // Token expires in 30 minutes
        userRepository.save(user);
    }

    public User findByResetToken(String token) {
        return userRepository.findByResetToken(token).orElse(null);
    }

    public boolean isTokenExpired(String token) {
        Instant tokenExpirationTime = getTokenExpirationTime(token);
        if (tokenExpirationTime == null) {
            return true;  // Token not found, considered expired
        }
        return Instant.now().isAfter(tokenExpirationTime);
    }

    
    public Instant getTokenExpirationTime(String token) {
        // Retrieve the user by reset token
        Optional<User> optionalUser = userRepository.findByResetToken(token);
        
        // Check if the user is present
        if (optionalUser.isPresent()) {
            // Return the token expiration time
            return optionalUser.get().getTokenExpiration();
        }
        
        // Throw an exception if the token is not found
        throw new IllegalArgumentException("Invalid reset token");
    }


    public void updatePassword(User user, String newPassword) {
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        user.setResetToken(null); // Clear the reset token
        user.setTokenExpiration(null);
        userRepository.save(user);
        System.out.println("Updated password hash: " + hashedPassword);
    }
    
//    public List<User> getUnverifiedGymOwners() {
//        return userRepository.findByRoleAndIsVerified("GYM_OWNER", false);
//    }
//
//    public void verifyGymOwner(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setVerified(true);
//        userRepository.save(user);
//    }
//



//    public JwtResponse authenticateUser(LoginRequest loginRequest) {
//        // Logic for authentication and JWT generation
//    }
    
}