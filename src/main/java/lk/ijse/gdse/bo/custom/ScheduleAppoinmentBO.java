package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.dto.TherapySessionDTO;

public interface ScheduleAppoinmentBO extends SuperBO {
    boolean saveSessionAndPayment(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO);

    boolean updateSessionAndPayment(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO);
}
