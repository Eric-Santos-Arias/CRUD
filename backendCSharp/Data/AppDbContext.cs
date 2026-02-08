using Microsoft.EntityFrameworkCore;
using backendCSharp.Models;
namespace backendCSharp.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {
        }

        public DbSet<Alumnos> Alumnos { get; set; }
    }
}
