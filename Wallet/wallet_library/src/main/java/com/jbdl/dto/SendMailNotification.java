package com.jbdl.dto;

import com.jbdl.enums.ServiceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SendMailNotification {
    private String receiverMailId;

    private String message;

    private String Subject;

    private ServiceType serviceType;
}
