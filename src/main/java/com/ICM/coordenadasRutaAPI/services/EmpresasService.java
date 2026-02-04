package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.repositories.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresasService {
    @Autowired
    private EmpresasRepository empresasRepository;

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

    public Optional<EmpresasModel> getCompanyById(Long id){
        return empresasRepository.findById(id);
    }

    /**
     * Handles CRUD operations for companies.
     * - saveCompany: Saves a new or existing company's information.
     * - editCompany: Updates an existing company's information based on its ID. Returns null if the company does not exist.
     * - deleteCompany: Deletes a company based on its ID.
     */

    public EmpresasModel saveCompany(EmpresasModel empresasModel) {
        return empresasRepository.save(empresasModel);
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

    public boolean deleteCompany(Long id) {
        if (!empresasRepository.existsById(id)) return false;
        empresasRepository.deleteById(id);
        return true;
    }
}
