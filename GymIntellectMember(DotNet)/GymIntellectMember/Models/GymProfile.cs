using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GymIntellectMember.Models
{
    public class GymProfile
    {
        [Key]
        [Column("gym_profile_id")]
        public int GymProfileId { get; set; }
        [Column("gym_name")]
        public string GymName { get; set; }
    }
}
