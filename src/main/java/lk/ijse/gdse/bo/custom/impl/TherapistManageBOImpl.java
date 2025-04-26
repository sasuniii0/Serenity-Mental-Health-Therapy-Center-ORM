package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.entity.Therapist;

import java.util.ArrayList;
import java.util.List;

public class TherapistManageBOImpl implements TherapistManageBO {


    @Override
    public List<TherapistDTO> getAllTherapist() {
        return List.of();
    }

    @Override
    public boolean addTherapist(TherapistDTO therapistDTO) {
        return false;
    }

    @Override
    public String generateNextTherapistId() {
        return "";
    }

    @Override
    public boolean deleteTherapist(String id) {
        return false;
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) {
        return false;
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
}
