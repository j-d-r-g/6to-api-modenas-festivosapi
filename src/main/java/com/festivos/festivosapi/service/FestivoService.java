package com.festivos.festivosapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.festivos.festivosapi.entity.FestivoEntity;
import com.festivos.festivosapi.entity.CalendarioEntity;
import com.festivos.festivosapi.model.Festivo;
import com.festivos.festivosapi.repository.FestivoRepository;
import com.festivos.festivosapi.repository.CalendarioRepository;

@Service
public class FestivoService {

   @Autowired
    private FestivoRepository festivoRepository;

    @Autowired
    private CalendarioRepository calendarioRepository;
    

    public List<Festivo> obtenerFestivos(int anio) {

        List<Festivo> listaFestivos = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        LocalDate fecha = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);

        while (!fecha.isAfter(fin)) {

            int mes = fecha.getMonthValue();
            int dia = fecha.getDayOfMonth();

            String url = "http://api-node:3000/" + anio + "/" + mes + "/" + dia;

            String respuesta = restTemplate.getForObject(url, String.class);

            if (respuesta.contains("Es festivo")) {

                String nombre = respuesta.split("\"nombre\":\"")[1].split("\"")[0];

                Festivo festivo = new Festivo(
                        nombre,
                        fecha.toString()
                );

                listaFestivos.add(festivo);

               FestivoEntity entity = new FestivoEntity(
                        nombre,
                        fecha.toString()
                );

                festivoRepository.save(entity);
            }

            fecha = fecha.plusDays(1);
        }

        return listaFestivos;
    }

    public boolean generarCalendario(int anio) {

        RestTemplate restTemplate = new RestTemplate();

        LocalDate fecha = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);

        while (!fecha.isAfter(fin)) {

            int mes = fecha.getMonthValue();
            int dia = fecha.getDayOfMonth();

            String url = "http://localhost:3000/festivos/" + anio + "/" + mes + "/" + dia;

            String respuesta = restTemplate.getForObject(url, String.class);

            String tipo;

            if (respuesta.contains("Es festivo")) {
                tipo = "FESTIVO";
            }
            else if (fecha.getDayOfWeek().getValue() == 6 || fecha.getDayOfWeek().getValue() == 7) {
                tipo = "FIN_SEMANA";
            }
            else {
                tipo = "LABORAL";
            }

            CalendarioEntity entity = new CalendarioEntity(
                    fecha.toString(),
                    tipo
            );

           calendarioRepository.save(entity);

            fecha = fecha.plusDays(1);
        }

        return true;
    }

    public List<CalendarioEntity> obtenerCalendario() {

    return calendarioRepository.findAll();

}
}