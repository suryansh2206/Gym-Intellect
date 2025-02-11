using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GymIntellectMember.Models
{
    public class WorkoutPlan
    {
        [Key]
        [Column("plan_id")]
        public int PlanId { get; set; }
        [Column("plan_name")]
        public string PlanName { get; set; }
    }
}
