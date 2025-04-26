package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.entity.TherapyProgram;
import lk.ijse.gdse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TherapyProgramManageBOImpl implements TherapyProgramManageBO {

    TherapyProgramDAO therapyProgramDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.THERAPY_PROGRAM);


    @Override
    public List<TherapyProgramDTO> getAllPrograms() {
        List<TherapyProgram> programs = therapyProgramDAO.getAll();
        ArrayList<TherapyProgramDTO> programDTOS = new ArrayList<>();

        for (TherapyProgram program : programs) {
            programDTOS.add(new TherapyProgramDTO(
                    program.getId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()

            ));
        }
        return programDTOS;
    }

    @Override
    public String generateNextTherapyProId() {
        return "";
    }

    @Override
    public boolean addTherapyProgram(TherapyProgramDTO therapyProgram) {
        return therapyProgramDAO.save(new TherapyProgram(
                therapyProgram.getId(),
                therapyProgram.getProgramName(),
                therapyProgram.getDuration(),
                therapyProgram.getFee()

        ));
    }

    @Override
    public boolean deleteTherapyProgram(String id) {
        return therapyProgramDAO.delete(id);
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgram) {
        return therapyProgramDAO.update(new TherapyProgram(
                therapyProgram.getId(),
                therapyProgram.getProgramName(),
                therapyProgram.getDuration(),
                therapyProgram.getFee()

        ));
    }

    @Override
    public List<String> loadTherapyProgramsForPatient(String patientId) {
        return therapyProgramDAO.loadTherapyProgramsForPatient(patientId);
    }

    @Override
    public String getProgramNameById(String programId) {
        return therapyProgramDAO.getProgramNameById(programId);

    }

    @Override
    public String getProgramIdByName(String selectedProgramName) {
        return therapyProgramDAO.getProgramIdByName(selectedProgramName);

    }

    @Override
    public ArrayList<String> getAllProgramsNames() {
        try {
            return therapyProgramDAO.getAllProgramNames();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get program names", e);
        }
    }

    @Override
    public String getNextProgramId() {
        return therapyProgramDAO.getNextId();
    }

    @Override
    public List<String> getRegisteredProgramsByPatientId(String patientId) {
        return therapyProgramDAO.getRegisteredProgramsByPatientId(patientId);
    }

    @Override
    public double getProgramFeeById(String programId) {
        return therapyProgramDAO.getProgramFeeById(programId);
    }

    @Override
    public ArrayList<String> getProgramNames() {
        return therapyProgramDAO.getAllProgramNames();
    }
}
