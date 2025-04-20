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
        List<TherapyProgramDTO> therapyProgramDTOS = new ArrayList<>();
        List<TherapyProgram> therapyPrograms = therapyProgramDAO.getAll();

        for (TherapyProgram therapyProgram : therapyPrograms){
            TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(therapyProgram.getId(),therapyProgram.getName(),therapyProgram.getFee(),therapyProgram.getDuration());
            therapyProgramDTOS.add(therapyProgramDTO);
        }
        return therapyProgramDTOS;
    }

    @Override
    public String generateNextTherapyProId() {
        String lastId = therapyProgramDAO.getLastTherapyProId(); // returns something like "MT-003"

        if (lastId != null && lastId.matches("MT-\\d+")) {
            int lastNumber = Integer.parseInt(lastId.split("-")[1]);
            int nextNumber = lastNumber + 1;
            return String.format("MT-%03d", nextNumber); // adds leading zeros
        } else {
            return "MT-001"; // First ID
        }
    }


    @Override
    public boolean addTherapyProgram(TherapyProgramDTO therapyProgram) {
        return therapyProgramDAO.save(new TherapyProgram(therapyProgram.getId(),therapyProgram.getName(),therapyProgram.getFee(),therapyProgram.getDuration()));
    }

    @Override
    public boolean deleteTherapyProgram(String id) {
        return therapyProgramDAO.delete(id);
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgram) {
        return therapyProgramDAO.update(new TherapyProgram(therapyProgram.getId(),therapyProgram.getName(),therapyProgram.getFee(),therapyProgram.getDuration()));
    }
}
