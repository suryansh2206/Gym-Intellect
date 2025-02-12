using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GymIntellectMember.Models
{
    public class User
    {
        [Key]
        [Column("user_id")]
        public int UserId { get; set; }
        public string Username { get; set; }
    }
}
