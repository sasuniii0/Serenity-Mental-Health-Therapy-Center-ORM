package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.QueryDAO;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.entity.Therapist;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TherapistManageBOImpl implements TherapistManageBO {

    TherapistDAO therapistManageDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);
    TherapyProgramDAO therapyProgramDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);
    QueryDAO queryDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<TherapistDTO> getAllTherapist() {
        try {
            List<Therapist> therapists = therapistManageDAO.getAll();
            List<TherapistDTO> therapistDTOS = new ArrayList<>();

            for (Therapist therapist : therapists) {
                // Handle possible null program
                String programId = (therapist.getTherapyProgram() != null)
                        ? therapist.getTherapyProgram().getId()
                        : null;

                therapistDTOS.add(new TherapistDTO(
                        therapist.getId(),
                        programId,
                        therapist.getName(),
                        therapist.getAddress(),
                        therapist.getMobileNumber(),
                        therapist.getNic()
                ));
            }
            return therapistDTOS;
        } catch (Exception e) {
            // Log the error
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error converting to DTOs", e);
            throw new RuntimeException("Failed to convert therapists to DTOs", e);
        }
    }

    @Override
    public boolean addTherapist(TherapistDTO therapistDTO) {
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
        return therapistManageDAO.save(therapist);
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

    @Override
    public Map<String, Integer> getTherapistSessionCounts() {
        return queryDAO.getTherapistSessionCounts();
    }
}
