package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.models.PaisesModel;
import com.ICM.coordenadasRutaAPI.models.RutasModel;
import com.ICM.coordenadasRutaAPI.repositories.DispositivosRepository;
import com.ICM.coordenadasRutaAPI.repositories.EmpresasRepository;
import com.ICM.coordenadasRutaAPI.repositories.PaisesRepository;
import com.ICM.coordenadasRutaAPI.repositories.RutasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresasService {
    @Autowired
    private EmpresasRepository empresasRepository;

    @Autowired
    private RutasRepository rutasRepository;

    @Autowired
    private DispositivosRepository dispositivosRepository;

    @Autowired
    private PaisesRepository paisesRepository;

    /**
     * Pais por defecto para la ruta base al crear empresa.
     * Cambia esto en application.properties si tu Perú no es ID=1:
     * app.rutas.defaultPaisId=2
     */
    @Value("${app.rutas.defaultPaisId:1}")
    private Long defaultPaisId;

    /**
     * Sufijo para la ruta base
     */
    @Value("${app.rutas.baseSuffix:-ruta-base}")
    private String baseRouteSuffix;

    /**
     * Authenticates a company with given credentials.
     * @param empresasModel contains login credentials.
     * @return Authenticated company or null if authentication fails.
     */
    public EmpresasModel authenticateCompany(EmpresasModel empresasModel) {
        String usuario = empresasModel.getUsuario();
        String password = empresasModel.getPassword();
        Optional<EmpresasModel> empresa = empresasRepository.findByUsuarioAndPassword(usuario, password);
        return empresa.orElse(null);
    }

    /**
     * Provides search services to obtain various types of company information.
     * Includes methods to find companies by name, by associated user, retrieve all companies, and find a specific company by its ID.
     */
    public Optional<EmpresasModel> findCompanyByName(String nombre){
        return empresasRepository.findByNombre(nombre);
    }

    public Optional<EmpresasModel> findCompanyByUser(String usuario){
        return empresasRepository.findByUsuario(usuario);
    }

    public List<EmpresasModel> getAllCompanies(){
        return empresasRepository.findAll();
    }

    public Page<EmpresasModel> getAllCompanies(Pageable pageable){
        return empresasRepository.findAll(pageable);
    }

    public Optional<EmpresasModel> getCompanyById(Long id){
        return empresasRepository.findById(id);
    }

    /**
     * Handles CRUD operations for companies.
     * - saveCompany: Saves a new or existing company's information.
     * - editCompany: Updates an existing company's information based on its ID. Returns null if the company does not exist.
     * - deleteCompany: Deletes a company based on its ID.
     */

    @Transactional
    public EmpresasModel saveCompany(EmpresasModel empresasModel) {
        if (empresasModel == null) throw new IllegalArgumentException("Empresa inválida");
        boolean isNew = (empresasModel.getId() == null);

        // guarda empresa
        EmpresasModel saved = empresasRepository.save(empresasModel);

        // solo en creación real
        if (isNew) {
            String empresaNombre = (saved.getNombre() == null) ? "" : saved.getNombre().trim();
            if (empresaNombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre de la empresa es obligatorio para crear ruta base");
            }

            String baseRouteName = empresaNombre + baseRouteSuffix; // ej: "ACME-ruta-base"

            boolean exists = rutasRepository.existsByEmpresasModelIdAndNomruta(saved.getId(), baseRouteName);
            if (!exists) {
                PaisesModel pais = paisesRepository.findById(defaultPaisId)
                        .orElseThrow(() -> new IllegalStateException(
                                "No existe el país por defecto (app.rutas.defaultPaisId=" + defaultPaisId + ")"
                        ));

                RutasModel rutaBase = new RutasModel();
                rutaBase.setId(null);
                rutaBase.setNomruta(baseRouteName);
                rutaBase.setEstado(true);
                rutaBase.setEmpresasModel(saved);
                rutaBase.setPaisesModel(pais);
                rutaBase.setDiadeshabilitacion(null);
                rutaBase.setDiaeliminacion(null);

                rutasRepository.save(rutaBase);
            }
        }

        return saved;
    }

    public EmpresasModel editCompany(Long id, EmpresasModel empresasModel){
        Optional<EmpresasModel> existing = empresasRepository.findById(id);
        if(existing.isPresent()){
            EmpresasModel empresa = existing.get();
            empresa.setNombre(empresasModel.getNombre());
            empresa.setUsuario(empresasModel.getUsuario());
            empresa.setPassword(empresasModel.getPassword());
            return empresasRepository.save(empresa);
        }
        return null;
    }

    @Transactional
    public boolean deleteCompany(Long id) {
        if (id == null) return false;

        if (id.equals(1L)) {
            throw new IllegalArgumentException("No se puede eliminar la empresa superusuario (id=1).");
        }

        if (!empresasRepository.existsById(id)) return false;

        if (!empresasRepository.existsById(1L)) {
            throw new IllegalStateException("No existe la empresa superusuario (id=1).");
        }

        rutasRepository.transferirRutasAEmpresa1(id, 1L);
        dispositivosRepository.transferirDispositivosAEmpresa1(id, 1L);

        empresasRepository.deleteById(id);
        return true;
    }
}
