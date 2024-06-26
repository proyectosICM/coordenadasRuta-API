package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.repositories.DispositivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivosService {
    @Autowired
    private DispositivosRepository dispositivosRepository;

    public List<DispositivosModel> getAllDispositivos() { return dispositivosRepository.findAll();}

    public Optional<DispositivosModel> getDispositivoById(Long id){
        return dispositivosRepository.findById(id);
    }

    public Optional<DispositivosModel> findByNombreAndEmpresasModelId(String nombre, Long empresaModelId) {
        return dispositivosRepository.findByNombreAndEmpresasModelId(nombre, empresaModelId);
    }

    public Page<DispositivosModel> findByEmpresaIdAndEstado(Long empresa, Boolean estado, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return dispositivosRepository.findByEmpresasModelIdAndEstado(empresa, estado, pageable);
    }

    public DispositivosModel saveDispositivo(DispositivosModel dispositivosModel) {
        return dispositivosRepository.save(dispositivosModel);
    }

    public DispositivosModel actualizarDispositivo(Long id, DispositivosModel dispositivosModel){
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

    public DispositivosModel actualizarDispositivoprop(Long id, DispositivosModel dispositivosModel){
        Optional<DispositivosModel> existing = dispositivosRepository.findById(id);
        if(existing.isPresent()){
            DispositivosModel dispositivo = existing.get();
            dispositivo.setRutasModel(dispositivosModel.getRutasModel());
            dispositivo.setVelocidad(dispositivosModel.getVelocidad());
            dispositivo.setVolumen(dispositivosModel.getVolumen());
            return dispositivosRepository.save(dispositivo);
        }
        return null;
    }


    public String props(Long id) {
        Optional<DispositivosModel> existing = dispositivosRepository.findById(id);

        if (existing.isPresent()) {
            DispositivosModel dispositivo = existing.get();

            // Concatena los valores de velocidad y volumen
            return dispositivo.getVolumen() + "\n" + dispositivo.getVelocidad();
        } else {
            return null;
        }
    }



    public DispositivosModel reasignarRuta(Long id, DispositivosModel dispositivosModel){
        Optional<DispositivosModel> existing = dispositivosRepository.findById(id);
        if(existing.isPresent()){
            DispositivosModel dispositivo = existing.get();
            dispositivo.setRutasModel(dispositivosModel.getRutasModel());
            dispositivo.setEmpresasModel(dispositivosModel.getEmpresasModel());
            return dispositivosRepository.save(dispositivo);
        }
        return null;
    }

    public void deleteDispositivoById(Long id){
        dispositivosRepository.deleteById(id);
    }

}
