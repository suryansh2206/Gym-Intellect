
    using GymIntellectMember.Models;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;

    namespace GymIntellectMember.Data
    {
        public class ApplicationDbContext : DbContext
        {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options) { }

        public DbSet<Member> Members { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<WorkoutPlan> WorkoutPlans { get; set; }
        public DbSet<GymProfile> GymProfiles { get; set; }
        public DbSet<MembershipPlan> MembershipPlans { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Member>().ToTable("member");
            modelBuilder.Entity<User>().ToTable("user");
            modelBuilder.Entity<WorkoutPlan>().ToTable("Workout_Plan");
            modelBuilder.Entity<GymProfile>().ToTable("Gym_Profile");
            modelBuilder.Entity<MembershipPlan>().ToTable("Membership_Plan");

            modelBuilder.Entity<Member>()
        .HasOne(m => m.WorkoutPlan)
        .WithMany()
        .HasForeignKey(m => m.PlanId)
        .OnDelete(DeleteBehavior.Restrict);  // Prevents cascade delete issues

            modelBuilder.Entity<Member>()
                .HasOne(m => m.MembershipPlan)
                .WithMany()
                .HasForeignKey(m => m.MembershipId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<Member>()
                .HasOne(m => m.GymProfile)
                .WithMany()
                .HasForeignKey(m => m.GymProfileId)
                .OnDelete(DeleteBehavior.Restrict);

            base.OnModelCreating(modelBuilder);
        }
    }
    }
