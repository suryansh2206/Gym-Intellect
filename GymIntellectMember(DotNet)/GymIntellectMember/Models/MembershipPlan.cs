using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GymIntellectMember.Models
{
    public class MembershipPlan
    {
        [Key]
        [Column("member_plan_id")]
        public int MemberPlanId { get; set; }

        [Column("plan_name")]
        public string PlanName { get; set; }
    }
}
