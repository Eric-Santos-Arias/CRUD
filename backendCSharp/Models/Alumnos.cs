using System.ComponentModel.DataAnnotations.Schema;

namespace backendCSharp.Models
{
    [Table("alumnos")]
    public class Alumnos
    {
        [Column("id")]
        public int Id { get; set; }          // Clave primaria
        [Column("nombre")]
        public required string Nombre { get; set; }
        [Column("apellido")]
        public required string Apellido { get; set; }
        [Column("telefono")]
        public required string Telefono { get; set; }
        [Column("direccion")]
        public required string Direccion { get; set; }
    }
}
