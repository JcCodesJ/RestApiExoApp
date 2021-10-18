package carroll.tbel.restapiexo.controllers;

import carroll.tbel.restapiexo.exceptions.ElementNotFoundException;
import carroll.tbel.restapiexo.models.dto.ErrorDTO;
import carroll.tbel.restapiexo.models.dto.ReservationDTO;
import carroll.tbel.restapiexo.models.forms.ReservationForm;
import carroll.tbel.restapiexo.models.forms.ReservationUpdateForm;
import carroll.tbel.restapiexo.service.ReservationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/", "all"})
    public List<ReservationDTO> getAll(){
        return service.getAll();
    }

    @GetMapping({"/{resId}"})
    public ReservationDTO getOne(@PathVariable String resId){
        return service.getOne(resId);
    }

    @GetMapping(params = {"resId"})
    public ReservationDTO getOneByParam(@RequestParam String resId){
        return service.getOne(resId);
    }

    @PostMapping(path = {"", "/", "/add"})
    public ReservationDTO insert(@Valid @RequestBody ReservationForm form, @RequestHeader HttpHeaders headers){

        for (String key : headers.keySet()) {
            System.out.println(headers.get(key) );
        }
        return service.insert(form);
    }

    @DeleteMapping("/{resId}")
    public ReservationDTO delete(@PathVariable(name = "resId") String id){
        return service.delete(id);
    }

    @DeleteMapping(params = "resId")
    public ReservationDTO deleteByParam(@RequestParam(name = "resId") String id){
        return service.delete(id);
    }

    @PutMapping(params = "resId")
    public ReservationDTO update(@RequestParam String resId, @Valid @RequestBody ReservationUpdateForm form){
        return service.update(resId, form);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ElementNotFoundException ex){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Expires", Instant.now().plusMillis(600_000).toString());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(new ErrorDTO(ex.getMessage()));
    }
}