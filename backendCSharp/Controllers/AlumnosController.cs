using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using backendCSharp.Data;
using backendCSharp.Models;

namespace backendCSharp.Controllers
{
    [ApiController] // indica que es un controlador de API
    [Route("/alumnos")]// ruta base
    public class AlumnosController : ControllerBase
    {
        private readonly AppDbContext _context;
        // constructor
        public AlumnosController(AppDbContext context)
        {
            _context = context;
        }

        // obtener alumnos
        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var alumnos = await _context.Alumnos.OrderBy(a => a.Id).ToListAsync();
            return Ok(alumnos);
        }

        // crear alumno
        [HttpPost]
        public async Task<IActionResult> Create(Alumnos alumno)
        {
            _context.Alumnos.Add(alumno);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetById), new { id = alumno.Id }, alumno);
        }

        // obtener alumno por id
        [HttpGet("{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var alumno = await _context.Alumnos.FindAsync(id);
            if (alumno == null) return NotFound();
            return Ok(alumno);
        }

        // actualizar alumno
        [HttpPut("{id}")]
        public async Task<IActionResult> Update(int id, Alumnos alumno)
        {
            var existente = await _context.Alumnos.FindAsync(id);
            if (existente == null) return NotFound();

            existente.Nombre = alumno.Nombre;
            existente.Apellido = alumno.Apellido;
            existente.Telefono = alumno.Telefono;
            existente.Direccion = alumno.Direccion;

            await _context.SaveChangesAsync();
            return Ok(existente);
        }

        // eliminar alumno
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var alumno = await _context.Alumnos.FindAsync(id);
            if (alumno == null) return NotFound();

            _context.Alumnos.Remove(alumno);
            await _context.SaveChangesAsync();
            return NoContent();
        }
    }
}
