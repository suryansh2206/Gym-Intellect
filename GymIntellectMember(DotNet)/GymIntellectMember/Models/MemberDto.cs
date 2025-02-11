namespace GymIntellectMember.Models
{
    public class MemberDto
    {
        public int MemberId { get; set; }
        public string Username { get; set; }
        public string MembershipPlanName { get; set; }
        public string WorkoutPlanName { get; set; }
        public string GymProfileName { get; set; }
    }
}
