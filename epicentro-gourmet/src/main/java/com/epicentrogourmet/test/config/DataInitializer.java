package com.epicentrogourmet.test.config;

import com.epicentrogourmet.test.entity.Costo;
import com.epicentrogourmet.test.entity.Festival;
import com.epicentrogourmet.test.entity.Rol;
import com.epicentrogourmet.test.entity.Usuario;
import com.epicentrogourmet.test.repository.FestivalRepository;
import com.epicentrogourmet.test.repository.RolRepository;
import com.epicentrogourmet.test.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatosIniciales(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            FestivalRepository festivalRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            Rol rolAdmin = rolRepository.findByNombre("ADMIN")
                    .orElseGet(() -> rolRepository.save(new Rol(null, "ADMIN")));

            rolRepository.findByNombre("RESPONSABLE")
                    .orElseGet(() -> rolRepository.save(new Rol(null, "RESPONSABLE")));

            rolRepository.findByNombre("EMPLEADO")
                    .orElseGet(() -> rolRepository.save(new Rol(null, "EMPLEADO")));

            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol(rolAdmin);
                usuarioRepository.save(admin);
            }

            if (festivalRepository.count() == 0) {
                festivalRepository.save(crearFestival(
                        "Festival de la Primavera", "Primavera",
                        LocalDate.of(2026, 9, 21), LocalDate.of(2026, 9, 27)));

                festivalRepository.save(crearFestival(
                        "Festival del Verano", "Verano",
                        LocalDate.of(2026, 12, 15), LocalDate.of(2026, 12, 30)));

                festivalRepository.save(crearFestival(
                        "Festival Sabores de Buenos Aires", "Otoño",
                        LocalDate.of(2027, 3, 10), LocalDate.of(2027, 3, 20)));
            }
        };
    }

    private Festival crearFestival(String nombre, String temporada, LocalDate inicio, LocalDate fin) {
        Costo costo = new Costo();
        costo.setCostoPorSuperficie(BigDecimal.valueOf(500));
        costo.setCostoPorMontaje(BigDecimal.valueOf(10));
        costo.setPlusElectricidad(BigDecimal.valueOf(2000));
        costo.setSueldoBase(BigDecimal.valueOf(100000));
        costo.setMontoAnioAntiguedad(BigDecimal.valueOf(5000));
        costo.setPlusCocinero(BigDecimal.valueOf(150000));
        costo.setPlusCocineroAyudante(BigDecimal.valueOf(100000));
        costo.setPlusLavaplatos(BigDecimal.valueOf(50000));

        Festival festival = new Festival();
        festival.setNombre(nombre);
        festival.setTemporada(temporada);
        festival.setFechaInicio(inicio);
        festival.setFechaFin(fin);
        festival.setCosto(costo);
        return festival;
    }
}