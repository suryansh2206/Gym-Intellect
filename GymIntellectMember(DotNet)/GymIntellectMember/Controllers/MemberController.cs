using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using GymIntellectMember.Models;
using GymIntellectMember.Data;
using Microsoft.EntityFrameworkCore;


namespace GymIntellectMember.Controllers
{
    [Route("api/members")]
    [ApiController]
    public class MemberController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public MemberController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<MemberDto>>> GetMembers()
        {
            var members = await _context.Members
                .Include(m => m.User)
                .Include(m => m.MembershipPlan)
                .Include(m => m.WorkoutPlan)
                .Include(m => m.GymProfile)
                .Select(m => new MemberDto
                {
                    MemberId = m.MemberId,
                    Username = m.User.Username,
                    MembershipPlanName = m.MembershipPlan.PlanName,
                    WorkoutPlanName = m.WorkoutPlan.PlanName,
                    GymProfileName = m.GymProfile.GymName
                })
                .ToListAsync();

            return Ok(members);
        }
    }
}
