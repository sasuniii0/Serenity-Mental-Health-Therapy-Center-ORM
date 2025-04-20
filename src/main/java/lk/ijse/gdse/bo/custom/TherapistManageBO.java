package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapistDTO;

import java.util.List;

public interface TherapistManageBO extends SuperBO {
    List<TherapistDTO> getAllTherapist();

    boolean addTherapist(TherapistDTO therapistDTO);

    String generateNextTherapistId();

    boolean deleteTherapist(String id);

    boolean updateTherapist(TherapistDTO therapistDTO);
}
