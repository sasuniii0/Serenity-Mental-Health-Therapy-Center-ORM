package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.entity.Therapist;

import java.util.ArrayList;
import java.util.List;

public class TherapistManageBOImpl implements TherapistManageBO {

    TherapistDAO therapistDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);
    @Override
    public List<TherapistDTO> getAllTherapist() {
        List<TherapistDTO> therapistDTOS = new ArrayList<>();
        List<Therapist> therapists = therapistDAO.getAll();
        for (Therapist therapist : therapists){
            therapistDTOS.add(new TherapistDTO(
                    therapist.getId(),
                    therapist.getName(),
                    therapist.getEmail(),
                    therapist.getContactNumber(),
                    therapist.getTherapyProgram()));
        }
        return therapistDTOS;

    }

    @Override
    public boolean addTherapist(TherapistDTO therapistDTO) {
        try{
            Therapist therapist = new Therapist(
                    therapistDTO.getId(),
                    therapistDTO.getName(),
                    therapistDTO.getEmail(),
                    therapistDTO.getContactNumber(),
                    therapistDTO.getTherapyProgramId(),
                    therapistDTO.getTherapyProgramName()
            );
            return therapistDAO.save(therapist);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateNextTherapistId() {
        String lastId = therapistDAO.getLastTherapistId(); // returns something like "MT-003"

        if (lastId != null && lastId.matches("T-\\d+")) {
            int lastNumber = Integer.parseInt(lastId.split("-")[1]);
            int nextNumber = lastNumber + 1;
            return String.format("T-%03d", nextNumber); // adds leading zeros
        } else {
            return "T-001"; // First ID
        }
    }

    @Override
    public boolean deleteTherapist(String id) {
        return therapistDAO.delete(id);
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) {
        try{
            Therapist therapist = new Therapist(
                    therapistDTO.getId(),
                    therapistDTO.getName(),
                    therapistDTO.getEmail(),
                    therapistDTO.getContactNumber(),
                    therapistDTO.getTherapyProgramId(),
                    therapistDTO.getTherapyProgramName()
            );
            return therapistDAO.update(therapist);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
