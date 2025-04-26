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


    @Override
    public List<TherapyProgramDTO> getAllPrograms() {
        return List.of();
    }

    @Override
    public String generateNextTherapyProId() {
        return "";
    }

    @Override
    public boolean addTherapyProgram(TherapyProgramDTO therapyProgram) {
        return false;
    }

    @Override
    public boolean deleteTherapyProgram(String id) {
        return false;
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgram) {
        return false;
    }

    @Override
    public List<String> loadTherapyProgramsForPatient(String patientId) {
        return List.of();
    }
}
