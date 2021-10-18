package carroll.tbel.restapiexo.controllers;

import carroll.tbel.restapiexo.models.dto.ClientDTO;
import carroll.tbel.restapiexo.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    public ResponseEntity<ClientDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(service.getOne(id) );
    }

}
