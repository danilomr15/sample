package br.com.danilomr.tcp.endpoint;

import br.com.danilomr.tcp.service.CpfValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class TcpServerEndpoint {

    @Autowired
    private CpfValidationService cpfValidationService;

    @ServiceActivator(inputChannel = "inboundChannel")
    public byte[] process(final byte[] message) {

        final String response = cpfValidationService.validate(new String(message));
        return response.getBytes();
    }
}
