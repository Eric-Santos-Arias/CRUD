using Microsoft.EntityFrameworkCore;
using backendCSharp.Data;

var builder = WebApplication.CreateBuilder(args);

if (builder.Environment.IsDevelopment())
{
    builder.Services.AddCors(options =>
    {
        options.AddPolicy("DevCorsPolicy",
            builder =>
            {
                builder.WithOrigins("http://localhost:8080",
                "http://127.0.0.1:8080")
                       .AllowAnyHeader()
                       .AllowAnyMethod()
                       .AllowCredentials();
            });
    });
}

// DbContext con PostgreSQL
builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

// Controladores
builder.Services.AddControllers();

// Swagger para versión 10.x - FORMA SIMPLIFICADA
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(); // ¡Sin configuración adicional!

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    Console.WriteLine("si estamos en desarrollo");
    app.UseSwagger();
    app.UseSwaggerUI(); // ¡Simplificado también!
    app.UseCors("DevCorsPolicy");
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
app.Run();