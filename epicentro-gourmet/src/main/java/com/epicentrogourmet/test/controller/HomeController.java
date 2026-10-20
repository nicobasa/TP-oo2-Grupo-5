package com.epicentrogourmet.test.controller;

import com.epicentrogourmet.test.entity.*;
import com.epicentrogourmet.test.repository.RolRepository;
import com.epicentrogourmet.test.service.EmpleadoService;
import com.epicentrogourmet.test.service.FestivalService;
import com.epicentrogourmet.test.service.UnidadVentaService;
import com.epicentrogourmet.test.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class HomeController {

    private final FestivalService festivalService;
    private final UnidadVentaService unidadVentaService;
    private final EmpleadoService empleadoService;
    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public HomeController(
            FestivalService festivalService,
            UnidadVentaService unidadVentaService,
            EmpleadoService empleadoService,
            UsuarioService usuarioService,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder) {
        this.festivalService = festivalService;
        this.unidadVentaService = unidadVentaService;
        this.empleadoService = empleadoService;
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("festivales", festivalService.traer());
        return "home";
    }

    @GetMapping("/registro-unidad")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("festivales", festivalService.traer());
        return "registro-unidad";
    }

    @PostMapping("/registro-unidad")
    @Transactional
    public String registrarUnidadConResponsable(
            @RequestParam Long festivalId,
            @RequestParam String tipoUnidad,
            @RequestParam String nombreComercial,
            @RequestParam Double superficie,
            @RequestParam String codigo,
            @RequestParam(required = false) String patente,
            @RequestParam(defaultValue = "false") boolean requiereElectricidad,
            @RequestParam(required = false) Integer cantidadCarpas,
            @RequestParam(required = false) Integer tiempoMontaje,
            @RequestParam String nombreResponsable,
            @RequestParam String apellidoResponsable,
            @RequestParam Long dniResponsable,
            @RequestParam LocalDate fechaNacimientoResponsable,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {
            if (usuarioService.traerPorUsername(username) != null) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
            }

            if (!codigo.matches("[A-Z]{2}[0-9]{8}")) {
                throw new IllegalArgumentException(
                        "El código de la unidad debe tener 2 letras mayúsculas seguidas de 8 números (ej: FT00000001).");
            }

            // 1. Crear el Usuario con rol RESPONSABLE
            Rol rolResponsable = rolRepository.findByNombre("RESPONSABLE")
                    .orElseThrow(() -> new IllegalStateException("Rol RESPONSABLE no encontrado"));

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setPassword(passwordEncoder.encode(password));
            nuevoUsuario.setRol(rolResponsable);
            usuarioService.agregar(nuevoUsuario);

            // 2. Crear la Unidad de Venta (FoodTruck o PuestoDesarmable)
            UnidadVenta unidad;
            if ("FOOD_TRUCK".equals(tipoUnidad)) {
                FoodTruck ft = new FoodTruck();
                ft.setPatente(patente);
                ft.setRequiereElectricidad(requiereElectricidad);
                unidad = ft;
            } else {
                PuestoDesarmable pd = new PuestoDesarmable();
                pd.setCantidadCarpas(cantidadCarpas != null ? cantidadCarpas : 1);
                pd.setTiempoMontaje(tiempoMontaje != null ? tiempoMontaje : 30);
                unidad = pd;
            }

            unidad.setNombreComercial(nombreComercial);
            unidad.setSuperficie(superficie);
            unidad.setCodigo(codigo);
            unidadVentaService.agregar(unidad);

            // 3. Crear el Empleado que actúa como Responsable y vincularlo
            Empleado responsable = new Empleado();
            responsable.setNombre(nombreResponsable);
            responsable.setApellido(apellidoResponsable);
            responsable.setDni(dniResponsable);
            responsable.setFechaNacimiento(fechaNacimientoResponsable);
            responsable.setFechaIngreso(LocalDate.now());
            responsable.setSueldoBase(100000.0);
            responsable.setActivo(true);
            responsable.setUsuario(nuevoUsuario);
            responsable.setUnidadVenta(unidad);
            empleadoService.agregar(responsable);

            // 4. Asignar el responsable a la unidad y vincular al festival elegido
            unidad.setResponsable(responsable);
            unidadVentaService.actualizar(unidad);

            Festival festival = festivalService.traer(festivalId);
            if (festival != null) {
                festival.getUnidadesDeVenta().add(unidad);
                festivalService.actualizar(festival);
            }

            redirectAttributes.addFlashAttribute("mensajeExito",
                    "Unidad '" + nombreComercial + "' y usuario '" + username + "' registrados con éxito. ¡Ya podés iniciar sesión!");
            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("festivales", festivalService.traer());
            return "registro-unidad";
        }
    }
}