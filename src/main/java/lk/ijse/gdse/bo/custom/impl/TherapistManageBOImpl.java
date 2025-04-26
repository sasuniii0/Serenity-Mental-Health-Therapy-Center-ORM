package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistManageBOImpl implements TherapistManageBO {

    TherapistDAO therapistManageDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);
    TherapyProgramDAO therapyProgramDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);


    @Override
    public List<TherapistDTO> getAllTherapist() {
        List<Therapist> therapists = therapistManageDAO.getAll();
        ArrayList<TherapistDTO> therapistDTOS = new ArrayList<>();

        for (Therapist therapist : therapists) {
            therapistDTOS.add(new TherapistDTO(
                    therapist.getId(),
                    therapist.getTherapyProgram().getId(),
                    therapist.getName(),
                    therapist.getAddress(),
                    therapist.getMobileNumber(),
                    therapist.getNic()

            ));
        }
        return therapistDTOS;
    }

    @Override
    public boolean addTherapist(TherapistDTO therapistDTO) {
        try {
            // Get the associated program
            TherapyProgram therapyProgram = therapyProgramDAO.getProgramId(therapistDTO.getProgramId());
            if(therapyProgram == null) {
                throw new NotFoundException("Program not found for ID: " + therapistDTO.getProgramId());
            }

            // Convert DTO to entity
            Therapist therapist = new Therapist(
                    therapistDTO.getId(),
                    therapistDTO.getName(),
                    therapistDTO.getAddress(),
                    therapistDTO.getMobileNumber(),
                    therapistDTO.getNic(),
                    therapyProgram
            );

            // Save using the correct DAO
            return therapistManageDAO.save(therapist);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save therapist: " + e.getMessage(), e);
        }
    }
    @Override
    public String generateNextTherapistId() {
        return "";
    }

    @Override
    public boolean deleteTherapist(String id) {
        return therapistManageDAO.delete(id);
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) {
        TherapyProgram therapyProgram = therapyProgramDAO.getProgramId(therapistDTO.getProgramId());

        if(therapyProgram == null) {
            throw new RuntimeException("Program not found for ID: " + therapistDTO.getProgramId());
        }
        Therapist therapist = new Therapist(
                therapistDTO.getId(),
                therapistDTO.getName(),
                therapistDTO.getAddress(),
                therapistDTO.getMobileNumber(),
                therapistDTO.getNic(),
                therapyProgram
        );
        return therapistManageDAO.update(therapist);
    }

    @Override
    public List<TherapyProgramDTO> getAllPrograms() {
        return List.of();
    }

    @Override
    public List<TherapyProgramDTO> getAllTherapyPrograms() {
        return List.of();
    }

    @Override
    public TherapyProgramDTO searchTherapyProgram(String program) {
        return null;
    }

    @Override
    public String getNextTherapistId() {
        return therapistManageDAO.getNextId();
    }

    @Override
    public ArrayList<String> getTherapistNamesByProgramId(String programId) {
        return therapistManageDAO.getTherapistNameByProgramId(programId);

    }

    @Override
    public String getTherapistIdByName(String selectedTherapistName) {
        return therapistManageDAO.getTherapistIdByName(selectedTherapistName);

    }

    @Override
    public String getTherapistNameById(String therapistId) {
        return therapistManageDAO.getTherapistNameById(therapistId);
    }

    @Override
    public ArrayList<String> getAllTherapistNames() {
        return therapistManageDAO.getAllTherapistNames();
    }

    @Override
    public Optional<Therapist> findByPK(String therapistId) {
        return Optional.empty();
    }
}
