package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Repositories.DispositivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivosService {
    @Autowired
    private DispositivosRepository dispositivosRepository;

    public List<DispositivosModel> Get() { return dispositivosRepository.findAll();}

    public Optional<DispositivosModel> GetById(Long id){
        return dispositivosRepository.findById(id);
    }

    public Optional<DispositivosModel> findByNombreAndEmpresasModelId(String nombre, Long empresaModelId) {
        return dispositivosRepository.findByNombreAndEmpresasModelId(nombre, empresaModelId);
    }
    public DispositivosModel Save(DispositivosModel dispositivosModel) {
        return dispositivosRepository.save(dispositivosModel);
    }

    public DispositivosModel Edit(Long id, DispositivosModel dispositivosModel){
        Optional<DispositivosModel> existing = dispositivosRepository.findById(id);
        if(existing.isPresent()){
            DispositivosModel dispositivo = existing.get();
            dispositivo.setNombre(dispositivosModel.getNombre());
            dispositivo.setRutasModel(dispositivosModel.getRutasModel());
            dispositivo.setEmpresasModel(dispositivosModel.getEmpresasModel());
            return dispositivosRepository.save(dispositivo);
        }
        return null;
    }

    public DispositivosModel ReasignarRuta(Long id, DispositivosModel dispositivosModel){
        Optional<DispositivosModel> existing = dispositivosRepository.findById(id);
        if(existing.isPresent()){
            DispositivosModel dispositivo = existing.get();
            dispositivo.setRutasModel(dispositivosModel.getRutasModel());
            dispositivo.setEmpresasModel(dispositivosModel.getEmpresasModel());
            return dispositivosRepository.save(dispositivo);
        }
        return null;
    }

    public void Delete(Long id){
        dispositivosRepository.deleteById(id);
    }

}
